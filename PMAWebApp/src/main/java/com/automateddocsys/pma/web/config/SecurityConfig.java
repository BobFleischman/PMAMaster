package com.automateddocsys.pma.web.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.automateddocsys.pma.web.handlers.CustomAuthenticationFailureHander;
import com.automateddocsys.pma.web.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customerUserDetailService;

	@Autowired
	private CustomAuthenticationFailureHander customAuthenticationFailureHandler;

	// private AuthenticationProvider authenticationProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerUserDetailService).passwordEncoder(new PlaintextPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/j_spring_security_check/**").permitAll()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/login**").permitAll()
		.antMatchers("/level2").hasRole("USER")
		.antMatchers("/").hasRole("VERIFIED")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login").permitAll()
			.failureHandler(customAuthenticationFailureHandler)
			.defaultSuccessUrl("/level2", true)
			// .failureUrl("/login?error=disabled")
			.usernameParameter("username").passwordParameter("password")
			.and()
			.logout().logoutSuccessUrl("/login?logout")
			.and()
			.csrf()
//				.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
//
//					private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
//
//					private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/v[0-9]*/.*", null);
//
//					@Override
//					public boolean matches(HttpServletRequest request) {
//						// CSRF disabled on allowedMethod
//						if (allowedMethods.matcher(request.getMethod()).matches())
//							return false;
//
//						// CSRF disabled on api calls
//						if (apiMatcher.matches(request))
//							return false;
//
//						// CSRF enables for other requests
//						return false;
//					}
//				})
			;
	}
	// .formLogin().loginPage("/login").permitAll().failureHandler(customAuthenticationFailureHandler)

}
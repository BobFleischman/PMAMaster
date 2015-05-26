/**
 * 
 */
package com.automateddocsys.pma.web.handlers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author Robert
 *
 */
@Component
public class CustomAuthenticationFailureHander implements AuthenticationFailureHandler {
	 private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String defaultFailureUrl = "/login?error=";
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		System.out.println("*************************************************");
		System.out.println("*************************************************");
		System.out.println("*************************************************");
		System.out.println("*************************************************");
		System.out.println("*************************************************");
		System.out.println(exception.getClass());
		System.out.println(exception.getMessage());
		String msg = exception.getMessage();
		msg = URLEncoder.encode(msg,"UTF-8");
		redirectStrategy.sendRedirect(request, response, defaultFailureUrl + msg );

	}

}

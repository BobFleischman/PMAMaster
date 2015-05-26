package com.automateddocsys.pma.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.automateddocsys.pma.webdata.config.DataConfiguration;

@Configuration
@Import({WebConfig.class, SecurityConfig.class, DataConfiguration.class })
@ImportResource({
  "classpath:META-INF/spring/pmaweb-app-context.xml",
  "classpath:META-INF/spring/web-context.xml",
})
public class ServletConfig {
	
//	<!-- freemarker config -->
//	<beans:bean id="freemarkerConfig"
//		class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
//		<beans:property name="templateLoaderPath" value="/WEB-INF/views/ftl" />
//	</beans:bean>


	@Bean
	public FreeMarkerViewResolver viewResolver() {
		FreeMarkerViewResolver result = new org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver();
		result.setCache(true);
		result.setPrefix("");;
		result.setSuffix(".ftl");
		result.setExposeSpringMacroHelpers(true);
		return result;
	}
	
	@Bean
	public FreeMarkerConfigurer freemarkerConfig() {
		FreeMarkerConfigurer result = new org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer();
		result.setTemplateLoaderPath("/WEB-INF/views/ftl/");
		return result;
	}
}

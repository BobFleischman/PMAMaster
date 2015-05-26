package com.automateddocsys.pma.web.mvc.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.automateddocsys.pma.web.config.RootConfiguration;
import com.automateddocsys.pma.web.config.ServletConfig;

public class WebInit implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();

		appContext.setDisplayName("PMA Web Application");

		// Registers the application configuration with the root context
		appContext.register(RootConfiguration.class);

		// Creates the Spring Container shared by all Servlets and Filters
		container.addListener(new ContextLoaderListener(appContext));
		// Creates the dispatcher servlet context
		AnnotationConfigWebApplicationContext servletContext = new AnnotationConfigWebApplicationContext();

		// Registers the servlet configuraton with the dispatcher servlet context
		servletContext.register(ServletConfig.class);

		// Further configures the servlet context
		ServletRegistration.Dynamic dispatcher = container.addServlet("appServlet",
				new DispatcherServlet(servletContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}
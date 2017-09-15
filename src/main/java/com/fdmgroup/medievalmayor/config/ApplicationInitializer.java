package com.fdmgroup.medievalmayor.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class ApplicationInitializer implements WebApplicationInitializer {
	
	static final Logger logger = LogManager.getLogger("ApplicationInitializer.class");

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebAppConfig.class);
		ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher",new DispatcherServlet(context));
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
		logger.trace("Initialized");
	}

	
	
}
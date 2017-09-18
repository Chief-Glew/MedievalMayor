package com.fdmgroup.medievalmayor.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan({"com.fdmgroup.medievalmayor.config","com.fdmgroup.medievalmayor.controllers"})
public class WebAppConfig {

	static final Logger logger = LogManager.getLogger("WebAppConfig.class");
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {  
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();  
		viewResolver.setPrefix("/WEB-INF/views/");  
		viewResolver.setSuffix(".jsp"); 
		logger.debug("ViewResolver set up");
		return viewResolver;  
	}
}

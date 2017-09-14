package com.fdmgroup.medievalmayor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan({"com.fdmgroup.medievalmayor.config","com.fdmgroup.medievalmayor.controllers"})
public class WebAppConfig {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {  
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();  
		viewResolver.setPrefix("/WEB-INF/views/");  
		viewResolver.setSuffix(".jsp"); 
		return viewResolver;  
	}
}

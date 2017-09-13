package com.fdmgroup.medievalmayor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.CRUD.GenericRead;
import com.fdmgroup.medievalmayor.CRUD.GenericWrite;
import com.fdmgroup.medievalmayor.config.WebAppConfig;

public class SpringDemoApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebAppConfig.class);
		for(String beanName : applicationContext.getBeanDefinitionNames()){
			System.out.println(beanName);
		}

		GenericRead<City> genericRead = applicationContext.getBean(GenericRead.class);
		GenericWrite<City> genericWrite = applicationContext.getBean(GenericWrite.class);
		
		System.out.println(genericRead.readAll());
		
		((ConfigurableApplicationContext)applicationContext).close();
	}
}

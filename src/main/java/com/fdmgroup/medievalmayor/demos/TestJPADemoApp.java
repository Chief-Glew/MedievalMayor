package com.fdmgroup.medievalmayor.demos;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.CRUD.CityJPACRUD;
import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;

public class TestJPADemoApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		
		CityFactory cityFactory = applicationContext.getBean(CityFactory.class);
		City city = cityFactory.getNewCity();
		
		CityJPACRUD cityJPACRUD = new CityJPACRUD();
		cityJPACRUD.create(city);
		
	}

}

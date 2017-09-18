package com.fdmgroup.medievalmayor.game.city;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.config.AppConfig;


public class CityFactoryTest {
	private CityFactory cityFactory;
	private City city;

	
	@Before
	public void init(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		cityFactory = applicationContext.getBean(CityFactory.class);
		city = new City("Test City", 10);
	}
	
	@Test
	public void testThatGetNewCityReturnsACity() {
		assertEquals(city.getClass(), cityFactory.getNewCity().getClass());
		
	}
	
	@Test
	public void testThatNewCitiesCanBeNamed() {
		City namedCity = cityFactory.getNewCity("Misty Mountains");
		assertEquals("Misty Mountains", namedCity.getCityName());
		
	}
}

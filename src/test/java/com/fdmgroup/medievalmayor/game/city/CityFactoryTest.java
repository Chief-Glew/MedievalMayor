package com.fdmgroup.medievalmayor.game.city;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class CityFactoryTest {
	private CityFactory cityFactory;
	private City city;

	
	@Before
	public void init(){
		cityFactory = new CityFactory();
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

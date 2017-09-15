package com.fdmgroup.medievalmayor.game.city;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;

import static org.junit.Assert.*;

public class CityFactoryTest {

	private CityFactory cityFactory;
	
	@Before
	public void init(){
		cityFactory = new CityFactory();
	}
	
	@Test
	public void testThatCityFactoryCanCreateADefaultCity(){
		City defaultCity = new City("DefaultCity", 10, 20, 5, new Farm(3), new Mine(2));
		City city = cityFactory.getNewCity();
		assertEquals(defaultCity.toString(), city.toString());
	}
	
	@Test
	public void testThatCityFactoryCanCreateACityWithAName(){
		City testCity = new City("Test City", 10, 20, 5, new Farm(3), new Mine(2));
		City city = cityFactory.getNewCity("Test City");
		assertEquals(testCity.toString(), city.toString());
	}

}

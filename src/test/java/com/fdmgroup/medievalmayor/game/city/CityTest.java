package com.fdmgroup.medievalmayor.game.city;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class CityTest {

	private CityFactory cityFactory;
	private City city;
	private Farm farm;
	
	@Before
	public void init(){
		cityFactory = new CityFactory();
		city = cityFactory.getNewCity();
		farm = new Farm();
	}
	
	@Test
	public void testThatACityContainsAFarm(){
		ResourceProducer cityFarm = city.getResourceProducerOfType(Farm.class);
		assertEquals(farm.getClass(), cityFarm.getClass());
	}
	
	@Test
	public void testThatACityContainsFiveResourceTypes(){

		assertEquals(4, city.getResourceGenerators().size());
	}
	
//	@Test
//	public void testThatGetNewCityReturnsACity() {
//		System.out.println(city.getResourceGenerators());
//		System.out.println(city.getResourceProducerOfType(Farm.class));
//		System.out.println(city.getResourceAmount("Food"));
//		
//		assertEquals(city.getClass(), cityFactory.getNewCity().getClass());
//	}
}

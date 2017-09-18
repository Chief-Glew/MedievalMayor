package com.fdmgroup.medievalmayor.game.city;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceFactory;

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
	public void testThatACityContainsFourResourceTypes(){
		assertEquals(4, city.getResourceGenerators().size());
	}
	
	@Test
	public void testThatACityBeginsWithZeroFood(){
		assertEquals(0, city.getResourceAmount("Food"));
	}
	
	@Test
	public void testThatACityBeginsWithTenPopulation(){
		assertEquals(10, city.getUnassignedPopulation());
	}
	
	@Test
	public void testThatACityCanHaveFoodAmountSet(){
		ResourceFactory resourceFactory = new ResourceFactory();
		Resource resource = resourceFactory.getFood(10);
		city.addResource(resource);
		assertEquals(10, city.getResourceAmount("Food"));
	}
	
	
}

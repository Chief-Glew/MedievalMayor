package com.fdmgroup.medievalmayor.game.city;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resources.Resource;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

public class CityTest {

	private CityFactory cityFactory;
	private City city;
	private Farm farm;
	
	@Before
	public void init(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		cityFactory = applicationContext.getBean(CityFactory.class);
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
	public void testThatCityGetIdMethodWorks(){
		assertEquals(0, city.getCityId());
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

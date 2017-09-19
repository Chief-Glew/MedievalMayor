package com.fdmgroup.medievalmayor.game.command;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class AdminCommandTest {

	private AdminCommand adminCommand;
	private ResourceProducer resourceProducer;
	private CityFactory cityFactory;
	private City city;
	private ApplicationContext applicationContext;
	
	@Before
	public void init(){
		applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		adminCommand = new AdminCommand(); 
		resourceProducer = new Forest(5);
		cityFactory = applicationContext.getBean(CityFactory.class);
		city = cityFactory.getNewCity();

	}
	
	@Test
	public void testThatSetBaseResourceProductionForResourceForestCanBeSetToTen(){
		adminCommand.setBaseResourceProductionForResourceProducer(resourceProducer, 10);
		assertEquals(10, resourceProducer.getBaseResourceProduction());
	}
	
	@Test
	public void testThatTheAmountOfLumberPerWoodCanBeChangedToOne(){
		adminCommand.setAmmountOfLumberPerWoodForLumberMill((LumberMill) city.getResourceProducerOfType(LumberMill.class), 1);
		assertEquals(((LumberMill) city.getResourceProducerOfType(LumberMill.class)).getAmountOfLumberPerWood(), 1);
	} //TODO get rid of casting!!!

	@Test
	public void testThatUpgradeMultiplierChangesTheMultiplierOfAFarmToFive(){
		adminCommand.setUpgradeMultiplierForResourceProducer(city.getResourceProducerOfType(Farm.class), 5);
		assertEquals(city.getResourceProducerOfType(Farm.class).getUpgradeMultiplier(), 5);
	}
	
	@After
	public void closeSpring(){
		((ConfigurableApplicationContext)applicationContext).close();
	}
}

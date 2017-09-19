package com.fdmgroup.medievalmayor.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.command.UpgradeCommand;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.ResourceProducerUpgradeHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

public class ExceptionsTest {
	private City city;
	private CityFactory cityFactory;
	private int unassignedPopulation;
	private ResourceProducerService resourceProducerService;
	private ResourceProducer farm;
	private UpgradeCommand upgradeCommand;
	private Map<String,Integer> cost;
	private ClientCommand clientCommand;
	
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		cityFactory = applicationContext.getBean(CityFactory.class);
		resourceProducerService = applicationContext.getBean(ResourceProducerService.class);
		city = cityFactory.getNewCity(); 
		unassignedPopulation = city.getUnassignedPopulation();
		farm = city.getResourceProducerOfType(Farm.class);
		cost = new HashMap<String, Integer>();
		clientCommand = applicationContext.getBean(ClientCommand.class);
	}
	
	@Test(expected = AssignedNegativeNumberException.class)
	public void testThatAssignedNegativeNumberExeceptionIsThrown() throws InsufficientPopulationException, AssignedNegativeNumberException{
		resourceProducerService.assignPeopleToResourceProducer(-1, unassignedPopulation, farm);
	}
	
	@Test(expected = InsufficientPopulationException.class)
	public void testThatInsufficientPopulationExceptionIsThrown() throws InsufficientPopulationException, AssignedNegativeNumberException{
		resourceProducerService.assignPeopleToResourceProducer(11, unassignedPopulation, farm);
	}
	
	@Test(expected = InsufficientResourcesException.class)
	public void testThatInsufficientResourcesExceptionIsThrown(){
		clientCommand.upgradeResourceProducerInCity(city, farm, cost);
	}
	
}

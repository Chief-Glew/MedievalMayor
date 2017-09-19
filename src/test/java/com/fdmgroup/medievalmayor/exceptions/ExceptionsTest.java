package com.fdmgroup.medievalmayor.exceptions;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

public class ExceptionsTest {
	private City city;
	private CityFactory cityFactory;
	private int unassignedPopulation;
	private ResourceProducerService resourceProducerService;
	private Farm farm;
	
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		cityFactory = applicationContext.getBean(CityFactory.class);
		resourceProducerService = applicationContext.getBean(ResourceProducerService.class);
		city = cityFactory.getNewCity();
		unassignedPopulation = city.getUnassignedPopulation();
		farm = new Farm();
	}
	
	@Test(expected = AssignedNegativeNumberException.class)
	public void testThatAssignedNegativeNumberExeceptionIsThrown() throws InsufficentPopulationException, AssignedNegativeNumberException{
		resourceProducerService.assignPeopleToResourceProducer(-1, unassignedPopulation, farm);
	}
	
	@Test(expected = InsufficentPopulationException.class)
	public void testThatInsufficientPopulationExceptionIsThrown() throws InsufficentPopulationException, AssignedNegativeNumberException{
		resourceProducerService.assignPeopleToResourceProducer(11, unassignedPopulation, farm);
	}
	
}

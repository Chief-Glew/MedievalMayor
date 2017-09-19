package com.fdmgroup.medievalmayor.game.command;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.city.City;
import com.fdmgroup.medievalmayor.city.CityFactory;
import com.fdmgroup.medievalmayor.command.ClientCommand;
import com.fdmgroup.medievalmayor.command.CommandInvoker;
import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.resourceproducers.Mine;

public class ClientCommandTest {
	private ClientCommand clientCommand;
	private CommandInvoker commandInvoker;
	private City city;
	private ApplicationContext applicationContext;
	
	@Before
	public void init(){
		applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		CityFactory cityFactory = applicationContext.getBean(CityFactory.class);
		commandInvoker = new CommandInvoker();
		clientCommand = new ClientCommand(commandInvoker);
		city = cityFactory.getNewCity();
	}

	@Test(expected = GameOverException.class)
	public void testThatZeroPopulationThrowsGameOverException() throws GameOverException {
		city.setTotalPopulation(0);
		clientCommand.nextTurn(city);
	}
	
	@Test
	public void testThatWorkersCanBeSetForResourceBuildings(){
		clientCommand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceProducerOfType(Mine.class), 5);
		assertEquals(5, city.getResourceProducerOfType(Mine.class).getNumberOfAssignedWorkers());
	}
	
	@After
	public void closeSpring(){
		((ConfigurableApplicationContext)applicationContext).close();
	}
}

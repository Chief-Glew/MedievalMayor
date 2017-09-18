package com.fdmgroup.medievalmayor.game.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;

public class ClientCommandTest {
	private ClientCommand clientCommand;
	private CommandInvoker commandInvoker;
	private City city;
	
	@Before
	public void init(){
		commandInvoker = new CommandInvoker();
		clientCommand = new ClientCommand(commandInvoker);
		city = new CityFactory().getNewCity();
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
	
}

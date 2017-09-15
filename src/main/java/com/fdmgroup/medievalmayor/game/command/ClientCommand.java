package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class ClientCommand {

	private static final Logger logger = LogManager.getLogger("ClientCommand.class");
	
	private CommandInvoker commandInvoker;

	public ClientCommand() {
		this.commandInvoker = new CommandInvoker();
	}

	public void setNumberOfWorkersInResourceBuilding(ResourceProducer resourceBuilding, int numberOfWorkers){
		UserCommand setNumberOfWorkersCommand = new SetNumberOfWorkersCommand(resourceBuilding, numberOfWorkers);
		commandInvoker.setCommand(setNumberOfWorkersCommand);
		commandInvoker.invokeCommands();
		logger.trace("Number of workers in "+resourceBuilding+" set" );
	}

	public void nextTurn(City city) throws GameOverException {
		
		UserCommand nextTurn = new NextTurnCommand(city);
		commandInvoker.setCommand(nextTurn);
		commandInvoker.invokeCommands();
		logger.trace("next turn method used");
		if(city.getTotalPopulation()<=0){
			logger.info("Game ended due via GameOverException");
			throw new GameOverException("GameOver Man, GameOver");
		}
	}
	
	public void setNumberOfWorkersInResourceBuildingForCity(City city, ResourceProducer resourceBuilding, int numberOfPeopleToAssign){
		UserCommand setNumberOfWorkers = new SetNumberOfWorkersInBuildingFromCityCommand(city, resourceBuilding, numberOfPeopleToAssign);
		commandInvoker.setCommand(setNumberOfWorkers);
		commandInvoker.invokeCommands();
		logger.trace("setNumberOfWorkersInResourceBuildingForCity method used");
	}
}
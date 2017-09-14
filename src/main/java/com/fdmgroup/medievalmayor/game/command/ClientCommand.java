package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;

public class ClientCommand {

	private static final Logger logger = LogManager.getLogger("ClientCommand");
	
	private CommandInvoker commandInvoker;

	public ClientCommand() {
		this.commandInvoker = new CommandInvoker();
	}

	public void setNumberOfWorkersInResourceBuilding(ResourceBuilding resourceBuilding, int numberOfWorkers){
		UserCommand setNumberOfWorkersCommand = new SetNumberOfWorkersCommand(resourceBuilding, numberOfWorkers);
		commandInvoker.setCommand(setNumberOfWorkersCommand);
		commandInvoker.invokeCommands();
		logger.trace("Number of workers in "+resourceBuilding+" set" );
	}

	public void nextTurn(City city) throws GameOverException {

		UserCommand nextTurn = new NextTurnCommand(city);
		commandInvoker.setCommand(nextTurn);
		commandInvoker.invokeCommands();
		if(city.getTotalPopulation()<=0){
			throw new GameOverException("GameOver Man, GameOver");
		}
	}
	
	public void setNumberOfWorkersInResourceBuildingForCity(City city, ResourceBuilding resourceBuilding, int numberOfPeopleToAssign){
		UserCommand setNumberOfWorkers = new SetNumberOfWorkersInBuildingFromCityCommand(city, resourceBuilding, numberOfPeopleToAssign);
		commandInvoker.setCommand(setNumberOfWorkers);
		commandInvoker.invokeCommands();
	}
}
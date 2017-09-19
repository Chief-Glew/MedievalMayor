package com.fdmgroup.medievalmayor.game.command;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

@Component
public class ClientCommand {

	private static final Logger logger = LogManager.getLogger("ClientCommand.class");
	
	private CommandInvoker commandInvoker;

	@Autowired
	public ClientCommand(CommandInvoker commandInvoker) {
		this.commandInvoker = commandInvoker;
	}

	public void nextTurn(City city) throws GameOverException {
		
		UserCommand nextTurn = new NextTurnCommand(city);
		invoke(nextTurn);
		logger.trace("next turn method used");
		if(city.getTotalPopulation()<=0){
			logger.info("Game ended via GameOverException");
			throw new GameOverException("GameOver Man, GameOver");
		}
	}
	
	public void setNumberOfWorkersInResourceBuildingForCity(City city, ResourceProducer resourceBuilding, int numberOfPeopleToAssign){
		UserCommand setNumberOfWorkers = new SetNumberOfWorkersInBuildingFromCityCommand(city, resourceBuilding, numberOfPeopleToAssign);
		invoke(setNumberOfWorkers);
		logger.trace("setNumberOfWorkersInResourceBuildingForCity method used");
	}
	
	public void upgradeResourceProducerInCity(City city, ResourceProducer resourceProducer, Map<String, Integer> cost){
		UserCommand command = new UpgradeCommand( city,  resourceProducer,  cost);
		invoke(command);
	}

	private void invoke(UserCommand command) {
		commandInvoker.setCommand(command);
		commandInvoker.invokeCommands();
	}
}
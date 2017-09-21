package com.fdmgroup.medievalmayor.game.command.usercommands;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.CommandInvoker;
import com.fdmgroup.medievalmayor.game.command.NextTurnCommand;
import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.command.setcommands.SetNumberOfWorkersInBuildingFromCityCommand;
import com.fdmgroup.medievalmayor.game.command.upgradecommands.UpgradeCommand;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

@Component
public class ClientCommand {

	private static final Logger logger = LogManager.getLogger("ClientCommand.class");
	
	private CommandInvoker commandInvoker;

	@Autowired
	public ClientCommand(CommandInvoker commandInvoker) {
		this.commandInvoker = commandInvoker;
	}

	public void nextTurn(City city, List<String> events) throws GameOverException {
		UserCommand nextTurn = new NextTurnCommand(city, events);
		invoke(nextTurn);
		logger.info("NextTurn method used in ClientCommand class");
		if(city.getTotalPopulation()<=0){
			logger.debug("Game ended via GameOverException in ClientCommand class");
			throw new GameOverException("GameOver Man, GameOver");
		}
	}
	
	public void setNumberOfWorkersInResourceBuildingForCity(City city, ResourceProducer resourceBuilding, int numberOfPeopleToAssign){
		UserCommand setNumberOfWorkers = new SetNumberOfWorkersInBuildingFromCityCommand(city, resourceBuilding, numberOfPeopleToAssign);
		invoke(setNumberOfWorkers);
		logger.info("SetNumberOfWorkersInResourceBuildingForCity method used in ClientCommand class");
	}
	
	public void upgradeResourceProducerInCity(City city, ResourceProducer resourceProducer, Map<String, Integer> cost){
		logger.info("SetNumberOfWorkersInResourceBuildingForCity method used in ClientCommand class");
		UserCommand command = new UpgradeCommand( city,  resourceProducer,  cost);
		invoke(command);
	}

	private void invoke(UserCommand command) {
		logger.info("NextTurn method used in ClientCommand class");
		commandInvoker.setCommand(command);
		commandInvoker.invokeCommands();
	}
}
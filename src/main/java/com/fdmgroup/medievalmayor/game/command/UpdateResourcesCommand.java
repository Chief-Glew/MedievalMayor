package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.handlers.updateresourceshandlers.UpdateFoodHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.updateresourceshandlers.UpdateGoldHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.updateresourceshandlers.UpdateLumberHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.updateresourceshandlers.UpdateResourcesHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.updateresourceshandlers.UpdateWoodHandler;

public class UpdateResourcesCommand implements UserCommand{
	
	private static final Logger logger = LogManager.getLogger("UpdateResourcesCommand.class");

	private City city; 

	private UpdateResourcesHandler updateResourcesHandler;
	
	public UpdateResourcesCommand(City city) {
		this.city = city;
		updateResourcesHandler = new UpdateFoodHandler();
		updateResourcesHandler.addToChain(new UpdateGoldHandler());
		updateResourcesHandler.addToChain(new UpdateWoodHandler());
		updateResourcesHandler.addToChain(new UpdateLumberHandler());
	}
 
	public void execute() {
		updateResourcesHandler.handle(city);
		UserCommand updatePopulationCommand = new UpdatePopulationCommand(city);
		CommandInvoker commandInvoker = new CommandInvoker();
		
		commandInvoker.setCommand(updatePopulationCommand);
		commandInvoker.invokeCommands();
		logger.debug("Execute method used");
	}

}

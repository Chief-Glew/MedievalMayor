package com.fdmgroup.medievalmayor.game.command.updatecommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.CommandInvoker;
import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers.UpdateFoodHandler;
import com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers.UpdateGoldHandler;
import com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers.UpdateGuardHandler;
import com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers.UpdateLumberHandler;
import com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers.UpdateResourcesHandler;
import com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers.UpdateWoodHandler;

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
		updateResourcesHandler.addToChain(new UpdateGuardHandler());

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

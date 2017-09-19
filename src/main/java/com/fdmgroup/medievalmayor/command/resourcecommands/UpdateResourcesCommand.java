package com.fdmgroup.medievalmayor.command.resourcecommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.city.City;
import com.fdmgroup.medievalmayor.command.CommandInvoker;
import com.fdmgroup.medievalmayor.command.UpdatePopulationCommand;
import com.fdmgroup.medievalmayor.command.UserCommand;
import com.fdmgroup.medievalmayor.handlers.updateresourceshandlers.UpdateFoodHandler;
import com.fdmgroup.medievalmayor.handlers.updateresourceshandlers.UpdateGoldHandler;
import com.fdmgroup.medievalmayor.handlers.updateresourceshandlers.UpdateLumberHandler;
import com.fdmgroup.medievalmayor.handlers.updateresourceshandlers.UpdateResourcesHandler;
import com.fdmgroup.medievalmayor.handlers.updateresourceshandlers.UpdateWoodHandler;

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

package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.resourcecommands.UpdateResourcesCommand;

public class NextTurnCommand implements UserCommand {
	
	private static final Logger logger = LogManager.getLogger("CommandInvoker.class");
	
	private City city;
	
	
	public NextTurnCommand(City city) {
		this.city = city;
	}

	@Override
	public void execute() {
		UserCommand updateResourceCommand = new UpdateResourcesCommand(city);
		CommandInvoker commandInvoker = new CommandInvoker();
		commandInvoker.setCommand(updateResourceCommand);
		commandInvoker.invokeCommands();
		
		logger.debug("Execute method used");
	}

}

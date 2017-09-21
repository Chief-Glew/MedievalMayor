package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.updatecommands.UpdateWeatherCommand;

public class NextTurnCommand implements UserCommand {
	
	private static final Logger logger = LogManager.getLogger("NextTurnCommand.class");
	
	private City city;
	
	public NextTurnCommand(City city) {
		this.city = city; 
	}

	@Override
	public void execute() {
		logger.info("NextTurnCommand executed");
		UserCommand updateWeatherCommand = new UpdateWeatherCommand(city);
		CommandInvoker commandInvoker = new CommandInvoker();
		commandInvoker.setCommand(updateWeatherCommand);
		commandInvoker.invokeCommands();
		
		
	}
}

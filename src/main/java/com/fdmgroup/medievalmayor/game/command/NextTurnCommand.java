package com.fdmgroup.medievalmayor.game.command;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.updatecommands.UpdateWeatherCommand;

public class NextTurnCommand implements UserCommand {
	
	private static final Logger logger = LogManager.getLogger("NextTurnCommand.class");
	
	private City city;
	private List<String> events;
	
	public NextTurnCommand(City city, List<String> events) {
		this.city = city; 
		this.events = events;
	}

	@Override
	public void execute() {
		logger.info("NextTurnCommand executed");
		UserCommand updateWeatherCommand = new UpdateWeatherCommand(city, events);
		CommandInvoker commandInvoker = new CommandInvoker();
		commandInvoker.setCommand(updateWeatherCommand);
		commandInvoker.invokeCommands();
		
		
	}
}

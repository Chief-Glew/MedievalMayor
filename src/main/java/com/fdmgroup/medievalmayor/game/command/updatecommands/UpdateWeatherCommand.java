package com.fdmgroup.medievalmayor.game.command.updatecommands;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.CommandInvoker;
import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.BadWeatherHandler;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.GoodWeatherHandler;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.NormalWeatherHandler;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.UpdateWeatherHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;

public class UpdateWeatherCommand implements UserCommand{
	
	static final Logger logger = LogManager.getLogger("UpdateWeatherCommand.class");

	private City city;
	private UpdateWeatherHandler updateWeatherHandler;
	private List<String> events;
			
	public UpdateWeatherCommand(City city, List<String> events) {
		this.city = city;
		updateWeatherHandler = new GoodWeatherHandler();
		updateWeatherHandler.addToChain(new BadWeatherHandler());
		updateWeatherHandler.addToChain(new NormalWeatherHandler());
		this.events = events;
	}

	@Override
	public void execute() {
		logger.info("UpdateWeatherCommand executed");
		Farm farm = (Farm)city.getResourceProducerOfType(Farm.class);
		double weather = Math.random();
		double weatherMultiplier = updateWeatherHandler.handle(weather, events);
		farm.setWeatherMultiplier(weatherMultiplier);
		UserCommand updateResourceCommand = new UpdateResourcesCommand(city, events);
		CommandInvoker commandInvoker = new CommandInvoker();
		commandInvoker.setCommand(updateResourceCommand);
		commandInvoker.invokeCommands();
	}

}

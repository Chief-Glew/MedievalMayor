package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.resourcecommands.UpdateResourcesCommand;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.BadWeatherHandler;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.GoodWeatherHandler;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.NormalWeatherHandler;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.UpdateWeatherHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;

public class UpdateWeatherCommand implements UserCommand{
	
	static final Logger logger = LogManager.getLogger("UpdateWeatherCommand.class");

	private double weather = Math.random();
	private City city;
	private UpdateWeatherHandler updateWeatherHandler;
			
	public UpdateWeatherCommand(City city) {
		this.city = city;
		updateWeatherHandler = new GoodWeatherHandler();
		updateWeatherHandler.addToChain(new BadWeatherHandler());
		updateWeatherHandler.addToChain(new NormalWeatherHandler());
	}

	@Override
	public void execute() {
		Farm farm = (Farm)city.getResourceProducerOfType(Farm.class);
		double weather = Math.random();
		double weatherMultiplier = updateWeatherHandler.handle(weather);
		farm.setWeatherMultiplier(weatherMultiplier);
		UserCommand updateResourceCommand = new UpdateResourcesCommand(city);
		CommandInvoker commandInvoker = new CommandInvoker();
		commandInvoker.setCommand(updateResourceCommand);
		commandInvoker.invokeCommands();
	}

}

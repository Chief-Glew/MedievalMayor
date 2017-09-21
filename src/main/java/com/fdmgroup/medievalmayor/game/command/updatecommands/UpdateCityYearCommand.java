package com.fdmgroup.medievalmayor.game.command.updatecommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.UserCommand;

public class UpdateCityYearCommand implements UserCommand{
	
	static final Logger logger = LogManager.getLogger("UpdateCityYearCommand.class");

	
	private City city; 

	public UpdateCityYearCommand(City city) {
		this.city = city;
	}

	@Override
	public void execute() {
		logger.info("UpdateCityYear Command executed");
		int newCityYear = city.getCityYear()+1;
		city.setCityYear(newCityYear);
	}
}

package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;

public class UpdateCityYearCommand implements UserCommand{
	
	static final Logger logger = LogManager.getLogger("UpdateCityYearCommand.class");

	
	private City city; 

	public UpdateCityYearCommand(City city) {
		this.city = city;
	}

	@Override
	public void execute() {
		int newCityYear = city.getCityYear()+1;
		city.setCityYear(newCityYear);
	}
}

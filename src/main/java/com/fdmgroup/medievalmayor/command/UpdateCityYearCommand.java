package com.fdmgroup.medievalmayor.command;

import com.fdmgroup.medievalmayor.city.City;

public class UpdateCityYearCommand implements UserCommand{
	
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

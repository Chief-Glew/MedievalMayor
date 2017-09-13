package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.City;

public class UpdatePopulationCommand implements UserCommand {

	private City city;
	
	public UpdatePopulationCommand(City city) {
		this.city = city;
	}

	

	@Override
	public void execute() {
		
	}

}

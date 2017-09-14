package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.city.City;

public class NextTurnCommand implements UserCommand {
	
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
	}

}

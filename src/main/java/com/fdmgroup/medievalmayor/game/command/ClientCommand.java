package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;

public class ClientCommand {

	private CommandInvoker commandInvoker;

	public ClientCommand() {
		this.commandInvoker = new CommandInvoker();
	}

	public void setNumberOfWorkersOnResourceBuilding(ResourceBuilding resourceBuilding, int numberOfWorkers){
		UserCommand setNumberOfWorkersCommand = new SetNumberOfWorkersCommand(resourceBuilding, numberOfWorkers);
		commandInvoker.setCommand(setNumberOfWorkersCommand);
		commandInvoker.invokeCommands();
	}
}
package com.fdmgroup.medievalmayor.command;

import com.fdmgroup.medievalmayor.building.resourcebuilding.ResourceBuilding;

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

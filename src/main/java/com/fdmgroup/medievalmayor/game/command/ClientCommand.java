package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;

public class ClientCommand {

	private static final Logger logger = LogManager.getLogger("ClientCommand");
	
	private CommandInvoker commandInvoker;

	public ClientCommand() {
		this.commandInvoker = new CommandInvoker();
	}

	public void setNumberOfWorkersInResourceBuilding(ResourceBuilding resourceBuilding, int numberOfWorkers){
		UserCommand setNumberOfWorkersCommand = new SetNumberOfWorkersCommand(resourceBuilding, numberOfWorkers);
		commandInvoker.setCommand(setNumberOfWorkersCommand);
		commandInvoker.invokeCommands();
		logger.trace("Number of workers in "+resourceBuilding+" set" );
	}
}
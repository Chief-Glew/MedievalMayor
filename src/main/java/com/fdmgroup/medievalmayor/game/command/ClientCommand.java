package com.fdmgroup.medievalmayor.game.command;

<<<<<<< HEAD:src/main/java/com/fdmgroup/medievalmayor/command/ClientCommand.java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.building.resourcebuilding.ResourceBuilding;
=======
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
>>>>>>> commandMerger:src/main/java/com/fdmgroup/medievalmayor/game/command/ClientCommand.java

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
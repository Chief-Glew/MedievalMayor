package com.fdmgroup.medievalmayor.game.command;

<<<<<<< HEAD:src/main/java/com/fdmgroup/medievalmayor/command/SetNumberOfWorkersCommand.java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.building.resourcebuilding.ResourceBuilding;
=======
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
>>>>>>> commandMerger:src/main/java/com/fdmgroup/medievalmayor/game/command/SetNumberOfWorkersCommand.java

public class SetNumberOfWorkersCommand implements UserCommand {
	
	private static final Logger logger = LogManager.getLogger("SetNumberOfWorkersCommand");
	
	private ResourceBuilding resourceBuilding;
	private int numberOfWorkers;
	

	public SetNumberOfWorkersCommand(ResourceBuilding resourceBuilding, int numberOfWorkers) {
		this.resourceBuilding = resourceBuilding;
		this.numberOfWorkers = numberOfWorkers;
	}

	public void execute() {
		logger.trace("Number of workers set in "+resourceBuilding+": "+numberOfWorkers);
		resourceBuilding.setNumberOfPeopleInBuilding(numberOfWorkers);
	}
}

package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
public class SetNumberOfWorkersCommand implements UserCommand {
	
	private static final Logger logger = LogManager.getLogger("SetNumberOfWorkersCommand.class");
	
	private ResourceProducer resourceBuilding;
	private int numberOfWorkers;
	

	public SetNumberOfWorkersCommand(ResourceProducer resourceBuilding, int numberOfWorkers) {
		this.resourceBuilding = resourceBuilding;
		this.numberOfWorkers = numberOfWorkers;
	}

	public void execute() {
		logger.debug("Number of workers set in "+resourceBuilding+": "+numberOfWorkers);
		resourceBuilding.setNumberOfAssignedWorkers(numberOfWorkers);
	}
}

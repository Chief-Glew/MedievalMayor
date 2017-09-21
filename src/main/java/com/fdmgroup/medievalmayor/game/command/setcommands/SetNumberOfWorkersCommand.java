package com.fdmgroup.medievalmayor.game.command.setcommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
public class SetNumberOfWorkersCommand implements UserCommand {
	
	private static final Logger logger = LogManager.getLogger("SetNumberOfWorkersCommand.class");
	
	private ResourceProducer resourceProducer;
	private int numberOfWorkers;
	

	public SetNumberOfWorkersCommand(ResourceProducer resourceProducer, int numberOfWorkers) {
		this.resourceProducer = resourceProducer;
		this.numberOfWorkers = numberOfWorkers;
	}

	public void execute() {
		logger.info("SetNumberOfWorkers Command executed");
		resourceProducer.setNumberOfAssignedWorkers(numberOfWorkers);
	}
}
 
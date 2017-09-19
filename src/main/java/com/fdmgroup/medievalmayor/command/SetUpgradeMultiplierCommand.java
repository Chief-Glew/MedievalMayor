package com.fdmgroup.medievalmayor.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.resourceproducers.ResourceProducer;

public class SetUpgradeMultiplierCommand implements UserCommand {
	
	static final Logger logger = LogManager.getLogger("SetUpgradeMultiplierCommand.class");

	private ResourceProducer resourceProducer;
	private int upgradeMultiplier;

	public SetUpgradeMultiplierCommand(ResourceProducer resourceProducer, int upgradeMultiplier) {
		this.resourceProducer = resourceProducer;
		this.upgradeMultiplier = upgradeMultiplier;
	}

	@Override
	public void execute() {
		resourceProducer.setUpgradeMultiplier(upgradeMultiplier);
		logger.debug("Execute method used");
	}

}

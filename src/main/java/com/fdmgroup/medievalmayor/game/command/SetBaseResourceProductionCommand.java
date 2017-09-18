package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class SetBaseResourceProductionCommand implements UserCommand {
	
	static final Logger logger = LogManager.getLogger("UpdateLumberHandler.class");

	private ResourceProducer resourceProducer;
	private int baseResourceProduction;

	public SetBaseResourceProductionCommand(ResourceProducer resourceProducer, int baseResourceProduction) {
		this.resourceProducer = resourceProducer;
		this.baseResourceProduction = baseResourceProduction;
	}

	@Override
	public void execute() {
		resourceProducer.setBaseResourceProduction(baseResourceProduction);
	}

}

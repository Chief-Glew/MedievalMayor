package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class SetBaseResourceProductionCommand implements UserCommand {

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

package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class SetUpgradeMultiplierCommand implements UserCommand {

	private ResourceProducer resourceProducer;
	private int upgradeMultiplier;

	public SetUpgradeMultiplierCommand(ResourceProducer resourceProducer, int upgradeMultiplier) {
		this.resourceProducer = resourceProducer;
		this.upgradeMultiplier = upgradeMultiplier;
	}

	@Override
	public void execute() {
		resourceProducer.setUpgradeMultiplier(upgradeMultiplier);
	}

}

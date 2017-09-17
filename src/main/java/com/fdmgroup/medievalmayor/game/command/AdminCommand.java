package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class AdminCommand {

	private CommandInvoker commandInvoker;

	public AdminCommand() {
		this.commandInvoker = new CommandInvoker();
	}
	
	private void doIt(UserCommand command) {
		commandInvoker.setCommand(command);
		commandInvoker.invokeCommands();
	}
	
	public void setBaseResourceProductionForResourceProducer(ResourceProducer resourceProducer, int baseResourceProduction) {
		UserCommand command = new SetBaseResourceProductionCommand(resourceProducer, baseResourceProduction);
		doIt(command);
	}
	
	public void setUpgradeMultiplierForResourceProducer(ResourceProducer resourceProducer, int upgradeMultiplier) {
		UserCommand command = new SetUpgradeMultiplierCommand(resourceProducer, upgradeMultiplier);
		doIt(command);
	}
	
	public void setAmmountOfLumberPerWoodForLumberMill(LumberMill lumberMill, int ammountOfLumberPerWood) {
		UserCommand command = new setAmmountOfLumberPerWoodCommand(lumberMill, ammountOfLumberPerWood);
		doIt(command);
	}
}

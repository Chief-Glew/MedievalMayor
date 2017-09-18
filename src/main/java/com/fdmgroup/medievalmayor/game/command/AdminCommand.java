package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class AdminCommand {
	
	static final Logger logger = LogManager.getLogger("AdminCommand.class");

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

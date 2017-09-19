package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

@Component
public class AdminCommand {
	
	static final Logger logger = LogManager.getLogger("AdminCommand.class");

	private CommandInvoker commandInvoker;

	@Autowired
	public AdminCommand(CommandInvoker commandInvoker) {
		this.commandInvoker = commandInvoker;
	}
	
	private void setAndInvoke(UserCommand command) {
		commandInvoker.setCommand(command);
		commandInvoker.invokeCommands();
		logger.debug("doIt method used");
	}
	
	public void setBaseResourceProductionForResourceProducer(ResourceProducer resourceProducer, int baseResourceProduction) {
		UserCommand command = new SetBaseResourceProductionCommand(resourceProducer, baseResourceProduction);
		setAndInvoke(command);
		logger.debug("SetBaseResourceProductionForResourceProducer method used");
	}
	
	public void setUpgradeMultiplierForResourceProducer(ResourceProducer resourceProducer, int upgradeMultiplier) {
		UserCommand command = new SetUpgradeMultiplierCommand(resourceProducer, upgradeMultiplier);
		setAndInvoke(command);
		logger.debug("SetUpgradeMultiplierForResourceProducer method used");
	}
	
	public void setAmmountOfLumberPerWoodForLumberMill(LumberMill lumberMill, int ammountOfLumberPerWood) {
		UserCommand command = new setAmmountOfLumberPerWoodCommand(lumberMill, ammountOfLumberPerWood);
		setAndInvoke(command);
		logger.debug("SetAmmountOfLumberPerWoodForLumberMill method used");
	}
}

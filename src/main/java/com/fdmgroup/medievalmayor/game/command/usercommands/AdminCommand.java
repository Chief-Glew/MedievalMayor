package com.fdmgroup.medievalmayor.game.command.usercommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.command.CommandInvoker;
import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.command.setcommands.SetBaseResourceProductionCommand;
import com.fdmgroup.medievalmayor.game.command.setcommands.SetUpgradeMultiplierCommand;
import com.fdmgroup.medievalmayor.game.command.setcommands.SetAmmountOfLumberPerWoodCommand;
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
		logger.info("SetAndInvoke method used in AdminCommand class");
	}
	
	public void setBaseResourceProductionForResourceProducer(ResourceProducer resourceProducer, int baseResourceProduction) {
		UserCommand command = new SetBaseResourceProductionCommand(resourceProducer, baseResourceProduction);
		setAndInvoke(command);
		logger.info("SetBaseResourceProductionForResourceProducer method used in AdminCommand class");
	}
	
	public void setUpgradeMultiplierForResourceProducer(ResourceProducer resourceProducer, int upgradeMultiplier) {
		UserCommand command = new SetUpgradeMultiplierCommand(resourceProducer, upgradeMultiplier);
		setAndInvoke(command);
		logger.info("SetUpgradeMultiplierForResourceProducer method used in AdminCommand class");
	}
	
	public void setAmmountOfLumberPerWoodForLumberMill(LumberMill lumberMill, int ammountOfLumberPerWood) {
		UserCommand command = new SetAmmountOfLumberPerWoodCommand(lumberMill, ammountOfLumberPerWood);
		setAndInvoke(command);
		logger.info("SetAmmountOfLumberPerWoodForLumberMill method used in AdminCommand class");
	}
}

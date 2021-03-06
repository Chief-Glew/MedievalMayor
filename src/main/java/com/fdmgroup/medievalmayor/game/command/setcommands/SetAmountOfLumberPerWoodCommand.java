package com.fdmgroup.medievalmayor.game.command.setcommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;

public class SetAmountOfLumberPerWoodCommand implements UserCommand {
	
	static final Logger logger = LogManager.getLogger("SetAmountOfLumberPerWoodCommand.class");

	private LumberMill lumberMill;
	private int ammountOfLumberPerWood;

	public SetAmountOfLumberPerWoodCommand(LumberMill lumberMill, int ammountOfLumberPerWood) {
		this.lumberMill = lumberMill;
		this.ammountOfLumberPerWood = ammountOfLumberPerWood;
	}

	@Override
	public void execute() {
		lumberMill.setAmmountOfLumberPerWood(ammountOfLumberPerWood);
		logger.info("SetAmountOfLumberPerWood Command executed");
	}

}

package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;

public class setAmmountOfLumberPerWoodCommand implements UserCommand {
	
	static final Logger logger = LogManager.getLogger("UpdateLumberHandler.class");

	private LumberMill lumberMill;
	private int ammountOfLumberPerWood;

	public setAmmountOfLumberPerWoodCommand(LumberMill lumberMill, int ammountOfLumberPerWood) {
		this.lumberMill = lumberMill;
		this.ammountOfLumberPerWood = ammountOfLumberPerWood;
	}

	@Override
	public void execute() {
		lumberMill.setAmmountOfLumberPerWood(ammountOfLumberPerWood);
	}

}

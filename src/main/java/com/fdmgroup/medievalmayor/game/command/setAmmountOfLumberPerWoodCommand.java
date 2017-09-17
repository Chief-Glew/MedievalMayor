package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;

public class setAmmountOfLumberPerWoodCommand implements UserCommand {

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

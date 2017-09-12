package com.fdmgroup.medievalmayor.command;

import com.fdmgroup.medievalmayor.building.resourcebuilding.ResourceBuilding;

public class SetNumberOfWorkersCommand implements UserCommand {
	private ResourceBuilding resourceBuilding;
	private int numberOfWorkers;
	

	public SetNumberOfWorkersCommand(ResourceBuilding resourceBuilding, int numberOfWorkers) {
		this.resourceBuilding = resourceBuilding;
		this.numberOfWorkers = numberOfWorkers;
	}

	public void execute() {
		resourceBuilding.setNumberOfPeopleInBuilding(numberOfWorkers);
	}
}

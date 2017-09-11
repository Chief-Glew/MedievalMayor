package com.fdmgroup.medievalmayor.building.resourcebuilding;

import com.fdmgroup.medievalmayor.building.Building;

public abstract class ResourceBuilding extends Building{

	private int noOfAssignedWorkers;
	
	public abstract int produceResource();

	public int getNumberOfPeopleInBuilding() {
		return noOfAssignedWorkers;
	}

	public void setNumberOfPeopleInBuilding(int numberOfPeople) {
		noOfAssignedWorkers = numberOfPeople;
	}
}

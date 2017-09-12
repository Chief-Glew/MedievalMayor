package com.fdmgroup.medievalmayor.building.resourcebuilding;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fdmgroup.medievalmayor.building.Building;

@MappedSuperclass
public abstract class ResourceBuilding extends Building{
	@Column(name="NO_ASSIGNED_WORKERS")
	private int noOfAssignedWorkers;
	
	public abstract int produceResource();

	public int getNumberOfPeopleInBuilding() {
		return noOfAssignedWorkers;
	}

	public void setNumberOfPeopleInBuilding(int numberOfPeople) {
		noOfAssignedWorkers = numberOfPeople;
	}
}

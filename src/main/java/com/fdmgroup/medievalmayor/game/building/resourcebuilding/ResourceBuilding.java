package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fdmgroup.medievalmayor.game.building.Building;

@MappedSuperclass
public abstract class ResourceBuilding extends Building{
	@Column(name="NUMBER_ASSIGNED_WORKERS")
	private int numberOfAssignedWorkers;
	@Column(name="RESOURCE_MULTIPLIER")
	private int multiplier;
	
	public ResourceBuilding() {
	}

	public ResourceBuilding(int multiplier) {
		this.multiplier = multiplier;
	}

	public abstract int produceResource();
	
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public int getNoOfAssignedWorkers() {
		return numberOfAssignedWorkers;
	}

	public void setNumberOfPeopleInBuilding(int numberOfPeople) {
		numberOfAssignedWorkers = numberOfPeople;
	}
}

package com.fdmgroup.medievalmayor.building.resourcebuilding;

public class Farm extends ResourceBuilding{
	
	public Farm(int multiplier) {
		super(multiplier);
	}

	@Override
	public int produceResource() {
		int foodProduced = getNoOfAssignedWorkers()*getMultiplier();
		return foodProduced;
	}
}
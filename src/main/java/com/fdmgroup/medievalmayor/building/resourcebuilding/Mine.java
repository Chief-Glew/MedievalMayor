package com.fdmgroup.medievalmayor.building.resourcebuilding;

public class Mine extends ResourceBuilding{	
	
	public Mine(int multiplier){
		super(multiplier);
	}

	@Override
	public int produceResource() {
		int goldProduced = getNoOfAssignedWorkers()*getMultiplier();
		return goldProduced;
	}
	
}
 
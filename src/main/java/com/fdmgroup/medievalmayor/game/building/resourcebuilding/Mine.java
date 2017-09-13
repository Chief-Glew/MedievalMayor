package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

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
 
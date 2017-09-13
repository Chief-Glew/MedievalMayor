package com.fdmgroup.medievalmayor.building.resourcebuilding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mine extends ResourceBuilding{	
	
	@Autowired
	public Mine(int multiplier){
		super(multiplier);
	}

	@Override
	public int produceResource() {
		int goldProduced = getNoOfAssignedWorkers()*getMultiplier();
		return goldProduced;
	}
	
}
 
package com.fdmgroup.medievalmayor.building.resourcebuilding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Farm extends ResourceBuilding{
	
	@Autowired
	public Farm(int multiplier) {
		super(multiplier);
	}

	@Override
	public int produceResource() {
		int foodProduced = getNoOfAssignedWorkers()*getMultiplier();
		return foodProduced;
	}
}
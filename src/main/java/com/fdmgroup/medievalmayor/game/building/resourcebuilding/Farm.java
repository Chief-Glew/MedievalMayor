package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Farm extends ResourceBuilding{
	
	static final Logger logger = LogManager.getLogger("Farm");
	
	public Farm(int multiplier) {
		super(multiplier);
	}

	@Override
	public int produceResource() {
		int foodProduced = getNumberOfAssignedWorkers()*getMultiplier();
		logger.trace("Food Produced");
		return foodProduced;
	}
}
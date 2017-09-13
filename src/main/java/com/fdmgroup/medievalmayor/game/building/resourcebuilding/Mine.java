package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mine extends ResourceBuilding{	
	
	static final Logger logger = LogManager.getLogger("Mine");
	
	public Mine(int multiplier){
		super(multiplier);
	}

	@Override
	public int produceResource() {
		int goldProduced = getNumberOfAssignedWorkers()*getMultiplier();
		logger.trace("Gold Produced");
		return goldProduced;
	}
	
}
 
package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Forest extends ResourceBuilding{

	static final Logger logger = LogManager.getLogger("Forest");

	private long forestId;
	
	public Forest(int multiplier) {
		super(multiplier);
	}

	@Override
	public int produceResource() {
		int woodProduced = getNumberOfAssignedWorkers()*getMultiplier();
		logger.trace("Wood Produced");
		return woodProduced;
	}

	public long getForestId() {
		return forestId;
	}

}

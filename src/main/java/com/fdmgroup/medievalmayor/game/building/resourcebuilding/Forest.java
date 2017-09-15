package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources.Resource;

public class Forest extends ResourceBuilding{

	static final Logger logger = LogManager.getLogger("Forest");

	private long forestId;
	
	public Forest(int multiplier) {
		super(multiplier);
	}



	public long getForestId() {
		return forestId;
	}

	@Override
	public Resource produceResource() {
		return resourceFactory.getWood(getNumberOfAssignedWorkers()*getMultiplier());
	}
}

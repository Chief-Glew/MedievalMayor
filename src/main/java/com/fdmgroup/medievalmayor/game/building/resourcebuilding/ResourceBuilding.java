package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fdmgroup.medievalmayor.game.building.Building;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources.Resource;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources.ResourceFactory;

@MappedSuperclass
public abstract class ResourceBuilding extends Building{
	
	static final Logger logger = LogManager.getLogger("ResourceBuilding");
	
	@Column(name="NUMBER_ASSIGNED_WORKERS")
	private int numberOfAssignedWorkers;
	@Column(name="RESOURCE_MULTIPLIER")
	private int multiplier;
	
	protected ResourceFactory resourceFactory;
	
	public ResourceBuilding() {
		resourceFactory = new ResourceFactory();
	}

	public ResourceBuilding(int multiplier) {
		this.multiplier = multiplier;
	}

	public abstract int produceResource();
	
	public abstract Resource produceResourceNew();

	
	public void setMultiplier(int multiplier) {
		logger.trace("Multiplier set");
		this.multiplier = multiplier;
	}

	public int getMultiplier() {
		logger.trace("Multiplier retrieved");
		return multiplier;
	}

	public int getNumberOfAssignedWorkers() {
		logger.trace("Number of assigned Workers retrieved");
		return numberOfAssignedWorkers;
	}

	public void setNumberOfPeopleInBuilding(int numberOfPeople) {
		logger.trace("Number of assigned Workers set");
		numberOfAssignedWorkers = numberOfPeople;
	}
}

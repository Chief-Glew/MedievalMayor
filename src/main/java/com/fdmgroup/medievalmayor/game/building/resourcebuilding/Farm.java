package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.IdAble;

@Entity(name="FARM")
public class Farm extends ResourceBuilding implements IdAble{
	
	static final Logger logger = LogManager.getLogger("Farm");
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="FARM_ID")
	private long farmId;
	
	public Farm(){}
	
	public Farm(int multiplier) {
		super(multiplier);
	}

	@Override
	public int produceResource() {
		int foodProduced = getNumberOfAssignedWorkers()*getMultiplier();
		logger.trace("Food Produced");
		return foodProduced;
	}

	@Override
	public long getId() {
		return farmId;
	}
}
package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.IdAble;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources.Resource;

@Entity(name="MINE")
public class Mine extends ResourceBuilding implements IdAble{	
	
	static final Logger logger = LogManager.getLogger("Mine");
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MINE_ID")
	private long mineId;
	
	public Mine(){}
	
	public Mine(int multiplier){
		super(multiplier);
	}

	@Override
	public int produceResource() {
		int goldProduced = getNumberOfAssignedWorkers()*getMultiplier();
		logger.trace("Gold Produced");
		return goldProduced;
	}

	@Override
	public long getId() {
		return mineId;
	}
	
	@Override
	public Resource produceResourceNew() {
		return resourceFactory.getGold(getNumberOfAssignedWorkers()*getMultiplier());
	}
	
}
 
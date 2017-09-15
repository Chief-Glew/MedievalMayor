package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;

@Entity(name="FARM")
@DiscriminatorValue(value = "FARM")
public class Farm extends ResourceProducer{
	
	static final Logger logger = LogManager.getLogger("Farm");
	
	public Farm(){}
	
	public Farm(int multiplier) {
		super(multiplier);
	}

	@Override
	public Resource produceResource() {
		return resourceFactory.getFood(getNumberOfAssignedWorkers()*getMultiplier());
	}
}
package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;

@Entity(name="FOREST")
@DiscriminatorValue(value = "FOREST")
public class Forest extends ResourceProducer{

	static final Logger logger = LogManager.getLogger("Forest.class");
	
	public Forest(){}
	
	public Forest(int multiplier) {
		super(multiplier);
	}

	public Forest(int numberOfAssignedWorkers, int multiplier, int resourceProducerCost, String resourceProducerName) {
		super(numberOfAssignedWorkers, multiplier, resourceProducerCost, resourceProducerName);
	}

	@Override
	public Resource produceResource() {
		logger.trace("Wood produced");
		return resourceFactory.getWood(getNumberOfAssignedWorkers()*getMultiplier());
	}
}

package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;

@Entity(name="LUMBERMILL")
@DiscriminatorValue(value = "LUMBERMILL")
public class LumberMill extends ResourceProducer{

static final Logger logger = LogManager.getLogger("LumberMill");
	
	public LumberMill(){}
	
	public LumberMill(int multiplier){
		super(multiplier);
	}

	public LumberMill(int numberOfAssignedWorkers, int multiplier, int resourceProducerCost, String resourceProducerName, int producerLevel) {
		super(numberOfAssignedWorkers, multiplier, resourceProducerCost, resourceProducerName, producerLevel);
	}

	@Override
	public Resource produceResource() {
		return resourceFactory.getLumber(getNumberOfAssignedWorkers()*getMultiplier());
	}
}

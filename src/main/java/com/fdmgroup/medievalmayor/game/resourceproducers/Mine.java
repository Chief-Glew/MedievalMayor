package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;

@Entity(name="MINE")
@DiscriminatorValue(value = "MINE")
public class Mine extends ResourceProducer {	
	
	static final Logger logger = LogManager.getLogger("Mine");
	
	public Mine(){}
	
	public Mine(int multiplier){
		super(multiplier);
	}

	public Mine(int numberOfAssignedWorkers, int multiplier, int resourceProducerCost, String resourceProducerName) {
		super(numberOfAssignedWorkers, multiplier, resourceProducerCost, resourceProducerName);
	}

	@Override
	public Resource produceResource() {
		return resourceFactory.getGold(getNumberOfAssignedWorkers()*getMultiplier());
	}
}
 
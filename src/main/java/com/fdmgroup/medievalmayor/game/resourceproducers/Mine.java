package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;

@Entity(name="MINE")
@DiscriminatorValue(value = "MINE")
public class Mine extends ResourceProducer {	
	
	static final Logger logger = LogManager.getLogger("Mine.class");
	
	public Mine(){}
	
	public Mine(int multiplier){
		super(multiplier);
	}

	public Mine(int numberOfAssignedWorkers, int baseResourceProduction, int resourceProducerCost,
			String resourceProducerName, int producerLevel, int upgradeMultiplier) {
		super(numberOfAssignedWorkers, baseResourceProduction, resourceProducerCost, resourceProducerName, producerLevel,
				upgradeMultiplier);
	}

	@Override
	public Resource produceResource() {
		logger.trace("Gold produced");
		Double levelMultiplier = Double.valueOf(Math.pow(getUpgradeMultiplier(),getProducerLevel()));
		return resourceFactory.getGold(
				getNumberOfAssignedWorkers()*
				getBaseResourceProduction()*
				levelMultiplier.intValue()
				);
	}
}
 
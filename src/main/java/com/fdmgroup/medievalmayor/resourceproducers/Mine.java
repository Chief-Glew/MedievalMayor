package com.fdmgroup.medievalmayor.resourceproducers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.resources.Resource;

@Entity(name="MINE")
@DiscriminatorValue(value = "MINE")
public class Mine extends ResourceProducer {	
	
	static final Logger logger = LogManager.getLogger("Mine.class");
	
	public Mine(){}
	
	public Mine(int numberOfAssignedWorkers, int baseResourceProduction, int resourceProducerCost,
			String resourceProducerName, int producerLevel, int upgradeMultiplier) {
		super(numberOfAssignedWorkers, baseResourceProduction, resourceProducerCost, resourceProducerName, producerLevel,
				upgradeMultiplier);
	}

	@Override
	public Resource produceResource() {
		logger.debug("Gold produced");
		Double levelMultiplier = Double.valueOf(Math.pow(getUpgradeMultiplier(),getProducerLevel()));
		return resourceFactory.getGold(
				getNumberOfAssignedWorkers()*
				getBaseResourceProduction()*
				levelMultiplier.intValue()
				);
	}
}
 
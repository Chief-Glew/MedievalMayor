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
	
	public Forest(int baseResourceProduction) {
		super(baseResourceProduction);
	}

	

	public Forest(int numberOfAssignedWorkers, int baseResourceProduction, int resourceProducerCost,
			String resourceProducerName, int producerLevel, int upgradeMultiplier) {
		super(numberOfAssignedWorkers, baseResourceProduction, resourceProducerCost, resourceProducerName, producerLevel,
				upgradeMultiplier);
	}

	@Override
	public Resource produceResource() {
		logger.debug("Wood produced");
		Double levelMultiplier = Double.valueOf(Math.pow(getUpgradeMultiplier(),getProducerLevel()));
		return resourceFactory.getWood(
				getNumberOfAssignedWorkers()*
				getBaseResourceProduction()*
				levelMultiplier.intValue()
				);
	}
}

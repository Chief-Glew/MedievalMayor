package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resources.Resource;

@Entity(name="FARM")
@DiscriminatorValue(value = "FARM")
public class Farm extends ResourceProducer{

	static final Logger logger = LogManager.getLogger("Farm.class");

	private double weatherMultiplier;
	
	public Farm(){}

	public Farm(int numberOfAssignedWorkers, int baseResourceProduction, int resourceProducerCost,String resourceProducerName, int producerLevel, int upgradeMultiplier) {
		super(numberOfAssignedWorkers, baseResourceProduction, resourceProducerCost, resourceProducerName, producerLevel, upgradeMultiplier);
	}

	public void setWeatherMultiplier(double weatherMultiplier) {
		this.weatherMultiplier = weatherMultiplier;
	}

	@Override
	public Resource produceResource() {
		logger.debug("Food Produced");
		Double levelMultiplier = Double.valueOf(Math.pow(getUpgradeMultiplier(),getProducerLevel()));
		Double doubleFoodAmount = getNumberOfAssignedWorkers()*getBaseResourceProduction()*weatherMultiplier*levelMultiplier;
		return resourceFactory.getFood(doubleFoodAmount.intValue());
	}

	
}
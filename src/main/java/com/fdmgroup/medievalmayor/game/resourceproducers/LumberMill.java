package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;

@Entity(name="LUMBERMILL")
@DiscriminatorValue(value = "LUMBERMILL")
public class LumberMill extends ResourceProducer{

static final Logger logger = LogManager.getLogger("LumberMill");
@Column
private int ammountOfLumberPerWood;
	
	public LumberMill(){}
	
	public LumberMill(int multiplier){
		super(multiplier);
	}

	public LumberMill(int numberOfAssignedWorkers, int baseResourceProduction, int resourceProducerCost,
			String resourceProducerName, int producerLevel, int upgradeMultiplier, int ammountOfLumberPerWood) {
		super(numberOfAssignedWorkers, baseResourceProduction, resourceProducerCost, resourceProducerName, producerLevel,
				upgradeMultiplier);
		this.ammountOfLumberPerWood = ammountOfLumberPerWood;
	}

	@Override
	public Resource produceResource() {
		Double levelMultiplier = Double.valueOf(Math.pow(getUpgradeMultiplier(),getProducerLevel()));
		return resourceFactory.getLumber(
				getNumberOfAssignedWorkers()*
				getBaseResourceProduction()*
				levelMultiplier.intValue()
				);
	}

	public int getAmountOfLumberPerWood() {
		return ammountOfLumberPerWood;
	}

	public void setAmmountOfLumberPerWood(int ammountOfLumberPerWood) {
		this.ammountOfLumberPerWood = ammountOfLumberPerWood;
	}
	
}

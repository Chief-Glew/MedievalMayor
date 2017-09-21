package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.Entity;

import com.fdmgroup.medievalmayor.game.resources.Resource;

@Entity
public class GuardHouse extends ResourceProducer{
	
	private double guardEffectiveness = 0.1;

	public GuardHouse() {}
	
	public GuardHouse(int numberOfAssignedWorkers, int baseResourceProduction, int resourceProducerCost,
			String resourceProducerName, int producerLevel, int upgradeMultiplier) {
		super(numberOfAssignedWorkers, baseResourceProduction, resourceProducerCost, resourceProducerName, producerLevel,
				upgradeMultiplier);
	}

	public GuardHouse(int baseResourceProduction, int upgradeMultiplier) {
		super(baseResourceProduction, upgradeMultiplier);
	}

	public GuardHouse(int baseResourceProduction) {
		super(baseResourceProduction);
	}

	@Override
	public Resource produceResource() {
		return resourceFactory.getFood(-getNumberOfAssignedWorkers());
	}

	public int getBanditsKilled(){
		Double doubleBanditsKilled = (1+(getProducerLevel()*guardEffectiveness))*getNumberOfAssignedWorkers();
		return doubleBanditsKilled.intValue();
	}
}

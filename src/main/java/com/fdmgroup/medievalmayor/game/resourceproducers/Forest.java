package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;

@Entity(name="FOREST")
@DiscriminatorValue(value = "FOREST")
public class Forest extends ResourceProducer{

	static final Logger logger = LogManager.getLogger("Forest");
	
	public Forest(){}
	
	public Forest(int multiplier) {
		super(multiplier);
	}

	@Override
	public Resource produceResource() {
		return resourceFactory.getWood(getNumberOfAssignedWorkers()*getMultiplier());
	}
}

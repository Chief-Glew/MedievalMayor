package com.fdmgroup.medievalmayor.game.resourceproducers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Component;

@Component
public class ResourceProducerFactory {
	
	static final Logger logger = LogManager.getLogger("ResourceProducerFactory.class");

	public ResourceProducer getFarm(){
		logger.debug("Farm retrieved");
		return new Farm(0, 3, 0, "Farm", 0, 3);
	}
	
	public ResourceProducer getMine(){
		logger.debug("Mine retrieved");
		return new Mine(0, 2, 0, "Mine", 0,3 );
	}
	
	public ResourceProducer getForest(){
		logger.debug("Forest retrieved");
		return new Forest(0, 1, 0, "Forest", 0, 3);
	}
	
	public ResourceProducer getLumberMill(){
		logger.debug("Lumber Mill retrieved");
		return new LumberMill(0, 4, 0, "Lumber Mill", 0, 3, 4);
	}
	
	public ResourceProducer getGuardHouse() {
		return new GuardHouse(0, 1, 0, "Guard House", 0, 3);
	}
}
package com.fdmgroup.medievalmayor.game.resourceproducers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		logger.debug("LumberMill retrieved");
		return new LumberMill(0, 4, 0, "LumberMill", 0, 3, 4);
	}
}
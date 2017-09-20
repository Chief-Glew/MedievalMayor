package com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class LumberMillStringHandler extends ResourceProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("LumberMillStringHandler.class");
	
	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("Lumber Mill")) {
			logger.debug("LumberMill class returned");
			return LumberMill.class;
		}
		else {
			logger.debug("Next.handle method used");
			return next.handle(producerName);
		}
	}
}

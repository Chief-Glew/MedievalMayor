package com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class ForestStringHandler extends ResourceProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("ForestStringHandler.class");

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("Forest")) {
			logger.info("Forest Class returned in FarmStringHandler class");
			return Forest.class;
		}
		else {
			logger.info("Next.handle method used");
			return next.handle(producerName);
		}
	}
}

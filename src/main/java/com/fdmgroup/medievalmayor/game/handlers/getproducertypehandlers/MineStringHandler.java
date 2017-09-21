package com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class MineStringHandler extends ResourceProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("MineStringHandler.class");

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("Mine")) {
			logger.info("Mine Class returned in FarmStringHandler class");
			return Mine.class;
		}
		else {
			logger.info("Next.handle method used");
			return next.handle(producerName);
		}
	}
}

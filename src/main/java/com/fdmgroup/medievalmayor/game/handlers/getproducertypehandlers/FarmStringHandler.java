package com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class FarmStringHandler extends ResourceProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("FarmStringHandler.class");

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("Farm")) {
			logger.info("Farm Class returned in FarmStringHandler class");
			return Farm.class;
		}
		else {
			logger.info("Next.Handle method used");
			return next.handle(producerName);
		}
	}
}

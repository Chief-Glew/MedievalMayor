package com.fdmgroup.medievalmayor.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.resourceproducers.ResourceProducer;

public class MineStringHandler extends ResourceProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("MineStringHandler.class");

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("Mine")) {
			logger.debug("Mine Class returned");
			return Mine.class;
		}
		else {
			logger.debug("Next.handle method used");
			return next.handle(producerName);
		}
	}
}

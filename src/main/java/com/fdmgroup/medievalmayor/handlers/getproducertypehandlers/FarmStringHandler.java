package com.fdmgroup.medievalmayor.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.resourceproducers.ResourceProducer;

public class FarmStringHandler extends ResourceProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("FarmStringHandler.class");

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("Farm")) {
			logger.debug("Farm Class returned");
			return Farm.class;
		}
		else {
			logger.debug("Next.Handle method used");
			return next.handle(producerName);
		}
	}
}

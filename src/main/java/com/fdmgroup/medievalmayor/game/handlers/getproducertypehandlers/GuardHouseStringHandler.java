package com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers;

import com.fdmgroup.medievalmayor.game.resourceproducers.GuardHouse;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class GuardHouseStringHandler extends ResourceProducerClassFromStringHandler{

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException {
		if (producerName.equals("Guard House")) {
			logger.info("Guard House Class returned in FarmStringHandler class");
			return GuardHouse.class;
		}
		else {
			logger.info("Next.Handle method used");
			return next.handle(producerName);
		}
	}

}

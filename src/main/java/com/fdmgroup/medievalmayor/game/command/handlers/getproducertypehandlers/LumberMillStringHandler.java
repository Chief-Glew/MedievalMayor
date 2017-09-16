package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class LumberMillStringHandler extends ProducerClassFromStringHandler {
	
	@Override
	public Class<? extends ResourceProducer> handle(String producerName) {
		if (producerName.equals("LumberMill")) {
			return LumberMill.class;
		}
		else {
			return next.handle(producerName);
		}
	}
}

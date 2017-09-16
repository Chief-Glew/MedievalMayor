package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class ForestStringHandler extends ProducerClassFromStringHandler {

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) {
		if (producerName.equals("Forest")) {
			return Forest.class;
		}
		else {
			return next.handle(producerName);
		}
	}
}

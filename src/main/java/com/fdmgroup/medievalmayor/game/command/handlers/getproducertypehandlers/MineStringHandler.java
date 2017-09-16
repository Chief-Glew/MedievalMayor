package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class MineStringHandler extends ProducerClassFromStringHandler {

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) {
		if (producerName.equals("Mine")) {
			return Mine.class;
		}
		else {
			return next.handle(producerName);
		}
	}
}

package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class FarmStringHandler extends ProducerClassFromStringHandler {

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("Farm")) {
			return Farm.class;
		}
		else {
			return next.handle(producerName);
		}
	}

}

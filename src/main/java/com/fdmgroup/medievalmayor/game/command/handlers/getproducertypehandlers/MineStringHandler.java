package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class MineStringHandler extends ProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("MineStringHandler.class");

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("Mine")) {
			return Mine.class;
		}
		else {
			return next.handle(producerName);
		}
	}
}

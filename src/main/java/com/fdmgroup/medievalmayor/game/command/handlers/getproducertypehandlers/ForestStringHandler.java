package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class ForestStringHandler extends ProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("ForestStringHandler.class");

	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("Forest")) {
			return Forest.class;
		}
		else {
			return next.handle(producerName);
		}
	}
}

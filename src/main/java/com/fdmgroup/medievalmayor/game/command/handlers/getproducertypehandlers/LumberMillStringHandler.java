package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class LumberMillStringHandler extends ProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("LumberStringHandler.class");
	
	@Override
	public Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException{
		if (producerName.equals("LumberMill")) {
			return LumberMill.class;
		}
		else {
			return next.handle(producerName);
		}
	}
}

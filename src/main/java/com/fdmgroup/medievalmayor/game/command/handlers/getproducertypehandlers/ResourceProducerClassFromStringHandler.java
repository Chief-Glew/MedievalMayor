package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public abstract class ResourceProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("ResourceProducerClassFromStringHandler.class");

	protected ResourceProducerClassFromStringHandler next;
	
	public ResourceProducerClassFromStringHandler() {
	}
	
	public void addToChain(ResourceProducerClassFromStringHandler handler) {
		if (isNextNull()) {
			next = handler;
		}
		else {
			next.addToChain(handler);
		}
		logger.debug("AddToChain method used");
	}
	
	protected boolean isNextNull() {
		return next==null;
	}
	
	public abstract Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException;
}

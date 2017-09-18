package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public abstract class ProducerClassFromStringHandler {
	
	static final Logger logger = LogManager.getLogger("ProducerStringHandler.class");

	protected ProducerClassFromStringHandler next;
	
	public ProducerClassFromStringHandler() {
	}
	
	public void addToChain(ProducerClassFromStringHandler handler) {
		if (isNextNull()) {
			next = handler;
		}
		else {
			next.addToChain(handler);
		}
	}
	
	protected boolean isNextNull() {
		return next==null;
	}
	
	public abstract Class<? extends ResourceProducer> handle(String producerName) throws NullPointerException;
}

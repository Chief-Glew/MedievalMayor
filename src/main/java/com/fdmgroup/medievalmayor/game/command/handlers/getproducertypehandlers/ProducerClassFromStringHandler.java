package com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers;

import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public abstract class ProducerClassFromStringHandler {

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
	
	public abstract Class<? extends ResourceProducer> handle(String producerName);
}

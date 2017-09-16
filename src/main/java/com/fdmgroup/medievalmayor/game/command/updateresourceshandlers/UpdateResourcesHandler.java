package com.fdmgroup.medievalmayor.game.command.updateresourceshandlers;

import java.util.Set;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceFactory;

public abstract class UpdateResourcesHandler {

	protected UpdateResourcesHandler next;
	protected ResourceFactory resourceFactory;
	
	public UpdateResourcesHandler() {
		resourceFactory = new ResourceFactory();
	}
	
	public void addToChain(UpdateResourcesHandler handler) {
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
	
	public abstract void handle(City city);
}

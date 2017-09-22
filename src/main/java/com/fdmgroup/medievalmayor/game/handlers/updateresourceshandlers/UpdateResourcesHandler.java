package com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

public abstract class UpdateResourcesHandler {//TODO add events for food generation
	
	static final Logger logger = LogManager.getLogger("UpdateResourceHandler.class");

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
		logger.debug("AddToChain method used");
	}
	
	protected boolean isNextNull() {
		logger.debug("IsNextNull method used");
		return next==null;
	}
	
	protected void handleNext(City city, List<String> events) {
		if (!isNextNull()) {
			next.handle(city, events);
		}
	}
	
	public abstract void handle(City city, List<String> events);
}

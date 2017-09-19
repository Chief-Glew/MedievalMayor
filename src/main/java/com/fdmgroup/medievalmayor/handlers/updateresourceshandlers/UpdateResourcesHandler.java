package com.fdmgroup.medievalmayor.handlers.updateresourceshandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.city.City;
import com.fdmgroup.medievalmayor.resources.ResourceFactory;

public abstract class UpdateResourcesHandler {
	
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
	
	public abstract void handle(City city);
}

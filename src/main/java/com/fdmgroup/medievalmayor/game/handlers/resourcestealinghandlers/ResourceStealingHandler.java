package com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;

public abstract class ResourceStealingHandler {

	static final Logger logger = LogManager.getLogger("RandomEventHandler.class");

	protected ResourceStealingHandler next;
	
	public void addToChain(ResourceStealingHandler handler) {
		if (next==null) {
			next = handler;
		}
		else {
			next.addToChain(handler); 
		}
		logger.debug("addToChain method used");
	}
	
	protected boolean isNextNull() {
		logger.debug("IsNextNull method used");
		return next==null;
	}
	
	public abstract List<String> handle(City city, List<String> events);
}

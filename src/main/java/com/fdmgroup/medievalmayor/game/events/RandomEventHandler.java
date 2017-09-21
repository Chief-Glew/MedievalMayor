package com.fdmgroup.medievalmayor.game.events;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;

public abstract class RandomEventHandler {
	
	static final Logger logger = LogManager.getLogger("RandomEventHandler.class");

	protected RandomEventHandler next;
	
	public void addToChain(RandomEventHandler handler) {
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

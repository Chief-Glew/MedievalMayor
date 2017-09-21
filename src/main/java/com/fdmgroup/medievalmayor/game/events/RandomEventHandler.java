package com.fdmgroup.medievalmayor.game.events;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;


public abstract class RandomEventHandler {
	
	static final Logger logger = LogManager.getLogger("RandomEventHandler.class");

	protected RandomEventHandler next;
	
	public void addToChain(RandomEventHandler handler) {
		logger.info("AddToChain method used in RandomEventHandler");
		if (next==null) {
			next = handler;
		}
		else {
			next.addToChain(handler); 
		}
	}
	
	protected boolean isNextNull() {
		logger.info("IsNextNull method used in RandomEventHandler");
		return next==null;
	}
	
	public abstract List<String> handle(City city, List<String> events);
}

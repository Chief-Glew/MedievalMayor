package com.fdmgroup.medievalmayor.game.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ResourceFactory {

	private static final Logger logger = LogManager.getLogger("ResourceFactory.class");
	
	public Resource getGold(int ammount) {
		logger.debug("Gold retrieved");
		return new Resource(ammount, "Gold");
	}
	
	public Resource getFood(int ammount) {
		logger.debug("Food retrieved");
		return new Resource(ammount, "Food");
	}
	
	public Resource getWood(int ammount) {
		logger.debug("Woood retrieved");
		return new Resource(ammount, "Wood");
	}
	
	public Resource getLumber(int ammount) {
		logger.debug("Lumber retrieved");
		return new Resource(ammount, "Lumber");
	}
	
	public Resource getPopulation(int ammount){
		logger.debug("Population retrieved");
		return new Resource(ammount, "Population");
	}
}

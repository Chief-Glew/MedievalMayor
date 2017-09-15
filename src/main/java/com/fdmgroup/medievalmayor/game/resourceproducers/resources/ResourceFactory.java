package com.fdmgroup.medievalmayor.game.resourceproducers.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceFactory {

	private static final Logger logger = LogManager.getLogger("ResourceFactory.class");
	
	public Resource getGold(int ammount) {
		logger.trace("Gold retrieved");
		return new Resource(ammount, "Gold");
	}
	
	public Resource getFood(int ammount) {
		logger.trace("Food retrieved");
		return new Resource(ammount, "Food");
	}
	
	public Resource getWood(int ammount) {
		logger.trace("Woood retrieved");
		return new Resource(ammount, "Wood");
	}
	
	public Resource getLumber(int ammount) {
		logger.trace("Lumber retrieved");
		return new Resource(ammount, "Lumber");
	}
	
	public Resource getPopulation(int ammount){
		logger.trace("Population retrieved");
		return new Resource(ammount, "Population");
	}
}

package com.fdmgroup.medievalmayor.game.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceStorageFactory {
	
	private static final Logger logger = LogManager.getLogger("ResourceStorageFactory.class");

	public ResourceStorageHandler getGoldStorage(int capacity) {
		logger.debug("Gold storage retrieved");
		return new ResourceStorageHandler(0, "Gold", capacity);
	}
	
	public ResourceStorageHandler getFoodStorage(int capacity) {
		logger.debug("Food storage retrieved");
		return new ResourceStorageHandler(0, "Food", capacity);
	}
	
	public ResourceStorageHandler getWoodStorage(int capacity) {
		logger.debug("Wood storage retrieved");
		return new ResourceStorageHandler(0, "Wood", capacity);
	}
	
	public ResourceStorageHandler getLumberStorage(int capacity) {
		logger.debug("Lumber storage retrieved");
		return new ResourceStorageHandler(0, "Lumber", capacity);
	}
	
	public ResourceStorageHandler getPopulationStorage(int capacity, int ammount) {
		logger.debug("Population storage retrieved");
		return new ResourceStorageHandler(ammount, "Population", capacity);
	}
	
	public ResourceStorageHandler getStorageForResource(Resource resource) {
		logger.debug("getStorageForResource method used");
		return new ResourceStorageHandler(0, resource.getResourceType(), 0);
	}
	
	
}

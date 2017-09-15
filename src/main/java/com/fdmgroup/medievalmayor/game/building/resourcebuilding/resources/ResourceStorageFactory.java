package com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources;

public class ResourceStorageFactory {

	public ResourceStorageHandler getGoldStorage(int capacity) {
		return new ResourceStorageHandler(0, "Gold", capacity);
	}
	
	public ResourceStorageHandler getFoodStorage(int capacity) {
		return new ResourceStorageHandler(0, "Food", capacity);
	}
	
	public ResourceStorageHandler getWoodStorage(int capacity) {
		return new ResourceStorageHandler(0, "Wood", capacity);
	}
	
	public ResourceStorageHandler getLumberStorage(int capacity) {
		return new ResourceStorageHandler(0, "Lumber", capacity);
	}
	
	public ResourceStorageHandler getPopulationStorage(int capacity, int ammount) {
		return new ResourceStorageHandler(ammount, "Population", capacity);
	}
	
	public ResourceStorageHandler getStorageForResource(Resource resource) {
		return new ResourceStorageHandler(0, resource.getResourceType(), 0);
	}
	
	
}

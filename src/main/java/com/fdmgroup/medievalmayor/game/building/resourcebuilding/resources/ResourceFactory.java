package com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources;

public class ResourceFactory {

	public Resource getGold(int ammount) {
		return new Resource(ammount, "Gold");
	}
	
	public Resource getFood(int ammount) {
		return new Resource(ammount, "Food");
	}
	
	public Resource getWood(int ammount) {
		return new Resource(ammount, "Wood");
	}
	
	public Resource getLumber(int ammount) {
		return new Resource(ammount, "Lumber");
	}
	
	public Resource getPopulation(int ammount){
		return new Resource(ammount, "Population");
	}
}

package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuildingService;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public class SetNumberOfWorkersInBuildingFromCityCommand implements UserCommand{

	private City city;
	private ResourceBuilding resourceBuilding;
	private int numberOfPeopleToAssign;
	private ResourceBuildingService resourceBuildingService;

	public SetNumberOfWorkersInBuildingFromCityCommand(
			City city, ResourceBuilding resourceBuilding, int numberOfPeopleToAssign){
		this.city = city;
		this.resourceBuilding = resourceBuilding;
		this.numberOfPeopleToAssign = numberOfPeopleToAssign;
		resourceBuildingService = new ResourceBuildingService();
	}
	
	@Override
	public void execute() {
		int unassignedPeople = city.getUnassignedPopulation();
		int newUnassignedPeople = unassignedPeople;
		try {
			newUnassignedPeople = resourceBuildingService.assignPeopleToBuilding(numberOfPeopleToAssign, unassignedPeople, resourceBuilding);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			e.printStackTrace();
		}
		city.setUnassignedPopulation(newUnassignedPeople);
	}

}

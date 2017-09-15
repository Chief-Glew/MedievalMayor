package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

public class SetNumberOfWorkersInBuildingFromCityCommand implements UserCommand{

	private City city;
	private ResourceProducer resourceBuilding;
	private int numberOfPeopleToAssign;
	private ResourceProducerService resourceBuildingService;

	public SetNumberOfWorkersInBuildingFromCityCommand(
			City city, ResourceProducer resourceBuilding, int numberOfPeopleToAssign){
		this.city = city;
		this.resourceBuilding = resourceBuilding;
		this.numberOfPeopleToAssign = numberOfPeopleToAssign;
		resourceBuildingService = new ResourceProducerService();
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
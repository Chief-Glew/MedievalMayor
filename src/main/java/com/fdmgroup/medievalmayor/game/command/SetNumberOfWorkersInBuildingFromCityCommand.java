package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

public class SetNumberOfWorkersInBuildingFromCityCommand implements UserCommand{
	
	private static final Logger logger = LogManager.getLogger("SetNumberOfWorkersInBuildingFromCityCommand.class");

	private City city;
	private ResourceProducer resourceBuilding;
	private int numberOfPeopleToAssign;
	private ResourceProducerService resourceBuildingService;

	public SetNumberOfWorkersInBuildingFromCityCommand(City city, ResourceProducer resourceBuilding, int numberOfPeopleToAssign){
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
			logger.debug("newUnassignedPeople set");
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			e.printStackTrace();
			logger.debug("Exception");
		}
		city.setUnassignedPopulation(newUnassignedPeople);
		logger.debug("Execute method used");
	}

}

package com.fdmgroup.medievalmayor.game.resourceproducers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;

public class ResourceProducerService {

	static final Logger logger = LogManager.getLogger("City.class");
	
	public int assignPeopleToBuilding(int numberOfPeopleToAssign, int numberOfPeopleAvailible, ResourceProducer building) throws InsufficentPopulationException, AssignedNegativeNumberException {
		if(numberOfPeopleToAssign < 0){
			throw new AssignedNegativeNumberException("Cannot assign a negative number of people to a building");
		}
		int currentPeople = building.getNumberOfAssignedWorkers();
		int difference = numberOfPeopleToAssign - currentPeople;
		if(difference > numberOfPeopleAvailible){
			throw new InsufficentPopulationException("Insufficent population available");
		}
		building.setNumberOfAssignedWorkers(numberOfPeopleToAssign);
		logger.debug("People assigned to building");
		return (numberOfPeopleAvailible - difference);
	}

	public int getPeopleInBuilding(ResourceProducer building){
		logger.trace("Number of assigned workers in "+building+" retrieved");
		return building.getNumberOfAssignedWorkers();
	}
	
	public Resource getResourceForBuilding(ResourceProducer building) {
		return building.produceResource();
	}
}

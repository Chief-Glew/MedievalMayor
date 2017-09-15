package com.fdmgroup.medievalmayor.game.building.resourcebuilding;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public class ResourceBuildingService {

	static final Logger logger = LogManager.getLogger("City");
	
	public int assignPeopleToBuilding(int numberOfPeopleToAssign, int numberOfPeopleAvailible, ResourceBuilding building) throws InsufficentPopulationException, AssignedNegativeNumberException {
		if(numberOfPeopleToAssign < 0){
			throw new AssignedNegativeNumberException("Cannot assign a negative number of people to a building");
		}
		int currentPeople = building.getNumberOfAssignedWorkers();
		int difference = numberOfPeopleToAssign - currentPeople;
		if(difference > numberOfPeopleAvailible){
			throw new InsufficentPopulationException("Insufficent population available");
		}
		building.setNumberOfPeopleInBuilding(numberOfPeopleToAssign);
		logger.debug("People assigned to building");
		return (numberOfPeopleAvailible - difference);
	}

	public int getPeopleInBuilding(ResourceBuilding building){
		logger.trace("Number of assigned workers in "+building+" retrieved");
		return building.getNumberOfAssignedWorkers();
	}
	
	public int getResourceForBuilding(ResourceBuilding building) {
		return building.produceResource();
	}
}

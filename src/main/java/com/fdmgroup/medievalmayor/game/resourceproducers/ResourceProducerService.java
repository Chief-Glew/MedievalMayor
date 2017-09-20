package com.fdmgroup.medievalmayor.game.resourceproducers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficientPopulationException;
import com.fdmgroup.medievalmayor.game.resources.Resource;

@Service
public class ResourceProducerService {

	static final Logger logger = LogManager.getLogger("ResourceProducerService.class");
	
	public int assignPeopleToResourceProducer(int numberOfPeopleToAssign, int numberOfPeopleAvailible, ResourceProducer building) throws InsufficientPopulationException, AssignedNegativeNumberException {
		if(numberOfPeopleToAssign < 0){
			throw new AssignedNegativeNumberException("Cannot assign a negative number of people to a building");
		}
		int currentPeople = building.getNumberOfAssignedWorkers();
		int difference = numberOfPeopleToAssign - currentPeople;
		if(difference > numberOfPeopleAvailible){
			throw new InsufficientPopulationException("Insufficent population available");
		}
		building.setNumberOfAssignedWorkers(numberOfPeopleToAssign);
		logger.debug("People assigned to building"); 
		return (numberOfPeopleAvailible - difference);
	}

	public int getPeopleInResourceProducer(ResourceProducer resourceProducer){
		logger.debug("Number of assigned workers in "+resourceProducer+" retrieved");
		return resourceProducer.getNumberOfAssignedWorkers();
	}
	
	public Resource getResourceFromResourceProducer(ResourceProducer resourceProducer) {
		return resourceProducer.produceResource();
	}
}

package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class GuardKiller extends PeopleKillingHandler {

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer guardHouse = city.getResourceProducerOfType(Mine.class);
		int numberOfAssignedWorkers = guardHouse.getNumberOfAssignedWorkers();
		boolean guardHouseHasPeople = numberOfAssignedWorkers!=0;
		
		if (guardHouseHasPeople&&isRandomNumberLessThanPointTwo()){
			guardHouse.setNumberOfAssignedWorkers(numberOfAssignedWorkers-1);;
			city.setTotalPopulation(city.getTotalPopulation()-1);
			events.add("Bandits killed a Guard");
		}
		else{
			handleNext(city, events);
		}			
	}

}

package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class FarmersKiller extends PeopleKillingHandler {

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer farm = city.getResourceProducerOfType(Farm.class);
		int numberOfAssignedWorkers = farm.getNumberOfAssignedWorkers();
		boolean farmHasPeople = numberOfAssignedWorkers!=0;
		
		if (farmHasPeople&&isRandomNumberLessThanPointTwo()){
			farm.setNumberOfAssignedWorkers(numberOfAssignedWorkers-1);;
			city.setTotalPopulation(city.getTotalPopulation()-1);
			events.add("Bandits killed a Farmer");
		}
		else{
			handleNext(city, events);
		}
		
	}

	

}

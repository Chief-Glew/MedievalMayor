package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class CarpenterKiller extends PeopleKillingHandler{

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer lumberMill = city.getResourceProducerOfType(Mine.class);
		int numberOfAssignedWorkers = lumberMill.getNumberOfAssignedWorkers();
		boolean lumberMillHasPeople = numberOfAssignedWorkers!=0;
		
		if (lumberMillHasPeople&&isRandomNumberLessThanPointTwo()){
			lumberMill.setNumberOfAssignedWorkers(numberOfAssignedWorkers-1);;
			city.setTotalPopulation(city.getTotalPopulation()-1);
			events.add("Bandits killed a Carpenter");
		}
		else{
			handleNext(city, events);
		}		
	}

}

package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class MinersKiller extends PeopleKillingHandler{

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer mine = city.getResourceProducerOfType(Mine.class);
		int numberOfAssignedWorkers = mine.getNumberOfAssignedWorkers();
		boolean mineHasPeople = numberOfAssignedWorkers!=0;
		
		if (mineHasPeople&&isRandomNumberLessThanPointTwo()){
			mine.setNumberOfAssignedWorkers(numberOfAssignedWorkers-1);;
			city.setTotalPopulation(city.getTotalPopulation()-1);
			events.add("Bandits killed a Miner");
		}
		else{
			handleNext(city, events);
		}		
	}

}

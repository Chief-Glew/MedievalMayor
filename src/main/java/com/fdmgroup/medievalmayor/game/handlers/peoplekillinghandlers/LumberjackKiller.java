package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class LumberjackKiller extends PeopleKillingHandler{

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer forest = city.getResourceProducerOfType(Mine.class);
		int numberOfAssignedWorkers = forest.getNumberOfAssignedWorkers();
		boolean forestHasPeople = numberOfAssignedWorkers!=0;
		
		if (forestHasPeople&&isRandomNumberLessThanPointTwo()){
			forest.setNumberOfAssignedWorkers(numberOfAssignedWorkers-1);;
			city.setTotalPopulation(city.getTotalPopulation()-1);
			events.add("Bandits killed a Lumberjack");
		}
		else{
			handleNext(city, events);
		}			
	}

}

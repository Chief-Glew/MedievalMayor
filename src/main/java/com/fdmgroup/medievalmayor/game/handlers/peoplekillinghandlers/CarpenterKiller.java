package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class CarpenterKiller extends PeopleKillingHandler{

	@Autowired
	public CarpenterKiller(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

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

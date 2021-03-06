package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class FarmersKiller extends PeopleKillingHandler {

	@Autowired
	public FarmersKiller(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer farm = city.getResourceProducerOfType(Farm.class);
		int numberOfAssignedWorkers = farm.getNumberOfAssignedWorkers();
		boolean farmHasPeople = numberOfAssignedWorkers!=0;
		
		if (farmHasPeople&&isRandomNumberLessThanProbabilityOfDeath()){
			farm.setNumberOfAssignedWorkers(numberOfAssignedWorkers-1);;
			city.setTotalPopulation(city.getTotalPopulation()-1);
			events.add("Bandits killed a Farmer");
			resetProbability();
		}
		else{
			handleNext(city, events);
		}
		
	}

	

}

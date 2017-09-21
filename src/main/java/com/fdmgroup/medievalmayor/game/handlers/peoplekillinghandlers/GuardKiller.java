package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class GuardKiller extends PeopleKillingHandler {

	@Autowired
	public GuardKiller(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer guardHouse = city.getResourceProducerOfType(Mine.class);
		int numberOfAssignedWorkers = guardHouse.getNumberOfAssignedWorkers();
		boolean guardHouseHasPeople = numberOfAssignedWorkers!=0;
		
		if (guardHouseHasPeople&&isRandomNumberLessThanProbabilityOfDeath()){
			guardHouse.setNumberOfAssignedWorkers(numberOfAssignedWorkers-1);;
			city.setTotalPopulation(city.getTotalPopulation()-1);
			events.add("Bandits killed a Guard");
			resetProbability();
		}
		else{
			handleNext(city, events);
		}			
	}

}

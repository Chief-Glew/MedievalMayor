package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class MinersKiller extends PeopleKillingHandler{

	@Autowired
	public MinersKiller(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

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

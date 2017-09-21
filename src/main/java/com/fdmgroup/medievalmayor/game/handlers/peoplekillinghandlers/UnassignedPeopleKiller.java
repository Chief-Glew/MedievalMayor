package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class UnassignedPeopleKiller extends PeopleKillingHandler {

	@Autowired
	public UnassignedPeopleKiller(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	@Override
	public void handle(City city, List<String> events) {
		boolean cityHasUnnassignedPeople = city.getUnassignedPopulation()!=0;
		if (cityHasUnnassignedPeople&&isRandomNumberLessThanProbabilityOfDeath()){
			city.addResource(resourceFactory.getPopulation(-1));
			city.setTotalPopulation(city.getTotalPopulation()-1);
			events.add("Bandits killed an unassigned person");
			resetProbability();
		}
		else{
			handleNext(city, events);
		}
	}


}

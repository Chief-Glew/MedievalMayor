package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import com.fdmgroup.medievalmayor.game.city.City;

public class UnassignedPeopleKiller extends PeopleKillingHandler {

	@Override
	public void handle(City city, List<String> events) {
		boolean cityHasUnnassignedPeople = city.getUnassignedPopulation()!=0;
		if (cityHasUnnassignedPeople&&isRandomNumberLessThanPointTwo()){
			city.addResource(resourceFactory.getPopulation(-1));
			city.setTotalPopulation(city.getTotalPopulation()-1);
			events.add("Bandits killed an unassigned person");
		}
		else{
			handleNext(city, events);
		}
	}


}

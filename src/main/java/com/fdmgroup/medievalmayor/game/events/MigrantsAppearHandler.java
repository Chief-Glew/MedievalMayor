package com.fdmgroup.medievalmayor.game.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resources.Resource;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class MigrantsAppearHandler extends RandomEventHandler{

	private double frequency = 0.1;
	private ResourceFactory resourceFactory;

	@Autowired
	public MigrantsAppearHandler(ResourceFactory resourceFactory) {
		this.resourceFactory = resourceFactory;
	}

	public double getFrequency() {
		return frequency;
	}


	public void setFrequency(double frequency) {
		if (frequency>=0&&frequency<1){
			this.frequency = frequency;
		}
	}


	@Override
	public List<String> handle(City city, List<String> events) {
		if(Math.random()<frequency){
			int numberOfMigrants = generateMigrantNumbers(city.getTotalPopulation()/4);
			numberOfMigrants += 1;
			Resource newPeople = resourceFactory.getPopulation(numberOfMigrants);
			city.addResource(newPeople);
			events.add(numberOfMigrants +" refugees have shown up from nearby villages attacked by bandits.");
		}
		if (!isNextNull()) {
			return next.handle(city, events);
		}
			return events;
	}
	
	private int generateMigrantNumbers(int maxAmount){
		Double migrants = Math.floor(Math.random()*maxAmount);
		return migrants.intValue();
	}

}

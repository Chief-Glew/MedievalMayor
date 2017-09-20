package com.fdmgroup.medievalmayor.game.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers.PeopleKillingHandler;
import com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers.ResourceStealingHandler;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;
import com.fdmgroup.medievalmayor.game.resourceproducers.GuardHouse;;

@Component
public class BanditsAttackHandler extends RandomEventHandler {

	private double frequency = 1;
	private PeopleKillingHandler peopleKillingHandler;
	private ResourceStealingHandler resourceStealingHandler;

	@Autowired
	public BanditsAttackHandler(PeopleKillingHandler peopleKillingHandler,
			ResourceStealingHandler resourceStealingHandler) {
		this.peopleKillingHandler = peopleKillingHandler;
		this.resourceStealingHandler = resourceStealingHandler;
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
			banditsAttack(city, events);
		}

		if (!isNextNull()) {
			return next.handle(city, events);
		}
		return events;
	}

	private void banditsAttack(City city, List<String> events) {
		int numberOfBandits = generateBanditNumbers(city);
		events.add("You were attacked by "+numberOfBandits+" bandits!");
		GuardHouse guardHouse = (GuardHouse)city.getResourceProducerOfType(GuardHouse.class);
		int numberOfBanditsKilled = guardHouse.getBanditsKilled();
		if (numberOfBanditsKilled>numberOfBandits){
			events.add("Your guards killed all of them!");
		}
		else{
			events.add("Your guards killed "+numberOfBanditsKilled+" of them!");
		}
		numberOfBandits -= numberOfBanditsKilled;
		for(int i=0 ; i < numberOfBandits; i++){
			if(Math.random()<0.5){
				peopleKillingHandler.handle(city, events);
			}
			else{
				resourceStealingHandler.handle(city, events);
			}
		}
	}

	private int generateBanditNumbers(City city) {
		Double doubleNumberOfBandits = Math.random()*city.getTotalPopulation()*Math.pow(1.02,city.getCityYear());
		doubleNumberOfBandits = Math.floor(doubleNumberOfBandits);
		return doubleNumberOfBandits.intValue();
	}

}

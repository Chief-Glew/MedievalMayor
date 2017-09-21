package com.fdmgroup.medievalmayor.game.events;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers.PeopleKillingHandler;
import com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers.ResourceStealingHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.GuardHouse;;

@Component
public class BanditsAttackHandler extends RandomEventHandler {
	
	private static final Logger logger = LogManager.getLogger("BanditsAttackHandler.class");

	private double frequency = 0.25;
	private PeopleKillingHandler peopleKillingHandler;
	private ResourceStealingHandler resourceStealingHandler;

	@Autowired
	public BanditsAttackHandler(PeopleKillingHandler peopleKillingHandler,
			ResourceStealingHandler resourceStealingHandler) {
		this.peopleKillingHandler = peopleKillingHandler;
		this.resourceStealingHandler = resourceStealingHandler;
	}

	public double getFrequency() {
		logger.info("GetFrequency method used in BanditsAttackHandler class");
		return frequency;
	}

	public void setFrequency(double frequency) {
		logger.info("SetFrequency method used in BanditsAttackHandler class");
		if (frequency>=0&&frequency<1){
			this.frequency = frequency;
		}
	}

	@Override
	public List<String> handle(City city, List<String> events) {
		logger.info("Handle method used in BanditsAttackHandler class");
		if(Math.random()<frequency){
			banditsAttack(city, events);
			logger.trace("Bandit attack");
		}

		if (!isNextNull()) {
			return next.handle(city, events);
		}
		return events;
	}

	private void banditsAttack(City city, List<String> events) {
		logger.info("BanditsAttack method used in BanditsAttackHandler class");
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
		int resourceStolenCounter=0;
		int peopleKilledCounter=0;
		for(int i=0 ; i < numberOfBandits; i++){
			if(Math.random()<0.5){
				peopleKillingHandler.handle(city, events);
				resourceStolenCounter++;
			}
			else{
				resourceStealingHandler.handle(city, events);
				peopleKilledCounter++;
			}
		}
		logger.trace("During this bandit raid"+resourceStolenCounter+" resources were stolen and "+peopleKilledCounter+" were killed");
	}

	private int generateBanditNumbers(City city) {
		logger.info("GenerateBanditNumbers method used in BanditsAttackHandler class");
		Double doubleNumberOfBandits = Math.random()*city.getTotalPopulation()*Math.pow(1.02,city.getCityYear());
		doubleNumberOfBandits = Math.floor(doubleNumberOfBandits);
		if(doubleNumberOfBandits == 0){
			doubleNumberOfBandits=1d;
		}
		return doubleNumberOfBandits.intValue();
	}

}

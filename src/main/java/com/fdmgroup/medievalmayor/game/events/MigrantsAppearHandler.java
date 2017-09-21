package com.fdmgroup.medievalmayor.game.events;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resources.Resource;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class MigrantsAppearHandler extends RandomEventHandler{
	
	private static final Logger logger = LogManager.getLogger("MigrantsAppearHandler.class");

	private double frequency = 0.1;
	private ResourceFactory resourceFactory;

	@Autowired
	public MigrantsAppearHandler(ResourceFactory resourceFactory) {
		this.resourceFactory = resourceFactory;
	}

	public double getFrequency() {
		logger.info("GetFrequency method used in MigrantsAppearHandler class");
		return frequency;
	}


	public void setFrequency(double frequency) {
		logger.info("SetFrequency method used in MigrantsAppearHandler class");
		if (frequency>=0&&frequency<1){
			this.frequency = frequency;
		}
	}


	@Override
	public List<String> handle(City city, List<String> events) {
		logger.info("Handle method used in MigrantsAppearHandler class");
		if(Math.random()<frequency){
			int numberOfMigrants = generateMigrantNumbers(city.getTotalPopulation()/4);
			numberOfMigrants += 1;
			Resource newPeople = resourceFactory.getPopulation(numberOfMigrants);
			city.addResource(newPeople);city.setTotalPopulation(city.getTotalPopulation()+numberOfMigrants);
			events.add(numberOfMigrants +" refugees have shown up from nearby villages attacked by bandits.");
		}
		if (!isNextNull()) {
			return next.handle(city, events);
		}
			return events;
	}
	
	private int generateMigrantNumbers(int maxAmount){
		logger.info("GenerateMigrantNumbers method used in MigrantsAppearHandler class");
		Double migrants = Math.floor(Math.random()*maxAmount);
		return migrants.intValue();
	}

}

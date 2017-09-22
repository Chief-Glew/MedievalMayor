package com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

public abstract class PeopleKillingHandler {

	static final Logger logger = LogManager.getLogger("RandomEventHandler.class");

	protected PeopleKillingHandler next;
	protected ResourceFactory resourceFactory;
	private static double probabilityDenominator = 50;
	
	public PeopleKillingHandler(ResourceFactory resourceFactory) {
		this.resourceFactory = resourceFactory;
	}
	
	public void addToChain(PeopleKillingHandler handler) {
		if (next==null) {
			next = handler;
		}
		else {
			next.addToChain(handler); 
		}
		logger.debug("addToChain method used");
	}
	
	protected boolean isNextNull() {
		logger.debug("IsNextNull method used");
		return next==null;
	}
	
	protected boolean isRandomNumberLessThanProbabilityOfDeath() {
		return Math.random()<getProbabilityOfDeath();
	}
	
	protected void handleNext(City city, List<String> events) {
		if (!isNextNull()){
		next.handle(city, events);
		}
	}
	
	private double getProbabilityOfDeath() {
		return 1/probabilityDenominator--;
	}
	
	protected void resetProbability() {
		probabilityDenominator = 50;
	}
	
	public abstract void handle(City city, List<String> events);
}

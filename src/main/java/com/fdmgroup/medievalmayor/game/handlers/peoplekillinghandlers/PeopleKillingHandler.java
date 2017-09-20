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
	
	public PeopleKillingHandler() {
		this.resourceFactory = new ResourceFactory();
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
	
	protected boolean isRandomNumberLessThanPointTwo() {
		return Math.random()<0.2;
	}
	
	protected void handleNext(City city, List<String> events) {
		if (!isNextNull()){
		next.handle(city, events);
		}
	}
	
	public abstract void handle(City city, List<String> events);
}

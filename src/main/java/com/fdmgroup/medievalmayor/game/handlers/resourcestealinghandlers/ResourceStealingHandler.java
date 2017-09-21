package com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

public abstract class ResourceStealingHandler {

	static final Logger logger = LogManager.getLogger("RandomEventHandler.class");

	protected ResourceStealingHandler next;
	protected ResourceFactory resourceFactory;
	private static double probabilityDenominator = 50;
	
	public ResourceStealingHandler(ResourceFactory resourceFactory) {
		this.resourceFactory = resourceFactory;
	}
	
	public void addToChain(ResourceStealingHandler handler) {
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
	
	protected void handleNext(City city, List<String> events) {
		if (!isNextNull()){
		next.handle(city, events);
		}
	}
	
	protected boolean isRandomNumberLessThanProbabilityOfTheft() {
		return Math.random()<getProbabilityOfTheft();
	}
	
	private double getProbabilityOfTheft() {
		return 1/probabilityDenominator--;
	}
	
	protected void resetProbability() {
		probabilityDenominator = 50;
	}
	
	protected boolean cityHasResource(City city, String resourceType) {
		return city.getResourceAmount(resourceType)>0;
	}
	
	public abstract void handle(City city, List<String> events);
}

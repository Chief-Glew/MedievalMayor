package com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpdateFoodHandler extends UpdateResourcesHandler {
	
	static final Logger logger = LogManager.getLogger("UpdateFoodHandler.class");

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer farm = city.getResourceProducerOfType(Farm.class);
		city.addResource(farm.produceResource());
		city.addResource(resourceFactory.getFood(-city.getTotalPopulation()));
		handleNext(city, events);

		logger.debug("Handle method used");
	}

}

package com.fdmgroup.medievalmayor.game.command.handlers.updateresourceshandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpdateFoodHandler extends UpdateResourcesHandler {
	
	static final Logger logger = LogManager.getLogger("UpdateFoodHandler.class");

	@Override
	public void handle(City city) {
		ResourceProducer farm = city.getResourceProducerOfType(Farm.class);
		city.addResource(farm.produceResource());
		city.addResource(resourceFactory.getFood(-city.getTotalPopulation()));
		if (!isNextNull()) {
			next.handle(city);
		}
		logger.debug("Handle method used");
	}

}

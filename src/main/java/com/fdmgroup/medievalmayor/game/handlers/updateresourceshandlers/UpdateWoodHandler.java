package com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpdateWoodHandler extends UpdateResourcesHandler {
	
	static final Logger logger = LogManager.getLogger("UpdateWoodHandler.class");

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer forest = city.getResourceProducerOfType(Forest.class);
		city.addResource(forest.produceResource());
		if (!isNextNull()) {
			next.handle(city, events);
		}
		logger.debug("Handle method used");
	}

}

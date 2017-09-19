package com.fdmgroup.medievalmayor.handlers.updateresourceshandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.city.City;
import com.fdmgroup.medievalmayor.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.resourceproducers.ResourceProducer;

public class UpdateWoodHandler extends UpdateResourcesHandler {
	
	static final Logger logger = LogManager.getLogger("UpdateWoodHandler.class");

	@Override
	public void handle(City city) {
		ResourceProducer forest = city.getResourceProducerOfType(Forest.class);
		city.addResource(forest.produceResource());
		if (!isNextNull()) {
			next.handle(city);
		}
		logger.debug("Handle method used");
	}

}

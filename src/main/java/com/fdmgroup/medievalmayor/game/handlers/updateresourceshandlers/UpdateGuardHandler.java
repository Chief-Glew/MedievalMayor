package com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.GuardHouse;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpdateGuardHandler extends UpdateResourcesHandler {

	static final Logger logger = LogManager.getLogger("UpdateFoodHandler.class");

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer guardHouse = city.getResourceProducerOfType(GuardHouse.class);
		city.addResource(guardHouse.produceResource());
		handleNext(city, events);

		logger.debug("Handle method used");
	}


}

package com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpdateGoldHandler extends UpdateResourcesHandler {
	
	static final Logger logger = LogManager.getLogger("UpdateGoldHandler.class");

	@Override
	public void handle(City city, List<String> events) {
		ResourceProducer mine = city.getResourceProducerOfType(Mine.class);
		city.addResource(mine.produceResource());
		handleNext(city, events);
		logger.debug("Handle method used");
	}



}

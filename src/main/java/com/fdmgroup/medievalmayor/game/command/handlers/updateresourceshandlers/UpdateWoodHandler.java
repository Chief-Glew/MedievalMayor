package com.fdmgroup.medievalmayor.game.command.handlers.updateresourceshandlers;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpdateWoodHandler extends UpdateResourcesHandler {

	@Override
	public void handle(City city) {
		ResourceProducer forest = city.getResourceProducerOfType(Forest.class);
		city.addResource(forest.produceResource());
		if (!isNextNull()) {
			next.handle(city);
		}
	}

}

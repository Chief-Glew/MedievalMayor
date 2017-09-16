package com.fdmgroup.medievalmayor.game.command.updateresourceshandlers;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpdateFoodHandler extends UpdateResourcesHandler {

	@Override
	public void handle(City city) {
		ResourceProducer farm = city.getResourceBuildingOfType(Farm.class);
		city.addResource(farm.produceResource());
		city.addResource(resourceFactory.getFood(-city.getTotalPopulation()));
		if (!isNextNull()) {
			next.handle(city);
		}
	}

}

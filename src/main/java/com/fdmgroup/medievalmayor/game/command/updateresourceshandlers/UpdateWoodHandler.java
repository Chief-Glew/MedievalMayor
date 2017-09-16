package com.fdmgroup.medievalmayor.game.command.updateresourceshandlers;

import java.util.Set;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpdateWoodHandler extends UpdateResourcesHandler {

	@Override
	public void handle(City city) {
		ResourceProducer forest = city.getResourceBuildingOfType(Forest.class);
		city.addResource(forest.produceResource());
		if (!isNextNull()) {
			next.handle(city);
		}
	}

}

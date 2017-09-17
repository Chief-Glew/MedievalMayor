package com.fdmgroup.medievalmayor.game.command.handlers.updateresourceshandlers;


import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpdateGoldHandler extends UpdateResourcesHandler {

	@Override
	public void handle(City city) {
		ResourceProducer mine = city.getResourceProducerOfType(Mine.class);
		city.addResource(mine.produceResource());
		if (!isNextNull()) {
			next.handle(city);
		}
	}

}

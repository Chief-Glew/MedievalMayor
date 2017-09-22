package com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class StealWoodHandler extends ResourceStealingHandler {

	@Autowired
	public StealWoodHandler(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	@Override
	public void handle(City city, List<String> events) {
		
		if (cityHasResource(city, "Wood")&&isRandomNumberLessThanProbabilityOfTheft()){
			city.addResource(resourceFactory.getWood(-1));
			events.add("Bandits stole 1 Wood");
			resetProbability();
		}
		else{
			handleNext(city, events);
		}
	}
}

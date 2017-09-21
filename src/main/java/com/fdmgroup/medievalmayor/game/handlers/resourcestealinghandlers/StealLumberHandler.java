package com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class StealLumberHandler extends ResourceStealingHandler {

	@Autowired
	public StealLumberHandler(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	@Override
	public void handle(City city, List<String> events) {
		
		if (cityHasResource(city, "Lumber")&&isRandomNumberLessThanProbabilityOfTheft()){
			city.addResource(resourceFactory.getLumber(-1));
			events.add("Bandits stole 1 Lumber");
			resetProbability();
		}
		else{
			handleNext(city, events);
		}
	}
}

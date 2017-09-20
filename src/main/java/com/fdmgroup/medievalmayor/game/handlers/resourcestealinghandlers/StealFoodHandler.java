package com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

@Component
public class StealFoodHandler extends ResourceStealingHandler {

	@Autowired
	public StealFoodHandler(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	@Override
	public void handle(City city, List<String> events) {
		
		if (cityHasResource(city, "Food")&&isRandomNumberLessThanPointTwo()){
			city.addResource(resourceFactory.getFood(-1));
			events.add("Bandits stole 1 food");
		}
		else{
			handleNext(city, events);
		}
	}



	
}

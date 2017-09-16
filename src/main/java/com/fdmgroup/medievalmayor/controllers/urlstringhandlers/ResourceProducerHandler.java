package com.fdmgroup.medievalmayor.controllers.urlstringhandlers;

import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

public class ResourceProducerHandler extends URLStringHandler {

	private ProducerClassFromStringHandler stringToClassHandler;
	private ResourceProducerService resourceProducerService;


	public ResourceProducerHandler() {
		resourceProducerService = new ResourceProducerService();
		stringToClassHandler = new FarmStringHandler();
		stringToClassHandler.addToChain(new ForestStringHandler());
		stringToClassHandler.addToChain(new LumberMillStringHandler());
		stringToClassHandler.addToChain(new MineStringHandler());
	}

	@Override
	public String handel(City city, String urlString, Model model) throws NullPointerException {

		try {
			Class<? extends ResourceProducer> resourceProducerClass = stringToClassHandler.handle(urlString);	
			
			int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(resourceProducerClass)) + city.getUnassignedPopulation();

			model.addAttribute("producerName", urlString);
			model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(resourceProducerClass)));
			model.addAttribute("maxAssignable", maxAssignable);
			
			return "assignationPage";
		}
		catch(NullPointerException exception) {
			return next.handel(city, urlString, model);
		}
	}

}

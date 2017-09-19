package com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

public class ResourceProducerHandler extends URLStringHandler {
	
	static final Logger logger = LogManager.getLogger("ResourceProducerHandler.class");

	private ResourceProducerClassFromStringHandler stringToClassHandler;
	private ResourceProducerService resourceProducerService;


	public ResourceProducerHandler() {
		resourceProducerService = new ResourceProducerService();
		stringToClassHandler = new FarmStringHandler();
		stringToClassHandler.addToChain(new ForestStringHandler());
		stringToClassHandler.addToChain(new LumberMillStringHandler());
		stringToClassHandler.addToChain(new MineStringHandler());
	}

	@Override
	public String handle(City city, String urlString, Model model) throws NullPointerException {

		try {
			Class<? extends ResourceProducer> resourceProducerClass = stringToClassHandler.handle(urlString);	
			
			int maxAssignable = resourceProducerService.getPeopleInResourceProducer(city.getResourceProducerOfType(resourceProducerClass)) + city.getUnassignedPopulation();

			model.addAttribute("producerName", urlString);
			model.addAttribute("currentAssigned", resourceProducerService.getPeopleInResourceProducer(city.getResourceProducerOfType(resourceProducerClass)));
			model.addAttribute("maxAssignable", maxAssignable);
			logger.debug("Handle method used");
			return "assignationPage";
		}
		catch(NullPointerException exception) {
			logger.debug("Null pointer exception");
			return next.handle(city, urlString, model);
		}
	}

}

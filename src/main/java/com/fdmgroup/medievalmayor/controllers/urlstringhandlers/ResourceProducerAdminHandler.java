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

public class ResourceProducerAdminHandler extends URLStringHandler {

	private ProducerClassFromStringHandler stringToClassHandler;

	public ResourceProducerAdminHandler() {
		stringToClassHandler = new FarmStringHandler();
		stringToClassHandler.addToChain(new ForestStringHandler());
		stringToClassHandler.addToChain(new LumberMillStringHandler());
		stringToClassHandler.addToChain(new MineStringHandler());
	}
	
	@Override
	public String handle(City city, String urlString, Model model) throws NullPointerException{
		try {
			Class<? extends ResourceProducer> resourceProducerClass = stringToClassHandler.handle(urlString);
			city.getResourceBuildingOfType(resourceProducerClass);
						
			return "resourceProducerAdminPage";
		}
		catch(NullPointerException exception) {
			return next.handle(city, urlString, model);
		}
	}

}

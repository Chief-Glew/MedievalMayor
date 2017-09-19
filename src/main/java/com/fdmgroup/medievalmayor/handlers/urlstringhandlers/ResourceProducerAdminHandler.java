package com.fdmgroup.medievalmayor.handlers.urlstringhandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.city.City;
import com.fdmgroup.medievalmayor.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.resourceproducers.ResourceProducer;

public class ResourceProducerAdminHandler extends URLStringHandler {
	
	static final Logger logger = LogManager.getLogger("ResourceProducerAdminHandler.class");

	private ResourceProducerClassFromStringHandler stringToClassHandler;

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
			ResourceProducer resourceProducer = city.getResourceProducerOfType(resourceProducerClass);
			model.addAttribute("resourceProducer", resourceProducer);
			int baseResourceProduction = resourceProducer.getBaseResourceProduction();
			int upgradeMultiplier = resourceProducer.getUpgradeMultiplier();
			model.addAttribute("baseResourceProduction", baseResourceProduction);
			model.addAttribute("upgradeMultiplier", upgradeMultiplier);
			logger.debug("handle method used"); 
			return "resourceProducerAdminPage";
		}
		catch(NullPointerException exception) {
			logger.debug("Null pointer exception");
			return next.handle(city, urlString, model);
		}
	}

}

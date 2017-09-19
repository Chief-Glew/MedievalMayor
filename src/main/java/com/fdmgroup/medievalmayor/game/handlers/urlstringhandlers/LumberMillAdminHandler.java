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
import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;

public class LumberMillAdminHandler extends URLStringHandler {
	
	static final Logger logger = LogManager.getLogger("LumberMillAdminHandler.class");

	private ResourceProducerClassFromStringHandler stringToClassHandler;


	public LumberMillAdminHandler() {
		stringToClassHandler = new FarmStringHandler();
		stringToClassHandler.addToChain(new ForestStringHandler());
		stringToClassHandler.addToChain(new LumberMillStringHandler());
		stringToClassHandler.addToChain(new MineStringHandler());
	}

	@Override
	public String handle(City city, String urlString, Model model) throws NullPointerException {
		if (urlString.equals("LumberMill")) {
			try {
				next.handle(city, urlString, model);
				LumberMill lumberMill = (LumberMill)city.getResourceProducerOfType(LumberMill.class);//TODO find a better way to do this
				int amountOfLumberPerWood = lumberMill.getAmountOfLumberPerWood();
				model.addAttribute("resourceProducer", lumberMill);
				model.addAttribute("amountOfLumberPerWood", amountOfLumberPerWood);
				logger.debug("Lumber mill handled");
				return "lumberMillAdminPage";
			}
			catch(NullPointerException exception) { 
				logger.debug("NullPointerException");
				return next.handle(city, urlString, model);
			}
		}
		else {
			logger.debug("next.handle method used");
			return next.handle(city, urlString, model);
		}
	}
}

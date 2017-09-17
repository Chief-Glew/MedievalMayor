package com.fdmgroup.medievalmayor.controllers.urlstringhandlers;

import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class LumberMillAdminHandler extends URLStringHandler {

	private ProducerClassFromStringHandler stringToClassHandler;


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
				
				model.addAttribute("amountOfLumberPerWood", amountOfLumberPerWood);
				return "lumberMillAdminPage";
			}
			catch(NullPointerException exception) {
				return next.handle(city, urlString, model);
			}
		}
		else {
			return next.handle(city, urlString, model);
		}
	}
}

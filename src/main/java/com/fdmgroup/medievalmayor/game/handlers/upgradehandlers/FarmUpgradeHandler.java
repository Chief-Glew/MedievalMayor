package com.fdmgroup.medievalmayor.game.handlers.upgradehandlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.usercommands.ClientCommand;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;

@Component
public class FarmUpgradeHandler extends ResourceProducerUpgradeHandler {

	@Autowired
	public FarmUpgradeHandler(ClientCommand clientCommand, ResourceProducerClassFromStringHandler resourceProducerClassFromStringHandler) {
		super(clientCommand,  resourceProducerClassFromStringHandler);
		Map <String, Integer> cost = new HashMap<String, Integer>();
		cost.put("Gold", 40);
		cost.put("Lumber", 50);
		setCost(cost);
	}

	@Override
	public String handle(City city, String urlString, Model model)  {
		if (urlString.equals("Farm")){
			return handlerMethod(city, urlString);
		}
		else {
			return next.handle(city, urlString, model);
		}
	}
}

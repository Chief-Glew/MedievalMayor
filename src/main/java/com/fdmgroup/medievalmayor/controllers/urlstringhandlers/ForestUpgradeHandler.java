package com.fdmgroup.medievalmayor.controllers.urlstringhandlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;

@Component
public class ForestUpgradeHandler extends ResourceProducerUpgradeHandler {

	@Autowired
	public ForestUpgradeHandler(ClientCommand clientCommand, ResourceProducerClassFromStringHandler resourceProducerClassFromStringHandler) {
		super(clientCommand,  resourceProducerClassFromStringHandler);
		Map <String, Integer> cost = new HashMap<String, Integer>();
		cost.put("Gold", 40);
		cost.put("Lumber", 50);
		setCost(cost);
	}

}

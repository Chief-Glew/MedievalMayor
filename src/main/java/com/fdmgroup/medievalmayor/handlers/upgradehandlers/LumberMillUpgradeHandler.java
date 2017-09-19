package com.fdmgroup.medievalmayor.handlers.upgradehandlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.command.ClientCommand;
import com.fdmgroup.medievalmayor.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;

@Component
public class LumberMillUpgradeHandler extends ResourceProducerUpgradeHandler {

	@Autowired
	public LumberMillUpgradeHandler(ClientCommand clientCommand, ResourceProducerClassFromStringHandler resourceProducerClassFromStringHandler) {
		super(clientCommand,  resourceProducerClassFromStringHandler);
		Map <String, Integer> cost = new HashMap<String, Integer>();
		cost.put("Gold", 40);
		cost.put("Lumber", 50);
		setCost(cost);
	}
}

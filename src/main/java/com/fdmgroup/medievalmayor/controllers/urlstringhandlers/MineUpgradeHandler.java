package com.fdmgroup.medievalmayor.controllers.urlstringhandlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ProducerClassFromStringHandler;

@Component
public class MineUpgradeHandler extends ResourceProducerUpgradeHandler {

	@Autowired
	public MineUpgradeHandler(ClientCommand clientCommand, ProducerClassFromStringHandler producerClassFromStringHandler) {
		super(clientCommand,  producerClassFromStringHandler);
		Map <String, Integer> cost = new HashMap<String, Integer>();
		cost.put("Gold", 40);
		cost.put("Lumber", 50);
		setCost(cost);
	}

}

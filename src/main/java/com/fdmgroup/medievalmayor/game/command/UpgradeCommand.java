package com.fdmgroup.medievalmayor.game.command;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpgradeCommand implements UserCommand {
	
	static final Logger logger = LogManager.getLogger("UpdateLumberHandler.class");

	private ResourceProducer resourceProducer;
	private Map<String, Integer> cost;
	private City city;
	
	public UpgradeCommand(City city, ResourceProducer resourceProducer, Map<String, Integer> cost) {
		this.resourceProducer = resourceProducer;
		this.cost = cost;
		this.city = city;
	}

	@Override
	public void execute() {
		Map<String, Integer> resources = city.getResources();
		
	}

}

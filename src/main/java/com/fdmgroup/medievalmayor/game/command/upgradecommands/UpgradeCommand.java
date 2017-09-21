package com.fdmgroup.medievalmayor.game.command.upgradecommands;

import java.util.Map;

import javax.naming.InsufficientResourcesException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class UpgradeCommand implements UserCommand {

	static final Logger logger = LogManager.getLogger("UpgradeCommand.class");

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
		logger.info("UpgradeCommand executed");
		Map<String, Integer> resources = city.getResources(); 
		boolean hasEnoughResources = true;
		for(String resource: cost.keySet()){
			int resourceCost = cost.getOrDefault(resource, 0);
			int currentResourceAmmount = resources.getOrDefault(resource, 0);
			if(resourceCost>currentResourceAmmount){
				try {
					logger.trace("UpgradeCommand executed");
					hasEnoughResources = false;
					throw new InsufficientResourcesException("Insufficient resources available");
				} catch (InsufficientResourcesException e) {
					e.printStackTrace();
				}
			}
			else{
				resources.put(resource, currentResourceAmmount-resourceCost);
			}
		}
		if (hasEnoughResources){
			city.setResources(resources);
			resourceProducer.incrementProducerLevel();
		}
	}
}

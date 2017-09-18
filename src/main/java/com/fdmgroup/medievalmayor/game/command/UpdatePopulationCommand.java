package com.fdmgroup.medievalmayor.game.command;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceFactory;

public class UpdatePopulationCommand implements UserCommand {
	
	private static final Logger logger = LogManager.getLogger("UpdatePopulationCommand.class");

	private City city;
	private ResourceFactory resourceFactory;
	private ResourceProducerService resourceBuildingService;

	public UpdatePopulationCommand(City city) {
		this.city = city;
		resourceFactory = new ResourceFactory();
		resourceBuildingService = new ResourceProducerService();
	}
	
	public void execute(){
		Map<String, Integer> resources = city.getResources();
		int totalPopulation = city.getTotalPopulation();
		if(resources.get("Food")>3){
			totalPopulation += 1;
			city.addResource(resourceFactory.getPopulation(1));
			logger.debug("Population increased");
		} 
		else if (resources.get("Food")<0){
			for (ResourceProducer resourceBuilding: city.getResourceGenerators()){
				try {
					resourceBuildingService.assignPeopleToBuilding(0, totalPopulation, resourceBuilding);
				} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
					logger.info("Exception");
					e.printStackTrace();
				}
			}
			totalPopulation -= 1;
			logger.debug("Population decreased");
			resources.put("Population", totalPopulation);
			resources.put("Food", 0);
			logger.debug("Food reset to 0");
			city.setResources(resources);
		}
		city.setTotalPopulation(totalPopulation);
		logger.debug("Execute method used");
	}
}


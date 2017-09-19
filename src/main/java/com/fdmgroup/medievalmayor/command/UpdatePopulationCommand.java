package com.fdmgroup.medievalmayor.command;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.city.City;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficientPopulationException;
import com.fdmgroup.medievalmayor.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.resourceproducers.ResourceProducerService;
import com.fdmgroup.medievalmayor.resources.ResourceFactory;

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
		int newCitizens;
		int exCitizens;
		if(resources.get("Food")>5){
			newCitizens = resources.get("Food")/5;
			totalPopulation += newCitizens;
			city.addResource(resourceFactory.getPopulation(1));
			logger.debug("Population increased by: "+newCitizens);
		} 
		else if (resources.get("Food")<0){
			exCitizens = resources.get("Food")/-5;
			for (ResourceProducer resourceBuilding: city.getResourceGenerators()){
				try {
					resourceBuildingService.assignPeopleToResourceProducer(0, totalPopulation, resourceBuilding);
				} catch (InsufficientPopulationException | AssignedNegativeNumberException e) {
					logger.info("Exception");
					e.printStackTrace();
				}
			}
			totalPopulation -= exCitizens;
			logger.debug("Population decreased by: "+exCitizens);
			resources.put("Population", totalPopulation);
			resources.put("Food", 0);
			logger.debug("Food reset to 0");
			city.setResources(resources);
		}
		city.setTotalPopulation(totalPopulation);
		logger.debug("Execute method used");
	}
}


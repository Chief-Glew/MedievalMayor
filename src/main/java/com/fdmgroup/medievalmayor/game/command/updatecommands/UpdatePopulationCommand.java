package com.fdmgroup.medievalmayor.game.command.updatecommands;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficientPopulationException;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.CommandInvoker;
import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;
import com.fdmgroup.medievalmayor.game.resources.ResourceFactory;

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
			city.addResource(resourceFactory.getPopulation(newCitizens));
			logger.debug("Population increased by: "+newCitizens);
		} 
		else if (resources.get("Food")<0){
			exCitizens = resources.get("Food");
			for (ResourceProducer resourceBuilding: city.getResourceGenerators()){
				try {
					resourceBuildingService.assignPeopleToResourceProducer(0, totalPopulation, resourceBuilding);
				} catch (InsufficientPopulationException | AssignedNegativeNumberException e) {
					logger.info("Exception");
					e.printStackTrace();
				}
			}
			totalPopulation += exCitizens;
			logger.debug("Population decreased by: "+exCitizens);
			resources.put("Population", totalPopulation);
			resources.put("Food", 0);
			logger.debug("Food reset to 0");
			city.setResources(resources);
		}
		city.setTotalPopulation(totalPopulation);
		CommandInvoker commandInvoker = new CommandInvoker();
		UserCommand updateCityYearCommand = new UpdateCityYearCommand(city);
		commandInvoker.setCommand(updateCityYearCommand);
		commandInvoker.invokeCommands();

		logger.debug("Execute method used");
	}
}


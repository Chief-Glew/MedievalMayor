package com.fdmgroup.medievalmayor.game.command.updatecommands;

import java.util.List;
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
	private List<String> events;

	public UpdatePopulationCommand(City city, List<String> events) {
		this.city = city;
		resourceFactory = new ResourceFactory();
		this.events = events;
		resourceBuildingService = new ResourceProducerService();
	}
	
	public void execute(){ 
		logger.info("UpdatePopulationCommand executed");
		Map<String, Integer> resources = city.getResources(); 
		int totalPopulation = city.getTotalPopulation();
		int newCitizens;
		int exCitizens;
		int people = 0;
		String comingOrGoing = "Arrived";
		if(resources.get("Food")>5){
			newCitizens = resources.get("Food")/5;
			totalPopulation += newCitizens;
			people = newCitizens;
			city.addResource(resourceFactory.getPopulation(newCitizens));
			logger.trace("Population increased by: "+newCitizens+" in UpdatePopulationCommand class");
		} 
		else if (resources.get("Food")<0){
			exCitizens = resources.get("Food");
			people = exCitizens;
			comingOrGoing = "Left";
			for (ResourceProducer resourceBuilding: city.getResourceProducers()){
				try {
					resourceBuildingService.assignPeopleToResourceProducer(0, totalPopulation, resourceBuilding);
					logger.trace("People recalled to city in UpdatePopulationCommand class");
				} catch (InsufficientPopulationException | AssignedNegativeNumberException e) {
					logger.debug("Exception: "+e+" in UpdatePopulationCommand class");
					e.printStackTrace();
				}
			}
			totalPopulation += exCitizens;
			logger.info("Population decreased by: "+exCitizens+" in UpdatePopulationCommand class");
			resources.put("Population", totalPopulation);
			resources.put("Food", 0);
			logger.info("Food reset to 0 in UpdatePopulationCommand class");
			city.setResources(resources);
		}
		events.add(people+" people have "+comingOrGoing);
		city.setTotalPopulation(totalPopulation);
		CommandInvoker commandInvoker = new CommandInvoker();
		UserCommand updateCityYearCommand = new UpdateCityYearCommand(city, events);
		commandInvoker.setCommand(updateCityYearCommand);
		commandInvoker.invokeCommands();

		logger.debug("Execute method used");
	}
}


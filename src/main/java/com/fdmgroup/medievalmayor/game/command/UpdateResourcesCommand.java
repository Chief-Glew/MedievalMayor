package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceFactory;

public class UpdateResourcesCommand implements UserCommand{

	private City city; 
	private ResourceFactory resourceFactory;
	private ResourceProducerService resourceBuildingService;
	static final Logger logger = LogManager.getLogger("CityService");
	
	
	public UpdateResourcesCommand(City city) {
		resourceFactory = new ResourceFactory();
		resourceBuildingService = new ResourceProducerService();
		this.city = city;
	}
 
	public void execute() {
		for (ResourceProducer resourceBuilding: city.getResourceGenerators()) {
			city.addResource(
					resourceBuildingService.getResourceForBuilding(
							resourceBuilding
							)
					);
		}
		int population = city.getTotalPopulation();
		city.addResource(resourceFactory.getFood(-population));
		
		UserCommand updatePopulationCommand = new UpdatePopulationCommand(city);
		CommandInvoker commandInvoker = new CommandInvoker();
		
		commandInvoker.setCommand(updatePopulationCommand);
		commandInvoker.invokeCommands();
	}

}

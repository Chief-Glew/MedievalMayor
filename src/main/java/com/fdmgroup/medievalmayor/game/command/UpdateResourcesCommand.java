package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.updateresourceshandlers.UpdateFoodHandler;
import com.fdmgroup.medievalmayor.game.command.updateresourceshandlers.UpdateGoldHandler;
import com.fdmgroup.medievalmayor.game.command.updateresourceshandlers.UpdateLumberHandler;
import com.fdmgroup.medievalmayor.game.command.updateresourceshandlers.UpdateResourcesHandler;
import com.fdmgroup.medievalmayor.game.command.updateresourceshandlers.UpdateWoodHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceFactory;

public class UpdateResourcesCommand implements UserCommand{
	
	private static final Logger logger = LogManager.getLogger("UpdateResourcesCommand.class");

	private City city; 
	private ResourceFactory resourceFactory;
	private ResourceProducerService resourceProducerService;
	private UpdateResourcesHandler updateResourcesHandler;
	
	public UpdateResourcesCommand(City city) {
		resourceFactory = new ResourceFactory();
		resourceProducerService = new ResourceProducerService();
		this.city = city;
		updateResourcesHandler = new UpdateFoodHandler();
		updateResourcesHandler.addToChain(new UpdateGoldHandler());
		updateResourcesHandler.addToChain(new UpdateWoodHandler());
		updateResourcesHandler.addToChain(new UpdateLumberHandler());
	}
 
	public void execute() {
		updateResourcesHandler.handle(city);
		for (ResourceProducer resourceProducer: city.getResourceGenerators()) {
			city.addResource(resourceProducerService.getResourceForBuilding(resourceProducer));
		}
		int population = city.getTotalPopulation();
		city.addResource(resourceFactory.getFood(-population));
		
		UserCommand updatePopulationCommand = new UpdatePopulationCommand(city);
		CommandInvoker commandInvoker = new CommandInvoker();
		
		commandInvoker.setCommand(updatePopulationCommand);
		commandInvoker.invokeCommands();
		logger.trace("Execute method used");
	}

}

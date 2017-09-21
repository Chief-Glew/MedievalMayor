package com.fdmgroup.medievalmayor.game.command.setcommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficientPopulationException;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

public class SetNumberOfWorkersInResourceProducerFromCityCommand implements UserCommand{
	
	private static final Logger logger = LogManager.getLogger("SetNumberOfWorkersInResourceBuildingFromCityCommand.class");

	private City city;
	private ResourceProducer resourceProducer;
	private int numberOfPeopleToAssign;
	private ResourceProducerService resourceProducerService;

	public SetNumberOfWorkersInResourceProducerFromCityCommand(City city, ResourceProducer resourceProducer, int numberOfPeopleToAssign){
		this.city = city;
		this.resourceProducer = resourceProducer;
		this.numberOfPeopleToAssign = numberOfPeopleToAssign;
		resourceProducerService = new ResourceProducerService();
	}
	
	@Override
	public void execute() {
		logger.info("SetNumberOfWorkersInResourceProducerFromCity Command executed");
		int unassignedPeople = city.getUnassignedPopulation();
		int newUnassignedPeople = unassignedPeople;
		try {
			newUnassignedPeople = resourceProducerService.assignPeopleToResourceProducer(numberOfPeopleToAssign, unassignedPeople, resourceProducer);
			logger.trace("NewUnassignedPeople set in SetNumberOfWorkersInResourceProducerFromCityCommand class");
		} catch (InsufficientPopulationException | AssignedNegativeNumberException e) {
			e.printStackTrace();
			logger.debug("Exception: "+e+" in SetNumberOfWorkersInResourceProducerFromCity Command");
		}
		city.setUnassignedPopulation(newUnassignedPeople);
	}
}

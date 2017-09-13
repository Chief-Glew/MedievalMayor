package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.medievalmayor.game.City;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.FarmService;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.MineService;

public class UpdateResourcesCommand implements UserCommand{

	private City city;
	private MineService mineService;
	private FarmService farmService;
	static final Logger logger = LogManager.getLogger("CityService");
	
	
	public UpdateResourcesCommand(City city) {
		this.mineService = new MineService();
		this.farmService = new FarmService();
		this.city = city;
	}

	@Override
	public void execute() {
		int gold = city.getGold();
		int food = city.getFood();
		Farm farm = city.getFarm();
		Mine mine = city.getMine();
		gold += mineService.produceResourcesForMine(mine);
		food += farmService.produceResourcesForFarm(farm);
		food -= city.getTotalPopulation(); 
		city.setFood(food);
		city.setGold(gold);
		logger.trace("Resources Updated");
		UserCommand updatePopulationCommand = new UpdatePopulationCommand(city);
		CommandInvoker commandInvoker = new CommandInvoker();
		
		commandInvoker.setCommand(updatePopulationCommand);
		commandInvoker.invokeCommands();
	}

}

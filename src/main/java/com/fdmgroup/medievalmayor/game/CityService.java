package com.fdmgroup.medievalmayor.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.building.BuildingManager;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.FarmService;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.MineService;

@Component
public class CityService {
	
	static final Logger logger = LogManager.getLogger("CityService");
	
	private static CityService instance;
	private MineService mineService;
	private FarmService farmService;

	@Autowired
	public CityService(MineService mineService, FarmService farmService){
		this.mineService = mineService;
		this.farmService = farmService;
		instance=this;
	}
	
	public static CityService getInstance(){
		logger.trace("CityService Instance retrieved");
		return instance; 
	}
	
	public void updateTurn(City city) {
		updateResources(city);
		updatePopulation(city);
		logger.trace("Turn Updated");
	}

	private void updatePopulation(City city) {
		int food = city.getFood();
		int totalPopulation = city.getTotalPopulation();
		int unassignedPopulation = city.getUnassignedPopulation();
		if(food>3){
			totalPopulation += 1;
			unassignedPopulation += 1;
		} 
		
		if(food<0){
			city.setUnassignedPopulation(totalPopulation);
			Mine mine = city.getMine();
			mineService.setNumberOfPeopleInMine(0,mine);
			Farm farm = city.getFarm();
			farmService.setNumberOfPeopleInFarm(0,farm);
			totalPopulation -= 1;
			unassignedPopulation = totalPopulation;
		}
		//TODO change the proportion of food deficient and number of leaving people
		//TODO verifier to see if correct number of people is moved
		
		city.setTotalPopulation(totalPopulation);
		city.setUnassignedPopulation(unassignedPopulation);
		logger.trace("Population Updated");
	}

	private void updateResources(City city) {
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
	}
	
	public void assignPeopleToFarm(City city, int numberOfPeople){
		Farm farm = city.getFarm();
		try {
			BuildingManager.getInstance().assignPeopleToBuilding
			(numberOfPeople, city.getUnassignedPopulation(), farm);
			logger.trace("People assigned to farm");
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			logger.error("Error! Unable to assign people to farm");
			e.printStackTrace();
		}
	}

}

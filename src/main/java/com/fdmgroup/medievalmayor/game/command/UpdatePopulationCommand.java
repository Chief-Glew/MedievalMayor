package com.fdmgroup.medievalmayor.game.command;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.FarmService;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.MineService;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.city.City;

public class UpdatePopulationCommand implements UserCommand {

	private City city;
	private MineService mineService;
	private FarmService farmService;

	public UpdatePopulationCommand(City city) {
		this.city = city;
		mineService = new MineService();
		farmService = new FarmService(); //TODO fix this bean it
		
	}


	@Override
	public void execute() {
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
			city.setFood(0);
		}
		//TODO change the proportion of food deficient and number of leaving people
		//TODO verifier to see if correct number of people is moved

		city.setTotalPopulation(totalPopulation);
		city.setUnassignedPopulation(unassignedPopulation);
	}

}


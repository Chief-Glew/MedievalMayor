package com.fdmgroup.medievalmayor.game.command;

import java.util.Map;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuildingService;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources.ResourceFactory;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public class UpdatePopulationCommand implements UserCommand {

	private City city;
	private ResourceFactory resourceFactory;
	private ResourceBuildingService resourceBuildingService;
	//private MineService mineService;
	//private FarmService farmService;

	public UpdatePopulationCommand(City city) {
		this.city = city;
		//mineService = new MineService();
		//farmService = new FarmService(); //TODO fix this bean it
		resourceFactory = new ResourceFactory();
		resourceBuildingService = new ResourceBuildingService();
	}
	
	public void execute(){
		Map<String, Integer> resources = city.getResources();
		int totalPopulation = city.getTotalPopulation();
		if(resources.get("Food")>3){
			totalPopulation += 1;
			city.addResource(resourceFactory.getPopulation(1));
		} 
		else if (resources.get("Food")<0){
			for (ResourceBuilding resourceBuilding: city.getResourceGenerators()){
				try {
					resourceBuildingService.assignPeopleToBuilding(0, totalPopulation, resourceBuilding);
				} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
					e.printStackTrace();
				}
			}
			totalPopulation -= 1;
			resources.put("Population", totalPopulation);
			resources.put("Food", 0);
			city.setResources(resources);
			city.setTotalPopulation(totalPopulation);
		}
		
	}
		//TODO change the proportion of food deficient and number of leaving people
		//TODO verifier to see if correct number of people is moved
}


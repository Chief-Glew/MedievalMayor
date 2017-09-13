package com.fdmgroup.medievalmayor;

import com.fdmgroup.medievalmayor.game.City;
import com.fdmgroup.medievalmayor.game.CityService;
import com.fdmgroup.medievalmayor.game.building.BuildingManager;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.FarmService;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.MineService;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public class DemoApp {

	public static void main(String[] args) {
		City city = new City(10,20,5, new Farm(3), new Mine(2));
		FarmService farmService = FarmService.getInstance();
		MineService mineService = MineService.getInstance();
		BuildingManager buildingManager = BuildingManager.getInstance();
		CityService cityService = CityService.getInstance();


		System.out.println("Initial City Values");
		System.out.println("Initial Number of Unassigned People: " + city.getUnassignedPopulation());
		System.out.println("Initial Number of Farmers: " + farm.getNoOfAssignedWorkers());
		System.out.println("Initial Number of Miners: " + mine.getNoOfAssignedWorkers());
		System.out.println("Initial Food: " + city.getFood());
		System.out.println("Initial Gold: " + city.getGold() + "\n");

		try {
			buildingManager.assignPeopleToBuilding(2, mines); 
			buildingManager.assignPeopleToBuilding(5, farms);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			e.printStackTrace();
		}

		System.out.println("Turn City Values Set");
		System.out.println("Current Number of Unassigned People: " + city.getUnassignedPopulation());
		System.out.println("Current Number of Farmers: " + farms.getNoOfAssignedWorkers());
		System.out.println("Current Number of Miners: " + mines.getNoOfAssignedWorkers());
		cityService.updateTurn(city);
		System.out.println("Turn Results");
		System.out.println("Current Food: " + city.getFood());
		System.out.println("Current Gold: " + city.getGold() );
		System.out.println("Total Population: " + city.getTotalPopulation()+ "\n");

		try {
			buildingManager.assignPeopleToBuilding(0, farms);
			buildingManager.assignPeopleToBuilding(15, mines);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			System.out.println("Error Occurred: "+e.getClass());
		}

		System.out.println("Turn City Values Set");

		System.out.println("Current Number of Unassigned People: " + city.getUnassignedPopulation());
		System.out.println("Current Number of Farmers: " + farms.getNoOfAssignedWorkers());
		System.out.println("Current Number of Miners: " + mines.getNoOfAssignedWorkers());
		cityService.updateTurn(city);
		System.out.println("Turn Results");
		System.out.println("Current Food: " + city.getFood());
		System.out.println("Current Gold: " + city.getGold());
		System.out.println("Total Population: " + city.getTotalPopulation()+ "\n");

		try {
			buildingManager.assignPeopleToBuilding(-5, farms);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			System.out.println("Error Occurred: "+e.getClass());
		}

		try {
			buildingManager.assignPeopleToBuilding(10, mines);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			System.out.println("Error Occurred: "+e.getClass());
		}

		System.out.println("Turn City Values Set");

		System.out.println("Current Number of Unassigned People: " + city.getUnassignedPopulation());
		System.out.println("Current Number of Farmers: " + farms.getNoOfAssignedWorkers());
		System.out.println("Current Number of Miners: " + mines.getNoOfAssignedWorkers());
		cityService.updateTurn(city);
		System.out.println("Turn Results");

		System.out.println("Current Food: " + city.getFood());
		System.out.println("Current Gold: " + city.getGold());
		System.out.println("Total Population: " + city.getTotalPopulation()+ "\n");

		try {
			buildingManager.assignPeopleToBuilding(-5, farms);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			System.out.println("Error Occurred: "+e.getClass());
		}

		try {
			buildingManager.assignPeopleToBuilding(10, mines);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			System.out.println("Error Occurred: "+e.getClass());
		}

		System.out.println("Turn City Values Set");

		System.out.println("Current Number of Unassigned People: " + city.getUnassignedPopulation());
		System.out.println("Current Number of Farmers: " + farms.getNoOfAssignedWorkers());
		System.out.println("Current Number of Miners: " + mines.getNoOfAssignedWorkers());
		cityService.updateTurn(city);
		System.out.println("Turn Results");

		System.out.println("Current Food: " + city.getFood());
		System.out.println("Current Gold: " + city.getGold());
		System.out.println("Total Population: " + city.getTotalPopulation()+ "\n");

	}
}

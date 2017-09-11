package com.fdmgroup.medievalmayor;

import com.fdmgroup.medievalmayor.building.BuildingManager;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Farms;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mines;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

public class DemoApp {

	public static void main(String[] args) {
		City city = City.getInstance();
		Farms farms = Farms.getInstance();
		Mines mines = Mines.getInstance();
		BuildingManager buildingManager = BuildingManager.getInstance();

		System.out.println("Initial Number of Unassigned People: " + city.getUnassignedPopulation());
		System.out.println("Initial Number of Farmers: " + farms.getNumberOfPeopleInBuilding());
		System.out.println("Initial Number of Miners: " + mines.getNumberOfPeopleInBuilding());
		System.out.println("Initial Food: " + city.getFood());
		System.out.println("Initial Gold: " + city.getGold() + "\n");

		try {
			buildingManager.assignPeopleToBuilding(2, mines);
			buildingManager.assignPeopleToBuilding(3, farms);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			e.printStackTrace();
		}

		System.out.println("Current Number of Unassigned People: " + city.getUnassignedPopulation());
		System.out.println("Current Number of Farmers: " + farms.getNumberOfPeopleInBuilding());
		System.out.println("Current Number of Miners: " + mines.getNumberOfPeopleInBuilding());
		city.updateResources();
		System.out.println("Current Food: " + city.getFood());
		System.out.println("Current Gold: " + city.getGold() + "\n");

		try {
			buildingManager.assignPeopleToBuilding(0, farms);
			buildingManager.assignPeopleToBuilding(15, mines);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			System.out.println("Error Occurred: "+e.getClass());
		}

		System.out.println("Current Number of Unassigned People: " + city.getUnassignedPopulation());
		System.out.println("Current Number of Farmers: " + farms.getNumberOfPeopleInBuilding());
		System.out.println("Current Number of Miners: " + mines.getNumberOfPeopleInBuilding());
		city.updateResources();
		System.out.println("Current Food: " + city.getFood());
		System.out.println("Current Gold: " + city.getGold() + "\n");

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

		System.out.println("Current Number of Unassigned People: " + city.getUnassignedPopulation());
		System.out.println("Current Number of Farmers: " + farms.getNumberOfPeopleInBuilding());
		System.out.println("Current Number of Miners: " + mines.getNumberOfPeopleInBuilding());
		city.updateResources();
		System.out.println("Current Food: " + city.getFood());
		System.out.println("Current Gold: " + city.getGold() + "\n");
	}
}

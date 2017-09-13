package com.fdmgroup.medievalmayor.game.building;

import com.fdmgroup.medievalmayor.game.City;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public class BuildingManager implements Buildable, PersonAssigner {

	private City city;

	public static class BuildingManagerInstanceHolder{
		private static final BuildingManager INSTANCE = new BuildingManager();
	}

	public static BuildingManager getInstance(){
		return BuildingManagerInstanceHolder.INSTANCE;
	}

	public void assignPeopleToBuilding(int numberOfPeopleToAssign, ResourceBuilding building) throws InsufficentPopulationException, AssignedNegativeNumberException {
		if(numberOfPeopleToAssign < 0){
			throw new AssignedNegativeNumberException("Cannot assign a negative number of people to a building");
		}
		int currentPeople = building.getNoOfAssignedWorkers();
		int difference = numberOfPeopleToAssign - currentPeople;
		int unassignedPeople = city.getUnassignedPopulation();
		if(difference > unassignedPeople){
			throw new InsufficentPopulationException("Insufficent population available");
		}
		building.setNumberOfPeopleInBuilding(numberOfPeopleToAssign);
		city.setUnassignedPopulation(unassignedPeople - difference);
	}
 
	public ResourceBuilding buildAMine(){
		return null;

	}

	public ResourceBuilding buildAFarm(){
		return null;

	}

	public int getPeopleInBuilding(ResourceBuilding building){

		return building.getNoOfAssignedWorkers();

	}





}

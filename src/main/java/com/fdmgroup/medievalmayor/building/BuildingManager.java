package com.fdmgroup.medievalmayor.building;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.medievalmayor.City;
import com.fdmgroup.medievalmayor.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

public class BuildingManager implements Buildable, PersonAssigner {

	private List<Building> cityBuildings;

	private BuildingManager(List<Building> cityBuildings){
		this.cityBuildings = cityBuildings;

	}

	public static class BuildingManagerInstanceHolder{
		private static final BuildingManager INSTANCE = new BuildingManager(new ArrayList<Building>());
	}

	public static BuildingManager getInstance(){
		return BuildingManagerInstanceHolder.INSTANCE;
	}

	public int assignPeopleToBuilding(int numberOfPeopleToAssign, int numberOfPeopleAvailible, ResourceBuilding building) throws InsufficentPopulationException, AssignedNegativeNumberException {
		if(numberOfPeopleToAssign < 0){
			throw new AssignedNegativeNumberException("Cannot assign a negative number of people to a building");
		}
		int currentPeople = building.getNoOfAssignedWorkers();
		int difference = numberOfPeopleToAssign - currentPeople;
		if(difference > numberOfPeopleAvailible){
			throw new InsufficentPopulationException("Insufficent population available");
		}
		building.setNumberOfPeopleInBuilding(numberOfPeopleToAssign);
		return (numberOfPeopleAvailible - difference);
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

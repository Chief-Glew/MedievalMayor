package com.fdmgroup.medievalmayor.building;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.City;
import com.fdmgroup.medievalmayor.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

@Component
public class BuildingManager implements Buildable, PersonAssigner {

	private List<Building> cityBuildings;
	private City city;

	@Autowired
	private BuildingManager(List<Building> cityBuildings){
		this.cityBuildings = cityBuildings;
		
	}

	public static class BuildingManagerInstanceHolder{
		private static final BuildingManager INSTANCE = new BuildingManager(new ArrayList<Building>());
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

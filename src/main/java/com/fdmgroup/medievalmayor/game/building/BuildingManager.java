package com.fdmgroup.medievalmayor.game.building;

<<<<<<< HEAD:src/main/java/com/fdmgroup/medievalmayor/building/BuildingManager.java
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.City;
import com.fdmgroup.medievalmayor.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

public class BuildingManager implements Buildable, PersonAssigner {

	static final Logger logger = LogManager.getLogger("BuildingManager");
	
	private List<Building> cityBuildings;

	private BuildingManager(List<Building> cityBuildings){
		this.cityBuildings = cityBuildings;

	}

=======
import com.fdmgroup.medievalmayor.game.City;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public class BuildingManager implements Buildable, PersonAssigner {

	private City city;

>>>>>>> commandMerger:src/main/java/com/fdmgroup/medievalmayor/game/building/BuildingManager.java
	public static class BuildingManagerInstanceHolder{
		private static final BuildingManager INSTANCE = new BuildingManager();
	}

	public static BuildingManager getInstance(){
		logger.trace("Building Manager Instance retrieved");
		return BuildingManagerInstanceHolder.INSTANCE;
	}

	public int assignPeopleToBuilding(int numberOfPeopleToAssign, int numberOfPeopleAvailible, ResourceBuilding building) throws InsufficentPopulationException, AssignedNegativeNumberException {
		if(numberOfPeopleToAssign < 0){
			throw new AssignedNegativeNumberException("Cannot assign a negative number of people to a building");
		}
		int currentPeople = building.getNumberOfAssignedWorkers();
		int difference = numberOfPeopleToAssign - currentPeople;
		if(difference > numberOfPeopleAvailible){
			throw new InsufficentPopulationException("Insufficent population available");
		}
		building.setNumberOfPeopleInBuilding(numberOfPeopleToAssign);
		logger.debug("People assigned to building");
		return (numberOfPeopleAvailible - difference);
	}

	public ResourceBuilding buildAMine(){
		logger.trace("Mine Built");
		return null;

	}

	public ResourceBuilding buildAFarm(){
		logger.trace("Farm built");
		return null;

	}

	public int getPeopleInBuilding(ResourceBuilding building){
		logger.trace("Number of assigned workers in "+building+" retrieved");
		return building.getNumberOfAssignedWorkers();
	}
}

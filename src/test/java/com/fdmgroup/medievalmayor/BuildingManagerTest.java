package com.fdmgroup.medievalmayor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuildingService;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public class BuildingManagerTest {

	private City city;
	private ResourceBuildingService buildingManager;
	private ResourceBuilding farm;
	private ResourceBuilding mine;

	@Before
	public void init(){
		buildingManager = ResourceBuildingService.getInstance();
		farm = Farm.getInstance();
		mine = Mine.getInstance();
		city= City.getInstance();
		try {
			buildingManager.assignPeopleToBuilding(0, mine);
			buildingManager.assignPeopleToBuilding(0, farm);
		} catch (InsufficentPopulationException e) {
			e.printStackTrace();
		} catch (AssignedNegativeNumberException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testThatWhenYouAssignTwoPeopleToTheMineTheMineHasTwoPeople() throws AssignedNegativeNumberException, InsufficentPopulationException{
		buildingManager.assignPeopleToBuilding(2, mine);
		assertEquals(2, buildingManager.getPeopleInBuilding(mine));
	}

	@Test
	public void testThatWhenYouAssignSevenPeopleToTheFarmTheFarmHasSevenPeople() throws AssignedNegativeNumberException, InsufficentPopulationException{
		buildingManager.assignPeopleToBuilding(7, farm);
		assertEquals(7, buildingManager.getPeopleInBuilding(farm));
	}

	@Test
	public void testThatWhenYouAssignFivePeopleToTheFarmAndThreePeopleToTheMineThereAreTwoPeopleLeftUnasigned() throws AssignedNegativeNumberException, InsufficentPopulationException{
		buildingManager.assignPeopleToBuilding(5, farm);
		buildingManager.assignPeopleToBuilding(3, mine);
		assertEquals(2, city.getUnassignedPopulation());
	}

	@Test(expected = InsufficentPopulationException.class)
	public void testThatUserCannotAssignMoreThanPopulationToBuildings() throws AssignedNegativeNumberException, InsufficentPopulationException{
		buildingManager.assignPeopleToBuilding(11, farm);
	}

	@Test(expected = AssignedNegativeNumberException.class)
	public void testThatUserCannotAssignNegativeNumberOfPeopleToBuildings() throws AssignedNegativeNumberException, InsufficentPopulationException{
		buildingManager.assignPeopleToBuilding(-1, mine);
	}
}

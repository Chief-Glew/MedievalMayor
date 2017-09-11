package com.fdmgroup.medievalmayor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.building.BuildingManager;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Farms;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mines;
import com.fdmgroup.medievalmayor.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

public class BuildingManagerTest {

	private City city;
	private BuildingManager buildingManager;
	private ResourceBuilding farm;
	private ResourceBuilding mine;

	@Before
	public void init(){
		buildingManager = BuildingManager.getInstance();
		farm = Farms.getInstance();
		mine = Mines.getInstance();
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

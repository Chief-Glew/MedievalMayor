package com.fdmgroup.medievalmayor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.building.BuildingManager;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Farms;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mines;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

public class CityTest {

	private City  city;
	private Farms farm;
	private Mines mine;
	private BuildingManager buildingManager;
	
	@Before
	public void init(){
		city = City.getInstance();
		farm = Farms.getInstance();
		mine = Mines.getInstance();
		buildingManager = BuildingManager.getInstance();
	}
	
	@Test
	public void testThatAFarmWithOnePersonIncreasesFoodSupplyByThree() throws InsufficentPopulationException, AssignedNegativeNumberException{
		buildingManager.assignPeopleToBuilding(1, farm);
		city.updateResources();
		assertEquals(3, city.getFood());
	}
	
	@Test
	public void testThatAMineWithOnePersonIncreasesGoldSupplyByTwo() throws InsufficentPopulationException, AssignedNegativeNumberException{
		buildingManager.assignPeopleToBuilding(1, mine);
		city.updateResources();
		assertEquals(2, city.getGold());
	}
}

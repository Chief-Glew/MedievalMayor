package com.fdmgroup.medievalmayor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.building.BuildingManager;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

public class CityTest {

	private City  city;
	private Farm farm;
	private Mine mine;
	private BuildingManager buildingManager;
	
	@Before
	public void init(){
		city = City.getInstance();
		farm = Farm.getInstance();
		mine = Mine.getInstance();
		buildingManager = BuildingManager.getInstance();
		farm.setMultiplier(3);
		mine.setMultiplier(2);
	}
	
	@Test
	public void testThatAFarmWithOnePersonIncreasesFoodSupplyByThree() throws InsufficentPopulationException, AssignedNegativeNumberException{
		buildingManager.assignPeopleToBuilding(1, farm);
		city.updateTurn();
		assertEquals(13, city.getFood());
	}
	
	@Test
	public void testThatAMineWithOnePersonIncreasesGoldSupplyByTwo() throws InsufficentPopulationException, AssignedNegativeNumberException{
		buildingManager.assignPeopleToBuilding(1, mine);
		city.updateTurn();
		assertEquals(7, city.getGold());
	}
}
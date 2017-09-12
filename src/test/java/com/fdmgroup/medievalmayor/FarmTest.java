package com.fdmgroup.medievalmayor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.building.BuildingManager;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

public class FarmTest {
	private Farm farm;
	private BuildingManager buildingManager;
	
	@Before
	public void init(){
		farm = Farm.getInstance();
		buildingManager = BuildingManager.getInstance();
	}
	
	@Test
	public void testThatWhenOnePersonIsAssignedToAFarmThreeFoodIsProduced() throws InsufficentPopulationException, AssignedNegativeNumberException{
		buildingManager.assignPeopleToBuilding(1, farm);
		assertEquals(3,farm.produceResource());
	}
	
	@Test
	public void testThatWhenTwoPeopleIsAssignedToAFarmSixFoodIsProduced() throws InsufficentPopulationException, AssignedNegativeNumberException{
		buildingManager.assignPeopleToBuilding(2, farm);
		assertEquals(6,farm.produceResource());
	}
	
	
}


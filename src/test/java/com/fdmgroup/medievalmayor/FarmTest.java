package com.fdmgroup.medievalmayor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

<<<<<<< HEAD
import com.fdmgroup.medievalmayor.building.BuildingManager;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;
=======
import com.fdmgroup.medievalmayor.game.building.BuildingManager;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;
>>>>>>> commandMerger

public class FarmTest {
	private Farm farm;
	private Mine mine;
	private BuildingManager buildingManager;
	private City city;
	private CityService cityService;

	
	@Before
	public void init(){
		farm = new Farm(3);
		mine = new Mine(1);
		cityService = CityService.getInstance();
		city = new City(10,20,5,farm,mine);
		buildingManager = BuildingManager.getInstance();
	}
	
	@Test
	public void testThatWhenOnePersonIsAssignedToAFarmThreeFoodIsProduced() throws InsufficentPopulationException, AssignedNegativeNumberException{
		cityService.assignPeopleToFarm(city, 1);
		cityService.updateTurn(city);
		assertEquals(3,farm.produceResource());
	}
	
	@Test
	public void testThatWhenTwoPeopleIsAssignedToAFarmSixFoodIsProduced() throws InsufficentPopulationException, AssignedNegativeNumberException{
		cityService.assignPeopleToFarm(city, 2);
		cityService.updateTurn(city);
		assertEquals(6,farm.produceResource());
	}
	
	
}


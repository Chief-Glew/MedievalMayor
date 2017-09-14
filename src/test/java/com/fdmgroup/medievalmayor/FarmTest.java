package com.fdmgroup.medievalmayor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.game.building.BuildingManager;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.city.CityService;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public class FarmTest {
	private Farm farm;
	private Mine mine;
	private BuildingManager buildingManager;
	private City city;
	private CityService cityService;
	private CityFactory cityFactory;

	
	@Before
	public void init(){
		cityFactory = new CityFactory();
		cityService = CityService.getInstance();
		city = cityFactory.getNewCity();
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


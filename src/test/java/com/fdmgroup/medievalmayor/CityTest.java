package com.fdmgroup.medievalmayor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

public class CityTest {

	private City  city;
	private Farm farm;
	private Mine mine;
	private ResourceProducerService buildingManager;
	
	@Before
	public void init(){
		city = City.getInstance();
		farm = Farm.getInstance();
		mine = Mine.getInstance();
		buildingManager = ResourceProducerService.getInstance();
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
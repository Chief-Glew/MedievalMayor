package com.fdmgroup.medievalmayor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

public class ForestTest {

	private Forest forest;
	@Spy
	private City city;
	private ClientCommand clientCommand;
	private ResourceProducerService buildingManager;
	private ForestService forestService; 
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		forest = new Forest(1);
		clientCommand = new ClientCommand();
		buildingManager = new ResourceProducerService();
		forestService = new ForestService();
	}
	
	@Test
	public void testThatGetPeopleInBuildingReturnOneWhenOnePersonIsAddedToTheForest(){
		clientCommand.setNumberOfWorkersInResourceBuildingForCity(city, forest,1);
		assertEqual(1,buildingManager.getPeopleInBuilding(forest));
				
	}
	
	@Test
	public void testThatForestProducesOneWoodWhenOnePersonIsAssigned(){
		clientCommand.setNumberOfWorkersInResourceBuildingForCity(city, forest,1);
		assertEqual(1,forestService.produceResourcesForForest(forest));
	}
	
	@Test
	public void testThatTheNumberOfPeopleInForestDoesNotChangeWhenANegativeNumberOfPeopleIsAssignedToForest(){
		clientCommand.setNumberOfWorkersInResourceBuildingForCity(city, forest,1);
		clientCommand.setNumberOfWorkersInResourceBuildingForCity(city, forest,-1);
		assertEqual(1,buildingManager.getPeopleInBuilding(forest));
				
	}
	
	@Test 
	public void testThatTheNumberOfPeopleDoesNotChangeInForestWhenTwentyPeopleAreAssigned(){
		clientCommand.setNumberOfWorkersInResourceBuildingForCity(city, forest,1);
		clientCommand.setNumberOfWorkersInResourceBuildingForCity(city, forest,20);
		assertEqual(1,buildingManager.getPeopleInBuilding(forest));				
	}
	
}

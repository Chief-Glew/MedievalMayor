package com.fdmgroup.medievalmayor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.fdmgroup.medievalmayor.game.city.City;

public class ForestTest {

	private Forest forest;
	@Spy
	private City city;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		forest = new Forest(1);
	}
	
	@Test
	public void testThatPeopleCanBeAssignedToForest(){
		
	}
	
	@Test
	public void testThatForestProducesThreeWoodWhenOnePersonIsAssigned(){
		
	}
	
	
}

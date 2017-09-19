package com.fdmgroup.medievalmayor.resourceproducers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;

public class LumberMillTest {
	
	private LumberMill lumberMill;
	
	@Before
	public void init(){
		lumberMill = new LumberMill(1, 3, 0, "Farm", 1, 3, 4);
	}
	
	@Test
	public void testThatWhenTheAmountOfLumberPerWoodIsSetTo4TheAmountOfLumberIs4(){
		lumberMill.setAmmountOfLumberPerWood(4);
		assertEquals(4,lumberMill.getAmountOfLumberPerWood());
	}

}

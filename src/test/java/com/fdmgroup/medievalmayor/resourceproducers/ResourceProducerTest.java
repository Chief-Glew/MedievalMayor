package com.fdmgroup.medievalmayor.resourceproducers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;

public class ResourceProducerTest {
	
	private Farm farm;

	@Before
	public void init(){
		farm = new Farm(1, 3, 0, "Farm", 1, 3);
	}
	
	@Test
	public void testThatWhenUpgradeMultiplierIsSetTo2TheUpgradeMultiplierIs2(){
		farm.setUpgradeMultiplier(2);
		assertEquals(2,farm.getUpgradeMultiplier());
	}
	
	@Test
	public void testThatWhenBaseResourceProductionIsSetTo3TheBaseResourceProductionIs3(){
		farm.setBaseResourceProduction(3);
		assertEquals(3,farm.getBaseResourceProduction());
	}
	
	@Test
	public void testThatWhenProducerLevelIsSetTo1TheProducerLevelIs1(){
		farm.setProducerLevel(1);
		assertEquals(1,farm.getProducerLevel());
	}
	
	@Test
	public void testThatWhenProducerLevelIsIncrementedBy1TheProducerLevelWillBe2(){
		farm.incrementProducerLevel();
		assertEquals(2,farm.getProducerLevel());
	}
}

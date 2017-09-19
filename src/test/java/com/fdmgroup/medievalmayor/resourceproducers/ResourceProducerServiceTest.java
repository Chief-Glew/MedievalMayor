package com.fdmgroup.medievalmayor.resourceproducers;

import org.junit.Before;
import org.junit.Test;

public class ResourceProducerServiceTest {
	
	private ResourceProducerService resourceProducerService;
	private Farm farm;
	
	@Before
	public void init(){
		resourceProducerService = new ResourceProducerService();
		farm = new Farm();
	}
	
	@Test
	public void testThatResourceProducersProduceResources(){
		resourceProducerService.getResourceFromResourceProducer(farm);
	}
//	
//	@Test
//	public void testThat
//	
}

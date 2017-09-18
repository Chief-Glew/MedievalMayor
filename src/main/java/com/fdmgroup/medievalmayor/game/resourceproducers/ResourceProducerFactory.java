package com.fdmgroup.medievalmayor.game.resourceproducers;

import org.springframework.stereotype.Component;

@Component
public class ResourceProducerFactory {

	public ResourceProducer getFarm(){
		return new Farm(0, 3, 0, "Farm", 0, 3);
	}
	
	public ResourceProducer getMine(){
		return new Mine(0, 2, 0, "Mine", 0,3 );
	}
	
	public ResourceProducer getForest(){
		return new Forest(0, 1, 0, "Forest", 0, 3);
	}
	
	public ResourceProducer getLumberMill(){
		return new LumberMill(0, 4, 0, "LumberMill", 0, 3, 4);
	}
}
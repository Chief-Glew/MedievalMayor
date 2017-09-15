package com.fdmgroup.medievalmayor.game.resourceproducers;

public class ResourceProducerFactory {

	public ResourceProducer getFarm(){
		return new Farm(0, 3, 0, "Farm");
	}
	
	public ResourceProducer getMine(){
		return new Mine(0, 3, 0, "Mine");
	}
	
	public ResourceProducer getForest(){
		return new Forest(0, 3, 0, "Forest");
	}
}
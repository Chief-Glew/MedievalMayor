package com.fdmgroup.medievalmayor.game.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerFactory;

@Component
public class CityFactory {
	
	private ResourceProducerFactory resourceProducerFactory;

	public CityFactory() {
		resourceProducerFactory = new ResourceProducerFactory();
	}
	
	public City getNewCity(){
		return new City("nullsberg", 10, 
				resourceProducerFactory.getFarm(), 
				resourceProducerFactory.getMine());
	}
	
	public City getNewCity(String cityName){
		return new City(cityName, 10, 
				resourceProducerFactory.getFarm(), 
				resourceProducerFactory.getMine());
	}
	
	public City getNewNewCity(){
		return new City("nullsberg", 10, 
				resourceProducerFactory.getFarm(), 
				resourceProducerFactory.getMine(), 
				resourceProducerFactory.getForest());
	}
}

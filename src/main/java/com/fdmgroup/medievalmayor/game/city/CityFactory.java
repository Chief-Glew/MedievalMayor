package com.fdmgroup.medievalmayor.game.city;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerFactory;

@Component
public class CityFactory {
	
	private ResourceProducerFactory resourceProducerFactory;

	static final Logger logger = LogManager.getLogger("CityFactory.class");

	public CityFactory() {
		resourceProducerFactory = new ResourceProducerFactory();
	}
	
	public City getNewCity(){
		return new City("DefaultCity", 10, 
				resourceProducerFactory.getFarm(), 
				resourceProducerFactory.getMine());
	}
	
	public City getNewCity(String cityName){
		return new City(cityName, 10, 
				resourceProducerFactory.getFarm(), 
				resourceProducerFactory.getMine());
	}
	
	public City getNewCityWithForest(){
		return new City("DefaultCity", 10, 
				resourceProducerFactory.getFarm(), 
				resourceProducerFactory.getMine(), 
				resourceProducerFactory.getForest());
	}
}

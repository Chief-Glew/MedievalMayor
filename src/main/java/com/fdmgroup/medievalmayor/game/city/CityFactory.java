package com.fdmgroup.medievalmayor.game.city;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerFactory;

@Component
public class CityFactory {

	private ResourceProducerFactory resourceProducerFactory;

	static final Logger logger = LogManager.getLogger("CityFactory.class");

	public CityFactory(ResourceProducerFactory resourceProducerFactory) {
		this.resourceProducerFactory = resourceProducerFactory;
	}

	public City getNewCity(){
		logger.debug("getNewCity method used");
		return new City("DefaultCity", 10, 
				resourceProducerFactory.getFarm(), 
				resourceProducerFactory.getMine(),
				resourceProducerFactory.getForest(),
				resourceProducerFactory.getLumberMill());
	}

	public City getNewCity(String cityName){
		logger.debug("getNewCity method used");
		return new City(cityName, 10, 
				resourceProducerFactory.getFarm(), 
				resourceProducerFactory.getMine(),
				resourceProducerFactory.getForest(),
				resourceProducerFactory.getLumberMill());
	}

}

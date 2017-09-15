package com.fdmgroup.medievalmayor.game.city;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;

@Component
public class CityFactory {

	static final Logger logger = LogManager.getLogger("CityFactory.class");

	@Autowired
	public CityFactory() {
	}
	
	public City getNewCity(){
		return new City("nullsberg", 10, new Farm(3), new Mine(2));
	}
	
	public City getNewCity(String cityName){
		return new City(cityName, 10, new Farm(3), new Mine(2));
	}
	
}

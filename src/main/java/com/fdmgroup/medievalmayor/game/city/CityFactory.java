package com.fdmgroup.medievalmayor.game.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;

@Component
public class CityFactory {

	@Autowired
	public CityFactory() {
	}
	
	public City getNewCity(){
		return new City("nullsberg", 10, new Farm(3), new Mine(2));
	}
	
	public City getNewCity(String cityName){
		return new City(cityName, 10, new Farm(3), new Mine(2));
	}
	
	public City getNewNewCity(){
		return new City("nullsberg", 10, new Farm(3), new Mine(2), new Forest(1));
	}
}

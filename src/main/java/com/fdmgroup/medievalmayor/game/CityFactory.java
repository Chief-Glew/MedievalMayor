package com.fdmgroup.medievalmayor.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;

@Component
public class CityFactory {

	@Autowired
	public CityFactory() {
	}
	
	public City getNewCity(){
		return new City(10, 20, 5, new Farm(3), new Mine(2));
	}
}

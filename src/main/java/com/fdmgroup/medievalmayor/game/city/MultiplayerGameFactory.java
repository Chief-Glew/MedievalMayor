package com.fdmgroup.medievalmayor.game.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.city.CityFactory;

@Component
public class MultiplayerGameFactory {
	
	public CityFactory cityFactory;
	
	@Autowired
	public MultiplayerGameFactory(CityFactory cityFactory) {
		this.cityFactory = cityFactory;
	}

	public MultiplayerGame getTwoPlayerGame(String city1, String city2) {
		return new MultiplayerGame(cityFactory.getNewCity(city1), cityFactory.getNewCity(city2));
	}

}

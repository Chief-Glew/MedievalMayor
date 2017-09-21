package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

import java.util.List;

import com.fdmgroup.medievalmayor.game.city.City;

public abstract class UpdateWeatherHandler {
	
	protected UpdateWeatherHandler next; 
	protected String weatherEffect = "";
	
	public abstract double handle(City city, double weather);
	
	public void addToChain(UpdateWeatherHandler handler) {
		if (next==null) {
			next = handler;
		}
		else {
			next.addToChain(handler); 
		}
	}

	public String getWeatherEffect() {
		return weatherEffect;
	}
}

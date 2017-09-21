package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;

@Component
public abstract class UpdateWeatherHandler {
	
	protected UpdateWeatherHandler next; 
	
	public abstract double handle(City city, double weather, String weatherEvent);
	
	public void addToChain(UpdateWeatherHandler handler) {
		if (next==null) {
			next = handler;
		}
		else {
			next.addToChain(handler); 
		}
	}
}
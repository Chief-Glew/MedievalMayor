package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.handlers.eventhandlers.RandomEventHandler;

public abstract class UpdateWeatherHandler {

	protected UpdateWeatherHandler next; 

	public abstract double handle(double weather, List<String> events);

	public void addToChain(UpdateWeatherHandler handler) {
		if (next==null) {
			next = handler;
		}
		else {
			next.addToChain(handler); 
		}
	}

	protected boolean isNextNull() {
		return next==null;
	}
	
	protected double handleNext(double weather, List<String> events) {
		if (!isNextNull()) {
			return next.handle(weather, events);
		}
		else {
			return 0;
		}
	}
}


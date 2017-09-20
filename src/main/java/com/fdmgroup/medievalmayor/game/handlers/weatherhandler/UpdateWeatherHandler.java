package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

public abstract class UpdateWeatherHandler {
	
	protected UpdateWeatherHandler next;
	
	public abstract double handle(double weather);
	
	public void addToChain(UpdateWeatherHandler handler) {
		if (next==null) {
			next = handler;
		}
		else {
			next.addToChain(handler); 
		}
	}
}

package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

public class GoodWeatherHandler extends UpdateWeatherHandler{
	
	@Override
	public double handle(double weather) {
		if(weather > 0.66){
			System.out.println("------------Good weather");
			System.out.println("Good weather");
			System.out.println("Good weather");
			System.out.println("Good weather");
			System.out.println("Good weather");
			return 1.2;
		} else {
			return next.handle(weather);
		}
	}
}

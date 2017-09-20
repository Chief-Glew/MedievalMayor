package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

public class BadWeatherHandler extends UpdateWeatherHandler{

	@Override
	public double handle(double weather) {
		if(weather < 0.33){
			System.out.println("------------Bad weather");
			System.out.println("Bad weather");
			System.out.println("Bad weather");
			System.out.println("Bad weather");
			System.out.println("Bad weather");
			return 0.8;
		}else{
			return next.handle(weather);
		}

	}
}
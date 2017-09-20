package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

public class NormalWeatherHandler extends UpdateWeatherHandler{

	@Override
	public double handle(double weather) {
		if(weather > 0.33 && weather < 0.66){
			System.out.println("------------Normal weather");
			System.out.println("Normal weather");
			System.out.println("Normal weather");
			System.out.println("Normal weather");
			System.out.println("Normal weather");
			return 1;
		}else{
			return next.handle(weather);
		}
	}
}

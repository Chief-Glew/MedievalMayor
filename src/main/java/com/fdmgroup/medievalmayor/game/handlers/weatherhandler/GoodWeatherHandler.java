package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;

@Component
public class GoodWeatherHandler extends UpdateWeatherHandler{
	
	static final Logger logger = LogManager.getLogger("GoodWeatherHandler.class");

	@Override
	public double handle(City city, double weather) {
		if(weather > 0.66){
			logger.debug("Good Weather This year");
			logger.debug("Good Weather handler used");
			System.out.println("----------------------Good Weather");
			weatherEffect = "Good weather has increased your crop yield! More food was produced this year";
			return 1.2;
		} else {
			return next.handle(city, weather);
		}
	}
	

}

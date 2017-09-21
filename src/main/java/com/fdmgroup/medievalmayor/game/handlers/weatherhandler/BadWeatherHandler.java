package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;

@Component
public class BadWeatherHandler extends UpdateWeatherHandler{

	static final Logger logger = LogManager.getLogger("BadWeatherHandler.class");

	@Override
	public double handle(City city, double weather) {
		if(weather < 0.33){
			logger.debug("Bad Weather This year");
			logger.debug("Bad Weather handler used");
			System.out.println("----------------------Bad Weather");
			weatherEffect = "Bad weather has reduced your crop yield! Less food was produced this year";
			return 0.8;
		}else{
			return next.handle(city, weather);
		}
	}
}
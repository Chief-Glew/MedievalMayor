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
	public double handle(double weather, List<String> events) {
		if(weather < 0.33){
			logger.debug("Bad Weather This year");
			logger.debug("Bad Weather handler used");
			System.out.println("----------------------Bad Weather");
			events.add("Bad Weather This year");
			return 0.8;
		}else{
			return handleNext(weather, events);
		}
	}
}
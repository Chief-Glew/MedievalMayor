package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.City;

@Component
public class NormalWeatherHandler extends UpdateWeatherHandler{

	static final Logger logger = LogManager.getLogger("NormalWeatherHandler.class");

	@Override
	public double handle(double weather, List<String> events) {
		if(weather > 0.33 && weather < 0.66){
			logger.debug("Normal Weather This year");
			logger.debug("Normal Weather handler used");
			System.out.println("----------------------Normal Weather");
			events.add("Normal Weather This year");
			return 1;
		}else{
			return handleNext(weather, events);
		}
	}
}

package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoodWeatherHandler extends UpdateWeatherHandler{
	
	static final Logger logger = LogManager.getLogger("GoodWeatherHandler.class");
	
	@Override
	public double handle(double weather) {
		if(weather > 0.66){
			logger.debug("Good Weather This year");
			logger.debug("Good Weather handler used");
			return 1.2;
		} else {
			return next.handle(weather);
		}
	}
}

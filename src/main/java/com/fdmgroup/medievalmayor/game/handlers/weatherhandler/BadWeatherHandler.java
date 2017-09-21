package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BadWeatherHandler extends UpdateWeatherHandler{

	static final Logger logger = LogManager.getLogger("BadWeatherHandler.class");
	
	@Override
	public double handle(double weather) {
		if(weather < 0.33){
			logger.debug("Bad Weather This year");
			logger.debug("Bad Weather handler used");
			return 0.8;
		}else{
			return next.handle(weather);
		}

	}
}
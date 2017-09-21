package com.fdmgroup.medievalmayor.game.handlers.weatherhandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NormalWeatherHandler extends UpdateWeatherHandler{

	static final Logger logger = LogManager.getLogger("NormalWeatherHandler.class");
	
	@Override
	public double handle(double weather) {
		if(weather > 0.33 && weather < 0.66){
			logger.debug("Normal Weather This year");
			logger.debug("Normal Weather handler used");
			return 1;
		}else{
			return next.handle(weather);
		}
	}
}

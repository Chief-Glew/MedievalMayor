package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FarmService {

	static final Logger logger = LogManager.getLogger("FarmService");

	
	public FarmService() {
	}

	public int produceResourcesForFarm(Farm farm){
		logger.trace("Farm resources produced");
		return farm.produceResource();
	}

	public void setNumberOfPeopleInFarm(int i, Farm farm) {
		logger.trace("Number of people in farm set");
		farm.setNumberOfPeopleInBuilding(i);		
	}
} 

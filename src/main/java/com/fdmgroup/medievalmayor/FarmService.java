package com.fdmgroup.medievalmayor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.building.resourcebuilding.Farm;

public class FarmService {
	
	static final Logger logger = LogManager.getLogger("FarmService");

	public static class FarmServiceInstanceHolder{
		private static final FarmService INSTANCE = new FarmService(); 
	}
	
	public static FarmService getInstance(){
		logger.trace("Farm Service Instance retrieved");
		return FarmServiceInstanceHolder.INSTANCE;
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

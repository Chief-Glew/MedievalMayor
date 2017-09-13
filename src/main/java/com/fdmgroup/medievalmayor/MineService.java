package com.fdmgroup.medievalmayor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.building.resourcebuilding.Mine;

public class MineService {
	
	static final Logger logger = LogManager.getLogger("MineService");
	
	private MineService() {
	}
	
	public static class MineServiceInstanceHolder{
		private static final MineService INSTANCE = new MineService();
	}
	
	public static MineService getInstance(){
		logger.trace("Mine service instance retrieved");
		return MineServiceInstanceHolder.INSTANCE;
	}
	
	public int produceResourcesForMine(Mine mine){
		logger.trace("Mine resources produced");
		return mine.produceResource();
	}
	
	public void setNumberOfPeopleInMine(int i, Mine mine) {
		logger.trace("Number of people in mine set");
		mine.setNumberOfPeopleInBuilding(i);		
	}

}

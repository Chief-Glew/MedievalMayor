package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MineService {
	
	static final Logger logger = LogManager.getLogger("MineService");
	private static MineService instance;
	
	public MineService() {
		instance = this;
	}
	
	public static MineService getInstance(){
		logger.trace("Mine service instance retrieved");
		return instance;
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

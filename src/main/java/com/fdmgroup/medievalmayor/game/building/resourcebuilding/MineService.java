package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MineService {
	
	private static MineService instance;
	
	@Autowired
	public MineService() {
		instance = this;
	}
	
	public static MineService getInstance(){
		return instance;
	}
	
	public int produceResourcesForMine(Mine mine){
		return mine.produceResource();
	}
	
	public void setNumberOfPeopleInMine(int i, Mine mine) {
		mine.setNumberOfPeopleInBuilding(i);		
	}

}

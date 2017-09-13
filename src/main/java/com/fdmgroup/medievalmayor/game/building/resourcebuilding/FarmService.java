package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FarmService {


	private static FarmService instance;

	@Autowired
	public FarmService() {
		instance = this;
	}

	public static FarmService getInstance(){
		return instance;
	}

	public int produceResourcesForFarm(Farm farm){
		return farm.produceResource();
	}

	public void setNumberOfPeopleInFarm(int i, Farm farm) {
		farm.setNumberOfPeopleInBuilding(i);		
	}
} 

package com.fdmgroup.medievalmayor;

import com.fdmgroup.medievalmayor.building.resourcebuilding.Farm;

public class FarmService {

	public static class FarmServiceInstanceHolder{
		private static final FarmService INSTANCE = new FarmService(); 
	}
	
	public static FarmService getInstance(){
		return FarmServiceInstanceHolder.INSTANCE;
	}
	
	public int produceResourcesForFarm(Farm farm){
		return farm.produceResource();
	}

	public void setNumberOfPeopleInFarm(int i, Farm farm) {
		farm.setNumberOfPeopleInBuilding(i);		
	}
} 

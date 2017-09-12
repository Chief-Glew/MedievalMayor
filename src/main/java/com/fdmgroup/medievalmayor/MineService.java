package com.fdmgroup.medievalmayor;

import com.fdmgroup.medievalmayor.building.resourcebuilding.Mine;

public class MineService {
	
	private MineService() {
	}
	
	public static class MineServiceInstanceHolder{
		private static final MineService INSTANCE = new MineService();
	}
	
	public static MineService getInstance(){
		return MineServiceInstanceHolder.INSTANCE;
	}
	
	public int produceResourcesForMine(Mine mine){
		return mine.produceResource();
	}
	
	public void setNumberOfPeopleInMine(int i, Mine mine) {
		mine.setNumberOfPeopleInBuilding(i);		
	}

}

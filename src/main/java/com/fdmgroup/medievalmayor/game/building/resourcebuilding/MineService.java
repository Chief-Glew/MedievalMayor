package com.fdmgroup.medievalmayor.game.building.resourcebuilding;

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

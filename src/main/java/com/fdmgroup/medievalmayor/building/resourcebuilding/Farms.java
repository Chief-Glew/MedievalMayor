package com.fdmgroup.medievalmayor.building.resourcebuilding;

public class Farms extends ResourceBuilding{
	
	private int farmLevel;
	
	private Farms(){
	}
	
	public static class FarmsInstanceHolder{
		private static final Farms INSTANCE = new Farms();
	}
	
	public static Farms getInstance(){
		return FarmsInstanceHolder.INSTANCE;
	}
	
	public int getFarmLevel() {
		return farmLevel;
	}

	public void setFarmLevel(int farmLevel) {
		this.farmLevel = farmLevel;
	}

	@Override
	public int produceResource() {
		int foodProduced = getNumberOfPeopleInBuilding()*getFarmLevel();
		return foodProduced;
	}
	

}
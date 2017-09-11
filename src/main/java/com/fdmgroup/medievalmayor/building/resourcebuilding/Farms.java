package com.fdmgroup.medievalmayor.building.resourcebuilding;

public class Farms extends ResourceBuilding{

	private Farms(){
		
	}
	
	public static class FarmsInstanceHolder{
		private static final Farms INSTANCE = new Farms();
	}
	
	public static Farms getInstance(){
		return FarmsInstanceHolder.INSTANCE;
	}
	
	@Override
	public int produceResource() {
		int foodProduced = getNumberOfPeopleInBuilding()*3;
		return foodProduced;
	}

}

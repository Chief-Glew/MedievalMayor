package com.fdmgroup.medievalmayor.building.resourcebuilding;

public class Mines extends ResourceBuilding{

	private int numberOfMines;
	
	private Mines(){}
	
	public static class MinesInstanceHolder{
		private static final Mines INSTANCE = new Mines();
	}
	
	public static Mines getInstance(){
		return MinesInstanceHolder.INSTANCE;
	}
	
	@Override
	public int produceResource() {
		int goldProduced = getNumberOfPeopleInBuilding()*2;
		return goldProduced;
	}

	
}

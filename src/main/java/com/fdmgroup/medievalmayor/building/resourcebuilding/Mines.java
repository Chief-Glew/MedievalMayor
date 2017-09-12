package com.fdmgroup.medievalmayor.building.resourcebuilding;

public class Mines extends ResourceBuilding{	
	private int mineLevel;
	
	private Mines(){}
	
	public static class MinesInstanceHolder{
		private static final Mines INSTANCE = new Mines();
	}
	
	public static Mines getInstance(){
		return MinesInstanceHolder.INSTANCE;
	}
	
	public int getMineLevel() {
		return mineLevel;
	}

	public void setMineLevel(int mineLevel) {
		this.mineLevel = mineLevel;
	}

	@Override
	public int produceResource() {
		int goldProduced = getNumberOfPeopleInBuilding()*getMineLevel();
		return goldProduced;
	}

	
}

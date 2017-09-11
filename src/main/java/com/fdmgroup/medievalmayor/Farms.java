package com.fdmgroup.medievalmayor;

public class Farms extends ResourceBuilding{

	private Farms(){}
	
	public static class FarmsInstanceHolder{
		private static final Farms INSTANCE = new Farms();
	}
	
	public static Farms getInstance(){
		return FarmsInstanceHolder.INSTANCE;
	}
	
	@Override
	public void produceResource() {
		// TODO Auto-generated method stub
		
	}

}

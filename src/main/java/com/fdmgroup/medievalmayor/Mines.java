package com.fdmgroup.medievalmayor;

public class Mines extends ResourceBuilding{

	
	private Mines(){}
	
	public static class MinesInstanceHolder{
		private static final Mines INSTANCE = new Mines();
	}
	
	public static Mines getInstance(){
		return MinesInstanceHolder.INSTANCE;
	}
	
	@Override
	public void produceResource() {
		// TODO Auto-generated method stub
		
	}

	
}

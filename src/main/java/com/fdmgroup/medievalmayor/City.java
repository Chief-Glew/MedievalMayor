package com.fdmgroup.medievalmayor;

import com.fdmgroup.medievalmayor.building.resourcebuilding.Farms;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mines;

public class City {
	
	private int totalPopulation;
	private int unassignedPopulation;
	private int gold;
	private int food;
	private Farms farm;
	private Mines mine;
	
	private City(int unassignedPopulation){
		this.unassignedPopulation = unassignedPopulation;	
		farm = Farms.getInstance();
		mine = Mines.getInstance();
	}
	
	public static class CityInstanceHolder{
		private static final City INSTANCE = new City(10);
	}
	public static City getInstance(){
		return CityInstanceHolder.INSTANCE;
	}
	
	public int getTotalPopulation() {
		return totalPopulation;
	}

	public int getUnassignedPopulation() {
		return unassignedPopulation;
	}

	public void setUnassignedPopulation(int numberOfPeople) {
		unassignedPopulation = numberOfPeople;
	}

	public int getGold() {
		return gold;
	}

	public int getFood() {
		return food;
	}

	public void updateResources() {
		gold += mine.produceResource();
		food += farm.produceResource();
	}
}

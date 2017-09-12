package com.fdmgroup.medievalmayor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fdmgroup.medievalmayor.building.resourcebuilding.Farms;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mines;

@Entity(name="CITY")
public class City implements IdAble{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CITY_ID")
	private long cityId;
	@Column(name="TOTAL_POPULATION")
	private int totalPopulation;
	@Column(name="UNASSIGNED_POPULATION")
	private int unassignedPopulation;
	@Column(name="GOLD")
	private int gold;
	@Column(name="FOOD")
	private int food;
	@Transient
	private Farms farm;
	@Transient
	private Mines mine;

	public City(){};

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

	@Override
	public long getId() {
		return cityId;
	}
}

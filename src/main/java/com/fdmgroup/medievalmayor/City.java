package com.fdmgroup.medievalmayor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fdmgroup.medievalmayor.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mine;

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
	//@Join
	//@OneToOne("FARM")
	@Transient
	private Farm farm;
	@Transient
	private Mine mine;

	public City(){};

	public City(int totalPopulation, int food, int gold, Farm farm, Mine mine){
		this.unassignedPopulation = totalPopulation;
		this.totalPopulation = totalPopulation;
		this.food = food;
		this.gold = gold;
		this.farm = farm;
		this.mine = mine;
	}

	public int getTotalPopulation() {
		return totalPopulation;
	}

	public int getUnassignedPopulation() {
		return unassignedPopulation;
	}

	public long getCityId() {
		return cityId;
	}

	public void setTotalPopulation(int totalPopulation) {
		this.totalPopulation = totalPopulation;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setFood(int food) {
		this.food = food;
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

	@Override
	public long getId() {
		return cityId;
	}

	public Farm getFarm() {
		return farm;
	}

	public Mine getMine() {
		return mine;
	}
}
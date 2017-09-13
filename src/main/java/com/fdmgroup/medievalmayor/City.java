package com.fdmgroup.medievalmayor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mine;

@Entity(name="CITY")
public class City implements IdAble{
	
	static final Logger logger = LogManager.getLogger("City");

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
		logger.trace("Total Population retrieved");
		return totalPopulation;
	}

	public int getUnassignedPopulation() {
		logger.trace("Unassigned Population retrieved");
		return unassignedPopulation;
	}

	public long getCityId() {
		logger.trace("City Id retrieved");
		return cityId;
	}

	public void setTotalPopulation(int totalPopulation) {
		logger.trace("Total Population set");
		this.totalPopulation = totalPopulation;
	}

	public void setGold(int gold) {
		logger.trace("Gold set");
		this.gold = gold;
	}

	public void setFood(int food) {
		logger.trace("Food set");
		this.food = food;
	}

	public void setUnassignedPopulation(int numberOfPeople) {
		logger.trace("Unassigned Population set");
		unassignedPopulation = numberOfPeople;
	}

	public int getGold() {
		logger.trace("Gold retrieved");
		return gold;
	}

	public int getFood() {
		logger.trace("Food retrieved");
		return food;
	}

	@Override
	public long getId() {
		logger.trace("Id retrieved");
		return cityId;
	}

	public Farm getFarm() {
		logger.trace("Farm retrieved");
		return farm;
	}

	public Mine getMine() {
		logger.trace("Mine retrieved");
		return mine;
	}
}
package com.fdmgroup.medievalmayor.game.city;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.IdAble;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources.Resource;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources.ResourceFactory;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources.ResourceStorageFactory;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources.ResourceStorageHandler;

@Entity(name="CITY")
public class City implements IdAble{

	static final Logger logger = LogManager.getLogger("City");

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CITY_ID")
	private long cityId;
	@Column(name="CITY_NAME")
	private String cityName;
	@Column(name="TOTAL_POPULATION")
	private int totalPopulation;
	@Column(name="UNASSIGNED_POPULATION")
	private int unassignedPopulation;
	@Column(name="GOLD")
	private int gold;
	@Column(name="FOOD")
	private int food;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FARM_ID")
	private Farm farm;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="MINE_ID")
	private Mine mine;

	private ResourceStorageHandler resourceStorage;
	private Set<ResourceBuilding> resourceGenerators;
	private ResourceStorageFactory storageFactory;

	public City(){};

	public City(String cityName, int totalPopulation){
		storageFactory = new ResourceStorageFactory();
		resourceGenerators = new HashSet<ResourceBuilding>();
		this.cityName = cityName;
		this.unassignedPopulation = totalPopulation;
		this.totalPopulation = totalPopulation;
		resourceStorage = storageFactory.getPopulationStorage(totalPopulation, totalPopulation);
	}

	public City(String cityName, int totalPopulation, int food, int gold, Farm farm, Mine mine){
		this.cityName = cityName;
		this.unassignedPopulation = totalPopulation;
		this.totalPopulation = totalPopulation;
		this.food = food;
		this.gold = gold;
		this.farm = farm;
		this.mine = mine;
	}

	public City(String cityName, int totalPopulation, ResourceBuilding... resourceBuildings) {
		this(cityName, totalPopulation);
		for (ResourceBuilding resourceBuilding:resourceBuildings) {
			resourceGenerators.add(resourceBuilding);
			resourceStorage.addResourceStore(storageFactory.getStorageForResource(resourceBuilding.produceResource()));
		}
	}

	public City(String cityName, int totalPopulation, Set<ResourceBuilding> resourceBuildings) {
		this(cityName, totalPopulation);
		resourceGenerators.addAll(resourceBuildings);
		for (ResourceBuilding resourceBuilding:resourceBuildings) {
			resourceStorage.addResourceStore(storageFactory.getStorageForResource(resourceBuilding.produceResource()));
		}
	}

	public int getTotalPopulation() {
		logger.trace("Total Population retrieved");
		return totalPopulation;
	}

	public int getUnassignedPopulation() {
		logger.trace("Unassigned Population retrieved");
		return getResources().get("Population");
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
		Map<String, Integer> newPop = new HashMap<String, Integer>();
		newPop.put("Population", numberOfPeople);
		setResources(newPop);
		unassignedPopulation = numberOfPeople;
	}

	public int getGold() {
		logger.trace("Gold retrieved");
		return getResources().get("Gold");
	}

	public int getFood() {
		logger.trace("Food retrieved");
		return getResources().get("Food");
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

	public String getCityName() {
		return cityName;
	}

	public void addResourceStore(ResourceStorageHandler handler) {
		resourceStorage.addResourceStore(handler);
	}

	public void addResource(Resource resource) {
		resourceStorage.addResource(resource);
	}

	public ResourceBuilding getResourceBuildingOfType(Class<? extends ResourceBuilding> type) {
		for (ResourceBuilding building: resourceGenerators) {
			if (type.equals(building.getClass())) {
				return building;
			}
		}
		return null;
	}

	public void addResourceBuilding(ResourceBuilding resourceBuilding) {
		ResourceStorageFactory resourceStorageFactory = new ResourceStorageFactory();
		resourceGenerators.add(resourceBuilding);
		resourceStorage.addResourceStore(resourceStorageFactory.getStorageForResource(resourceBuilding.produceResource()));
	}

	public Map<String, Integer> getResources(){
		return resourceStorage.getResources();
	}

	public Set<ResourceBuilding> getResourceGenerators(){
		return resourceGenerators;
	}

	public void setResources(Map<String, Integer> resources) {
		resourceStorage.setResources(resources);
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", totalPopulation=" + totalPopulation + ", unassignedPopulation="
				+ unassignedPopulation + ", gold=" + gold + ", food=" + food + "]";
	}


}
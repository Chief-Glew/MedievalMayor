package com.fdmgroup.medievalmayor.game.city;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.IdAble;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resources.Resource;
import com.fdmgroup.medievalmayor.game.resources.ResourceStorageFactory;
import com.fdmgroup.medievalmayor.game.resources.ResourceStorageHandler;

@Entity(name="CITY")
public class City implements IdAble{

	static final Logger logger = LogManager.getLogger("City.class");

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CITY_ID")
	private long cityId;
	@Column(name="CITY_NAME")
	private String cityName;
	@Column(name="CITY_YEAR")
	private int cityYear;
	@Column(name="TOTAL_POPULATION") 
	private int totalPopulation;
	@OneToOne(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="RESOURCE_ID")
	private ResourceStorageHandler resourceStorage;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="CITY_RESOURCE_PRODUCERS", joinColumns=@JoinColumn(name="CITY_ID"), inverseJoinColumns=@JoinColumn(name="RESOURCE_PRODUCER_ID"))
	private Set<ResourceProducer> resourceProducers;
	@Transient  
	private ResourceStorageFactory storageFactory;

	public City(){};

	public City(String cityName, int totalPopulation){
		storageFactory = new ResourceStorageFactory();
		resourceProducers = new HashSet<ResourceProducer>();
		this.cityName = cityName;
		this.totalPopulation = totalPopulation;
		resourceStorage = storageFactory.getPopulationStorage(totalPopulation, totalPopulation);
	}

	public City(String cityName, int totalPopulation, ResourceProducer... resourceBuildings) {
		this(cityName, totalPopulation);
		for (ResourceProducer resourceBuilding:resourceBuildings) {
			resourceProducers.add(resourceBuilding);
			resourceStorage.addResourceStore(storageFactory.getStorageForResource(resourceBuilding.produceResource()));
		}
	}

	public City(String cityName, int totalPopulation, Set<ResourceProducer> resourceBuildings) {
		this(cityName, totalPopulation);
		resourceProducers.addAll(resourceBuildings);
		for (ResourceProducer resourceBuilding:resourceBuildings) {
			resourceStorage.addResourceStore(storageFactory.getStorageForResource(resourceBuilding.produceResource()));
		}
	}

	public int getTotalPopulation() {
		logger.debug("Total Population retrieved");
		return totalPopulation;
	}

	public int getUnassignedPopulation() {
		logger.debug("Unassigned Population retrieved");
		return getResources().get("Population");
	}

	public long getCityId() {
		logger.debug("City Id retrieved");
		return cityId;
	}
	

	public int getCityYear() {
		logger.debug("City year retrieved");
		return cityYear;
	}

	public void setCityYear(int cityYear) {
		logger.debug("City year set");
		this.cityYear = cityYear;
	}

	public void setTotalPopulation(int totalPopulation) {
		logger.debug("Total Population set");
		this.totalPopulation = totalPopulation;
	}

	public void setUnassignedPopulation(int numberOfPeople) {
		logger.debug("Unassigned Population set");
		Map<String, Integer> newPop = new HashMap<String, Integer>();
		newPop.put("Population", numberOfPeople);
		setResources(newPop);
	}

	public int getGold() {
		logger.debug("Gold retrieved");
		return getResources().get("Gold");
	}

	public int getFood() {
		logger.debug("Food retrieved");
		return getResources().get("Food");
	}

	@Override
	public long getId() {
		logger.debug("Id retrieved");
		return cityId;
	}

	public String getCityName() {
		logger.debug("City name retrieved");
		return cityName;
	}

	public void addResourceStore(ResourceStorageHandler handler) {
		logger.debug("ResourceStore added");
		resourceStorage.addResourceStore(handler);
	}

	public void addResource(Resource resource) {
		logger.debug("Resource added");
		resourceStorage.addResource(resource);
	}

	public ResourceProducer getResourceProducerOfType(Class<? extends ResourceProducer> type) {
		for (ResourceProducer building: resourceProducers) {
			if (type.equals(building.getClass())) {
				logger.debug("Resource building retrieved");
				return building;
			}
		}
		logger.debug("getReesourceBuildingOfType method not working as intended");
		return null;
	}

	public void addResourceBuilding(ResourceProducer resourceBuilding) {
		ResourceStorageFactory resourceStorageFactory = new ResourceStorageFactory();
		resourceProducers.add(resourceBuilding);
		resourceStorage.addResourceStore(resourceStorageFactory.getStorageForResource(resourceBuilding.produceResource()));
		logger.debug("Resource Building Added");
	}

	public Map<String, Integer> getResources(){
		logger.debug("Resources retrieved");
		return resourceStorage.getResources();
	}

	public Set<ResourceProducer> getResourceGenerators(){
		logger.debug("ResourceGenerators retrieved");
		return resourceProducers;
	}

	public void setResources(Map<String, Integer> resources) {
		logger.debug("Resources set");
		resourceStorage.setResources(resources);
	}
	
	public int getResourceAmount(String resourceName) {
		Map<String, Integer> resources = getResources();
		return resources.get(resourceName);
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", totalPopulation=" + totalPopulation
				+ ", resourceStorage=" + resourceStorage + ", resourceGenerators=" + resourceProducers
				+ ", storageFactory=" + storageFactory + "]";
	}

}
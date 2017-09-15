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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.IdAble;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceStorageFactory;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceStorageHandler;

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
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="RESOURCE_ID")
	private ResourceStorageHandler resourceStorage;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CITY_RESOURCE_PRODUCERS", joinColumns=@JoinColumn(name="CITY_ID"), inverseJoinColumns=@JoinColumn(name="RESOURCE_PRODUCER_ID"))
	private Set<ResourceProducer> resourceGenerators;
	@Transient  
	private ResourceStorageFactory storageFactory;

	public City(){};
	
	public City(String cityName, int totalPopulation){
		storageFactory = new ResourceStorageFactory();
		resourceGenerators = new HashSet<ResourceProducer>();
		this.cityName = cityName;
		this.totalPopulation = totalPopulation;
		resourceStorage = storageFactory.getPopulationStorage(totalPopulation, totalPopulation);
	}

	//constructor for new resource management
	public City(String cityName, int totalPopulation, ResourceProducer... resourceBuildings) {
		this(cityName, totalPopulation);
		for (ResourceProducer resourceBuilding:resourceBuildings) {
			resourceGenerators.add(resourceBuilding);
			resourceStorage.addResourceStore(storageFactory.getStorageForResource(resourceBuilding.produceResource()));
		}
	}

	//constructor for new resource management
	public City(String cityName, int totalPopulation, Set<ResourceProducer> resourceBuildings) {
		this(cityName, totalPopulation);
		resourceGenerators.addAll(resourceBuildings);
		for (ResourceProducer resourceBuilding:resourceBuildings) {
			resourceStorage.addResourceStore(
					storageFactory.getStorageForResource(
							resourceBuilding.produceResource()
							)
					);
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

	public void setUnassignedPopulation(int numberOfPeople) {
		logger.trace("Unassigned Population set");
		Map<String, Integer> newPop = new HashMap<String, Integer>();
		newPop.put("Population", numberOfPeople);
		setResources(newPop);
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

	public String getCityName() {
		return cityName;
	}

	//method for new resource management
	public void addResourceStore(ResourceStorageHandler handler) {
		resourceStorage.addResourceStore(handler);
	}

	//method for new resource management
	public void addResource(Resource resource) {
		resourceStorage.addResource(resource);
	}

	//method for new resource management
	public ResourceProducer getResourceBuildingOfType(Class<? extends ResourceProducer> type) {
		for (ResourceProducer building: resourceGenerators) {
			if (type.equals(building.getClass())) {
				return building;
			}
		}
		return null;
	}

	//method for new resource management
	public void addResourceBuilding(ResourceProducer resourceBuilding) {
		ResourceStorageFactory resourceStorageFactory = new ResourceStorageFactory();
		resourceGenerators.add(resourceBuilding);
		resourceStorage.addResourceStore(
				resourceStorageFactory.getStorageForResource(
						resourceBuilding.produceResource()
						)
				);
	}
	
	//method for new resource management
	public Map<String, Integer> getResources(){
		return resourceStorage.getResources();
	}

	//method for new resource management
	public Set<ResourceProducer> getResourceGenerators(){
		return resourceGenerators;
	}
	
	public void setResources(Map<String, Integer> resources) {
		resourceStorage.setResources(resources);
	}
	
}
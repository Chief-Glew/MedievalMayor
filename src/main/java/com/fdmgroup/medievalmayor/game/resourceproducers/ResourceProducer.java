package com.fdmgroup.medievalmayor.game.resourceproducers;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceFactory;

@Entity(name="RESOURCE_PRODUCER")
@DiscriminatorColumn(name="RESOURCE_PRODUCER_TYPE")
public abstract class ResourceProducer{
	
	static final Logger logger = LogManager.getLogger("ResourceProducer.class");
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RESOURCE_PRODUCER_ID")
	private long resourceProducerId;
	@Column(name="NUMBER_ASSIGNED_WORKERS")
	private int numberOfAssignedWorkers;
	@Column(name="BASE_RESOURCE_PRODUCTION")
	private int baseResourceProduction;
	@Column(name="RESOURCE_PRODUCER_COST")
	private int resourceProducerCost;
	@Column(name="RESOURCE_PRODUCER_NAME")
	private String resourceProducerName;
	@Column(name="PRODUCER_LEVEL")
	private int producerLevel;
	@Column(name="UPGRADE_MULTIPLIER")
	private int upgradeMultiplier;
	@Transient
	protected ResourceFactory resourceFactory;
	
	public ResourceProducer() {
		resourceFactory = new ResourceFactory();
		baseResourceProduction = 3;
		upgradeMultiplier = 3;
		producerLevel = 0;
	}

	public ResourceProducer(int baseResourceProduction) {
		this();
		this.baseResourceProduction = baseResourceProduction;
	}
	
	public ResourceProducer(int baseResourceProduction, int upgradeMultiplier) {
		this(baseResourceProduction);
		this.upgradeMultiplier = upgradeMultiplier;
	}
	



	public ResourceProducer(int numberOfAssignedWorkers, int baseResourceProduction, int resourceProducerCost,
			String resourceProducerName, int producerLevel, int upgradeMultiplier) {
		this(baseResourceProduction, upgradeMultiplier);
		this.numberOfAssignedWorkers = numberOfAssignedWorkers;
		this.resourceProducerCost = resourceProducerCost;
		this.resourceProducerName = resourceProducerName;
		this.producerLevel = producerLevel;
	}

	public abstract Resource produceResource();
	
	public void setBaseResourceProduction(int baseResourceProduction) {
		logger.trace("Base Resource Production set for "+resourceProducerName);
		this.baseResourceProduction = baseResourceProduction;
	}

	public int getBaseResourceProduction() {
		logger.trace("Base Resource Production retrieved for "+resourceProducerName);
		return baseResourceProduction;
	}

	public int getNumberOfAssignedWorkers() {
		logger.trace("Number of assigned Workers retrieved for "+resourceProducerName);
		return numberOfAssignedWorkers;
	}

	public void setNumberOfAssignedWorkers(int numberOfPeople) {
		logger.trace("Number of assigned Workers set for "+resourceProducerName);
		numberOfAssignedWorkers = numberOfPeople;
	}

	public String resourceProducerName() {
		return resourceProducerName;
	}

	public int getProducerLevel() {
		return producerLevel;
	}

	public int getUpgradeMultiplier() {
		return upgradeMultiplier;
	}

	public void setUpgradeMultiplier(int upgradeMultiplier) {
		this.upgradeMultiplier = upgradeMultiplier;
	}

	public void setProducerLevel(int producerLevel) {
		this.producerLevel = producerLevel;
	}
	
	public void incrementProducerLevel() {
		producerLevel++;
	}
	
}

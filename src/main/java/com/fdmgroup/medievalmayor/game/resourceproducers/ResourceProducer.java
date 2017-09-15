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
	@Column(name="RESOURCE_MULTIPLIER")
	private int multiplier;
	@Column(name="RESOURCE_PRODUCER_COST")
	private int resourceProducerCost;
	@Column(name="RESOURCE_PRODUCER_NAME")
	private String resourceProducerName;
	@Transient
	protected ResourceFactory resourceFactory;
	
	public ResourceProducer() {
		resourceFactory = new ResourceFactory();
	}

	public ResourceProducer(int multiplier) {
		this();
		this.multiplier = multiplier;
	}
	

	public ResourceProducer(int numberOfAssignedWorkers, int multiplier, int resourceProducerCost,
			String resourceProducerName) {
		this(multiplier);
		this.numberOfAssignedWorkers = numberOfAssignedWorkers;
		this.resourceProducerCost = resourceProducerCost;
		this.resourceProducerName = resourceProducerName;
	}

	public abstract Resource produceResource();
	
	public void setMultiplier(int multiplier) {
		logger.trace("Multiplier set");
		this.multiplier = multiplier;
	}

	public int getMultiplier() {
		logger.trace("Multiplier retrieved");
		return multiplier;
	}

	public int getNumberOfAssignedWorkers() {
		logger.trace("Number of assigned Workers retrieved");
		return numberOfAssignedWorkers;
	}

	public void setNumberOfPeopleInBuilding(int numberOfPeople) {
		logger.trace("Number of assigned Workers set");
		numberOfAssignedWorkers = numberOfPeople;
	}

	public String resourceProducerName() {
		return resourceProducerName;
	}
}

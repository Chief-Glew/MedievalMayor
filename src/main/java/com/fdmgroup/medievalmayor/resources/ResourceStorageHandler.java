package com.fdmgroup.medievalmayor.resources;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Entity(name="RESOURCE_STORAGE")
public class ResourceStorageHandler extends Resource{
	
	private static final Logger logger = LogManager.getLogger("ResourceStorageHandler.class");

	@Column(name="CAPACITY")
	private int capacity;
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name= "NEXT_RESOURCE", nullable=true)
	private ResourceStorageHandler next;

	public ResourceStorageHandler(){
		super(0, "default");
	}
	
	public ResourceStorageHandler(int ammount, String type, int capacity) {
		super(ammount, type);
		this.capacity = capacity;
	}

	public void addResourceStore(ResourceStorageHandler handler) {
		if(isSameResourceType(handler)) {
			capacity += handler.capacity;
		}
		else {
			if (next==null) {
				next = handler;
			}
			else {
				next.addResourceStore(handler);
			}
		}
		logger.debug("addResourceStore method used");
	}

	public void addResource(Resource resource) throws NullPointerException {
		if (this.isSameTypeOfResource(resource)) {
			amount += resource.amount;
		}
		else {
			next.addResource(resource);
		}
		logger.debug("addResource method used");
	}
	
	public Map<String, Integer> getResources(){
		Map<String, Integer> resources;
		if (next==null) {
			resources = new HashMap<String, Integer>();
		}
		else {
			resources = next.getResources();
		}
		resources.put(getResourceType(), amount);
		logger.debug("getResource method used");
		return resources;
	}
	
	public void setResources(Map<String, Integer> resources) {
		if (resources.containsKey(getResourceType())) {
			amount = resources.get(getResourceType());
		}
		if (next!=null) {
			next.setResources(resources);
		}
		logger.debug("setResources method used");
	}

	private boolean isSameResourceType(ResourceStorageHandler handler) throws NullPointerException {
		logger.debug("isSameResourceType method used");
		return this.isSameTypeOfResource(handler);
	}
}

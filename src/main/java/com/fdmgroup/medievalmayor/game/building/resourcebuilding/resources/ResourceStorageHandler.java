package com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorageHandler extends Resource{

	private int capacity;
	private ResourceStorageHandler next;

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
	}

	public void addResource(Resource resource) throws NullPointerException {
		if (this.isSameTypeOfResource(resource)) {
			ammount += resource.ammount;
		}
		else {
			next.addResource(resource);
		}
	}
	
	public Map<String, Integer> getResources(){
		Map<String, Integer> resources;
		if (next==null) {
			resources = new HashMap<String, Integer>();
		}
		else {
			resources = next.getResources();
		}
		resources.put(getResourceType(), ammount);
		return resources;
	}
	
	public void setResources(Map<String, Integer> resources) {
		if (resources.containsKey(getResourceType())) {
			ammount = resources.get(getResourceType());
		}
		if (next!=null) {
			next.setResources(resources);
		}
	}

	private boolean isSameResourceType(ResourceStorageHandler handler) throws NullPointerException {
		return this.isSameTypeOfResource(handler);
	}
}

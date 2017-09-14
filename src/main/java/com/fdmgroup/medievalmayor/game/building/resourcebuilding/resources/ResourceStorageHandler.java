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
		if(canAddCapacity(handler)) {}
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
		if (this.equals(resource)) {
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
		resources.put(getType(), ammount);
		return resources;
	}
	
	public void setResources(Map<String, Integer> resources) {
		if (resources.containsKey(getType())) {
			ammount = resources.get(getType());
		}
		if (next!=null) {
			next.setResources(resources);
		}
	}

	private boolean canAddCapacity(ResourceStorageHandler handler) throws NullPointerException {
		if (this.equals(handler)) {
			capacity += handler.capacity;
		}
		else {
			return false;
		}
		return true;
	}




}

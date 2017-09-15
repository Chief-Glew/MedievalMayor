package com.fdmgroup.medievalmayor.game.building.resourcebuilding.resources;

public class Resource {

	protected int ammount;
	private String resourceType;
	
	public Resource(int ammount, String resourceType) {
		this.ammount = ammount;
		this.resourceType = resourceType;
	}
	
	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public String getResourceType() {
		return resourceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (resourceType == null) {
			if (other.resourceType != null)
				return false;
		} else if (!resourceType.equals(other.resourceType))
			return false;
		return true;
	}
	
	public boolean isSameTypeOfResource(Resource resource){
		return resourceType.equals(resource.resourceType);
	}
	
}

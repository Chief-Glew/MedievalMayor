package com.fdmgroup.medievalmayor.game.resourceproducers.resources;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Resource {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RESOURCE_ID")
	private long resourceId;
	@Column(name="RESOURCE_AMOUNT")
	protected int amount;
	@Column(name="RESOURCE_TYPE")
	private String resourceType;
	
	public Resource() {
	}
	
	public Resource(int ammount, String resourceType) {
		this.amount = ammount;
		this.resourceType = resourceType;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int ammount) {
		this.amount = ammount;
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

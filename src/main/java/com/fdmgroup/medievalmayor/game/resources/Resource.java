package com.fdmgroup.medievalmayor.game.resources;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@MappedSuperclass
public class Resource {
	
	private static final Logger logger = LogManager.getLogger("Resource.class");

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
		logger.debug("Amount retrieved");
		return amount;
	}

	public void setAmount(int ammount) {
		logger.debug("Amount set");
		this.amount = ammount;
	}

	public String getResourceType() {
		logger.debug("Resource type retrieved");
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
		logger.debug("IsSameTypeOfResource method used");
		return resourceType.equals(resource.resourceType);
	}
}

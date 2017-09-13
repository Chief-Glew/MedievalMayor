package com.fdmgroup.medievalmayor.building;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.IdAble;

@MappedSuperclass
public abstract class Building implements IdAble{
	
	static final Logger logger = LogManager.getLogger("Building");
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BUILDING_ID")
	private long buildingId;
	@Column(name="BUILDING_COST")
	private int buildingCost;
	@Column(name="BUILDING_NAME")
	private String buildingName;
	
	public long getId(){
		logger.trace("Id retrieved");
		return buildingId;
	}
}

package com.fdmgroup.medievalmayor.building;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fdmgroup.medievalmayor.IdAble;

@MappedSuperclass
public abstract class Building implements IdAble{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BUILDING_ID")
	private long buildingId;
	@Column(name="BUILDING_COST")
	private int buildingCost;
	@Column(name="BUILDING_NAME")
	private String buildingName;
	
	public long getId(){
		return buildingId;
	}
}

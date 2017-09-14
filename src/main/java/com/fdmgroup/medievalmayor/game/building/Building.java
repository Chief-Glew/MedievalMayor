package com.fdmgroup.medievalmayor.game.building;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fdmgroup.medievalmayor.game.IdAble;

@MappedSuperclass
public abstract class Building {
	
	static final Logger logger = LogManager.getLogger("Building");
	
	@Column(name="BUILDING_COST")
	private int buildingCost;
	@Column(name="BUILDING_NAME")
	private String buildingName;
	
}

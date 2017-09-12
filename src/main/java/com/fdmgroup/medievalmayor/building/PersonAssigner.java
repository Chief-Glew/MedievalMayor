package com.fdmgroup.medievalmayor.building;

import com.fdmgroup.medievalmayor.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

public interface PersonAssigner {

	public int assignPeopleToBuilding(int people, int availiblePeople, ResourceBuilding rb) throws InsufficentPopulationException, AssignedNegativeNumberException;
	public int getPeopleInBuilding(ResourceBuilding rb);
}

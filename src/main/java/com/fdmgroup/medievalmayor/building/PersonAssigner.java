package com.fdmgroup.medievalmayor.building;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public interface PersonAssigner {

	public int assignPeopleToBuilding(int people, int availiblePeople, ResourceBuilding rb) throws InsufficentPopulationException, AssignedNegativeNumberException;
	public int getPeopleInBuilding(ResourceBuilding rb);
}

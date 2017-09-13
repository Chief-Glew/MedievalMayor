package com.fdmgroup.medievalmayor.game.building;

import com.fdmgroup.medievalmayor.game.building.resourcebuilding.ResourceBuilding;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public interface PersonAssigner {

	public void assignPeopleToBuilding(int people, ResourceBuilding rb) throws InsufficentPopulationException, AssignedNegativeNumberException;
	public int getPeopleInBuilding(ResourceBuilding rb);
}

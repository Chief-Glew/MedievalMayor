package com.fdmgroup.medievalmayor;

public interface PersonAssigner {

	public void assignPeopleToBuilding(int people, ResourceBuilding rb);
	public void removePeopleFromBuilding(int people, ResourceBuilding rb);
	public int getPeopleInBuilding(ResourceBuilding rb);
}

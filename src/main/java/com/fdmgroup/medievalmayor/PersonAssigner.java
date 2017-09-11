package com.fdmgroup.medievalmayor;

public interface PersonAssigner {

	public void assignPeopleToBuilding(int people, ResourceBuilding rb);
	public void remouvePeopleFromBuilding(int people, ResourceBuilding rb);
	public int getPeopleFromBuilding(int people, ResourceBuilding rb);
}

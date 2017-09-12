package com.fdmgroup.medievalmayor.CRUD.building;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.CRUD.GenericCrud;
import com.fdmgroup.medievalmayor.building.Building;

public class BuildingJPACRUD<T> implements GenericCrud<Building> {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private static final Logger logger = LogManager.getLogger("medievalMayor Log");

	protected void connect(){
		entityManagerFactory = Persistence.createEntityManagerFactory("medievalMayor");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		logger.trace("Connect Method used in BuildingJPACRUD");
	}

	private void disconnect(){
		entityManager.close();
		entityManagerFactory.close();
		logger.trace("Disconnect Method used in BuildingJPACRUD");
	}

	public void create(Building building) {
		connect();
		entityManager.persist(building);
		entityManager.flush();
		entityManager.getTransaction().commit();
		logger.trace("Create Method used in BuildingJPACRUD");
		disconnect();
	}

	public Building read(long id) {
		connect();
		Building building = entityManager.find(Building.class, id);
		logger.trace("Read Method used in BuildingJPACRUD");
		disconnect();
		return building;
	}

	public Set<Building> readAll() {
		connect();
		List<Building> buildings = new ArrayList<Building>();
		TypedQuery<Building> typedQuery = 
				entityManager.createQuery("SELECT b FROM BUILDING b", Building.class);
		buildings = typedQuery.getResultList();
		logger.trace("ReadAll Method used in BuildingJPACRUD");
		Set<Building> buildingSet = new HashSet<Building>();
		buildingSet.addAll(buildings);
		disconnect();
		return buildingSet;
	}

	public void update(Building building) {
		connect();
		entityManager.find(Building.class, building.getId());
		entityManager.merge(building);
		logger.trace("Update Method used in BuildingJPACRUD");
		disconnect();
	}

	public void delete(long id) {
		Building buildingToRemove = read(id);
		connect();
		Building placeHolderBuilding = entityManager.find(Building.class, buildingToRemove.getId());
		entityManager.remove(placeHolderBuilding);
		entityManager.flush();
		entityManager.getTransaction().commit();
		logger.trace("Delete Method used in BuildingJPACRUD");
		disconnect();
	}
}

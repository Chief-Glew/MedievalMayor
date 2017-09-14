package com.fdmgroup.medievalmayor.CRUD;


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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.City;

@Component
@Qualifier("JPA")
public class CityJPACRUD implements GenericCrud<City> {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private static final Logger logger = LogManager.getLogger("CityJPACRUD");

	protected void connect(){
		entityManagerFactory = Persistence.createEntityManagerFactory("medievalMayor");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		logger.trace("Connect Method used in CityJPACRUD");
	}

	private void disconnect(){
		entityManager.close();
		entityManagerFactory.close();
		logger.trace("Disconnect Method used in CityJPACRUD");
	}

	public void create(City city) {
		connect();
		entityManager.persist(city);
		entityManager.flush();
		entityManager.getTransaction().commit();
		logger.trace("Create Method used in CityJPACRUD");
		disconnect();
	}

	public City read(long id) {
		connect();
		City city = entityManager.find(City.class, id);
		logger.trace("Read Method used in CityJPACRUD");
		disconnect();
		return city;
	}

	public Set<City> readAll() {
		connect();
		List<City> citys = new ArrayList<City>();
		TypedQuery<City> typedQuery = 
				entityManager.createQuery("SELECT c FROM CITY c", City.class);
		citys = typedQuery.getResultList();
		logger.trace("ReadAll Method used in CityJPACRUD");
		Set<City> citySet = new HashSet<City>();
		citySet.addAll(citys);
		disconnect();
		return citySet;
	}

	public void update(City city) {
		connect();
		entityManager.find(City.class, city.getId());
		entityManager.merge(city);
		logger.trace("Update Method used in CityJPACRUD");
		disconnect();
	}

	public void delete(long id) {
		City cityToRemove = read(id);
		connect();
		City placeHolderCity = entityManager.find(City.class, cityToRemove.getId());
		entityManager.remove(placeHolderCity);
		entityManager.flush();
		entityManager.getTransaction().commit();
		logger.trace("Delete Method used in CityJPACRUD");
		disconnect();
	}
}

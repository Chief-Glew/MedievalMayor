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
import org.springframework.stereotype.Component;

import com.fdmgroup.medievalmayor.game.city.MultiplayerGame;

@Component
public class MultiplayerGameCrud implements GenericCrud<MultiplayerGame> {
	private static final Logger logger = LogManager.getLogger("MultiplayerGameJPACRUD.class");

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	protected void connect(){
		entityManagerFactory = Persistence.createEntityManagerFactory("medievalMayor");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		logger.debug("Connect Method used in MultiplayerGameJPACrud");
	}

	private void disconnect(){
		entityManager.close();
		entityManagerFactory.close();
		logger.debug("Disconnect Method used in MultiplayerGameJPACrud");
	}

	public void create(MultiplayerGame multiplayerGame) {
		connect();
		entityManager.persist(multiplayerGame);
		entityManager.flush();
		entityManager.getTransaction().commit();
		logger.debug("Create Method used in MultiplayerGameJPACrud");
		disconnect();
	}

	public MultiplayerGame read(long id) {
		connect();
		MultiplayerGame multiplayerGame = entityManager.find(MultiplayerGame.class, id);
		logger.debug("Read Method used in MultiplayerGameJPACrud");
		disconnect();
		return multiplayerGame;
	}

	public Set<MultiplayerGame> readAll() {
		connect();
		List<MultiplayerGame> multiplayerGames = new ArrayList<MultiplayerGame>();
		TypedQuery<MultiplayerGame> typedQuery = 
				entityManager.createQuery("SELECT m FROM MULTIPLAYERGAME m", MultiplayerGame.class);
		multiplayerGames = typedQuery.getResultList();
		logger.debug("ReadAll Method used in MultiplayerGameJPACrud");
		Set<MultiplayerGame> multiplayerGameSet = new HashSet<MultiplayerGame>();
		multiplayerGameSet.addAll(multiplayerGames);
		disconnect();
		return multiplayerGameSet;
	}

	public void update(MultiplayerGame multiplayerGame) {
		connect();
		entityManager.find(MultiplayerGame.class, multiplayerGame.getId());
		entityManager.merge(multiplayerGame);
		entityManager.flush();
		entityManager.getTransaction().commit();
		logger.debug("Update Method used in MultiplayerGameJPACrud");
		disconnect();
	}

	public void delete(long id) {
		MultiplayerGame multiplayerGameToRemove = read(id);
		connect();
		MultiplayerGame placeHolderMultiplayerGame = entityManager.find(MultiplayerGame.class, multiplayerGameToRemove.getId());
		entityManager.remove(placeHolderMultiplayerGame);
		entityManager.flush();
		entityManager.getTransaction().commit();
		logger.debug("Delete Method used in MultiplayerGameJPACrud");
		disconnect();
	}
}


package com.fdmgroup.medievalmayor.game.city;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.IdAble;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;

@Entity(name="MULTIPLAYER_GAME")
public class MultiplayerGame implements IdAble{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MULTIPLAYER_GAME_ID")
	private long multiplayerGameId;
	@ElementCollection
	@CollectionTable
	private Map<City, Boolean> cities;
	@Transient
	private ClientCommand clientCommand;
	
	protected MultiplayerGame(){
		ApplicationContext 	applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		clientCommand = applicationContext.getBean(ClientCommand.class);
		((ConfigurableApplicationContext)applicationContext).close();
	}

	public MultiplayerGame(City... cities) {
		this();
		this.cities = new HashMap<City, Boolean>();
		for (City city:cities){
			this.cities.put(city, false);
		}
	}

	public Set<City> getCities() {
		return cities.keySet();
	}

	@Override
	public long getId() {
		return multiplayerGameId;
	}

	public boolean isReady(City city) {
		return cities.get(city);
	}
	
	public void setReady(City city) throws GameOverException{
		cities.put(city, true);
		if (allCitiesReady()){
			doNextTurn();
		}
	}

	private void doNextTurn() throws GameOverException {
		for (City city: cities.keySet()){
			clientCommand.nextTurn(city);
		}
	}

	private boolean allCitiesReady() {
		return !cities.values().contains(false);
	}

}

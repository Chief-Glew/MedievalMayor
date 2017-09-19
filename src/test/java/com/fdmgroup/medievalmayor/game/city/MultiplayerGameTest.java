package com.fdmgroup.medievalmayor.game.city;


import static org.junit.Assert.assertFalse;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.config.AppConfig;

public class MultiplayerGameTest {

	private MultiplayerGame multiplayerGame;
	private MultiplayerGameFactory multiplayerGameFactory;
	
	@Before
	public void init(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		multiplayerGameFactory = applicationContext.getBean(MultiplayerGameFactory.class);
		multiplayerGame = multiplayerGameFactory.getTwoPlayerGame("city1", "city2");
		((ConfigurableApplicationContext)applicationContext).close();
	}
	
	@Test
	public void testThatIsReady_City_ReturnsFalseForANewMulitplayerGame(){
		Set<City> cities = multiplayerGame.getCities();
		City city = null;
		for (City localCity: cities){
			city = localCity;
		}
		assertFalse(multiplayerGame.isReady(city));
	}
}

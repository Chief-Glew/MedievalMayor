package com.fdmgroup.medievalmayor.game.city;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.city.City;
import com.fdmgroup.medievalmayor.config.AppConfig;

public class MultiplayerGameTest {

	private MultiplayerGame multiplayerGame;
	private MultiplayerGameFactory multiplayerGameFactory;
	
	@Before
	public void init(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		multiplayerGameFactory = applicationContext.getBean(MultiplayerGameFactory.class);
		multiplayerGame = multiplayerGameFactory.getTwoPlayerGame("city1", "city2");
	}
	
	@Test
	public void testThatIsReady_City_ReturnsFalseForANewMulitplayerGame(){
		Set<City> cities = multiplayerGame.getCities();
		for (City city: cities){
			
		}
		assertFalse(multiplayerGame.isReady())
	}
}

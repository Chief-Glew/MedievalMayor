package com.fdmgroup.medievalmayor.game.city;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;

public class UpdateCityYearCommandTest {
	
	private ClientCommand clientCommand;
	private CityFactory cityFactory;
	private City city;
	
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		cityFactory = applicationContext.getBean(CityFactory.class);
		city = cityFactory.getNewCity();
		clientCommand = applicationContext.getBean(ClientCommand.class);
	}
	
	@Test
	public void testThatUpdateCityYearCommandIncreasesTheCityYearBy1() throws GameOverException{
		clientCommand.nextTurn(city);
		assertEquals(1,city.getCityYear());
	}
}

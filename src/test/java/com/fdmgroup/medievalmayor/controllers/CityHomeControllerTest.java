package com.fdmgroup.medievalmayor.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.CRUD.GenericRead;
import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.city.City;


public class CityHomeControllerTest {
	private CityHomeController cityHomeController;
	private Model modelMock;
	private GenericRead<City> readCrud;

	@Before
	public void init(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		cityHomeController = applicationContext.getBean(CityHomeController.class);
		modelMock = mock(Model.class);
	}

	@Test
	public void testThatShowCitiesWillDirectToIndexJSP(){
		assertEquals("index", cityHomeController.showCities(modelMock));
	}
	
	@Test
	public void testThatDisplayCityStatsWillDirectToNewUserHomeJSP(){
		assertEquals("newUserHome", cityHomeController.displayCityStats("1", modelMock));
	}
	
	@Test
	public void testThatDisplayAdminPageWillDirectToAdminPageJSP(){
		assertEquals("adminPage", cityHomeController.displayAdminPage("1", modelMock));
	}
	
	@Test
	public void testThatDisplayAdminPageForResourseProducerWillDirectToResourceProducerAdminPageJSP(){
		assertEquals("resourceProducerAdminPage", cityHomeController.displayAdminPageForResourseProducer("1", "Farm", modelMock));
	}
	
	@Test
	public void testThatDisplayAdminPageForResourseProducerWillDirectToRWrongTurnPageJSPIfGivenNull(){
		assertEquals("wrongTurnPage", cityHomeController.displayAdminPageForResourseProducer("1", null, modelMock));
	}
	
	
}

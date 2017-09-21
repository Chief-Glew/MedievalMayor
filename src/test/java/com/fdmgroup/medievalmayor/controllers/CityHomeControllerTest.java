package com.fdmgroup.medievalmayor.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.config.AppConfig;


public class CityHomeControllerTest {
	private SingleplayerController singleplayerController;
	private Model modelMock;
	private ApplicationContext applicationContext;
	
	@Before
	public void init(){
		applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		singleplayerController = applicationContext.getBean(SingleplayerController.class);
		modelMock = mock(Model.class);
	}

	@Test
	public void testThatShowCitiesWillDirectToIndexJSP(){
		assertEquals("index", singleplayerController.showCities(modelMock));
	}
	
	@Test
	public void testThatDisplayCityStatsWillDirectToNewUserHomeJSP(){
		assertEquals("newUserHome", singleplayerController.displayCityStats("1", null, modelMock));
	}
	
	@Test
	public void testThatDisplayAdminPageWillDirectToAdminPageJSP(){
		assertEquals("adminPage", singleplayerController.displayAdminPage("1", null, modelMock));
	}
	
	@Test
	public void testThatDisplayAdminPageForResourseProducerWillDirectToResourceProducerAdminPageJSP(){
		assertEquals("resourceProducerAdminPage", singleplayerController.displayAdminPageForResourseProducer("1", "Farm", null, modelMock));
	}
	
	@Test
	public void testThatDisplayAdminPageForResourseProducerWillDirectToRWrongTurnPageJSPIfGivenNull(){
		assertEquals("wrongTurnPage", singleplayerController.displayAdminPageForResourseProducer("1", null, null, modelMock));
	}
	
	@Test
	public void testThatUpdateAdminValuesForResourceProducerWillDirectAssignationPage(){
		assertEquals("assignationPage", singleplayerController.updateAdminValuesForResourceProducer("1", "Farm", "2", "2", modelMock));
	}
	
	@Test
	public void testThatUpdateAdminValuesForResourceProducerWillDirectToRWrongTurnPageJSPIfGivenNull(){
		assertEquals("wrongTurnPage", singleplayerController.updateAdminValuesForResourceProducer("100001", "Farm", "2", "2", modelMock));
	}
	
	@Test
	public void testThatdisplayAssignerFormWillDirectToResourceProducerAssignationPageJSP(){
		assertEquals("assignationPage", singleplayerController.displayAssignerForm("1", "Farm", modelMock));
	}
	
	@Test
	public void testThatDisplayAssignerFormWillDirectToRWrongTurnPageJSPIfGivenNull(){
		assertEquals("wrongTurnPage", singleplayerController.displayAssignerForm("1", null, modelMock));
	}
	
	@After
	public void closeSpring(){
		((ConfigurableApplicationContext)applicationContext).close();
	}
}

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
	private CityHomeController cityHomeController;
	private Model modelMock;
	private ApplicationContext applicationContext;
	
	@Before
	public void init(){
		applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
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
	
	@Test
	public void testThatUpdateAdminValuesForResourceProducerWillDirectAssignationPage(){
		assertEquals("assignationPage", cityHomeController.updateAdminValuesForResourceProducer("1", "Farm", "2", "2", modelMock));
	}
	
	@Test
	public void testThatUpdateAdminValuesForResourceProducerWillDirectToRWrongTurnPageJSPIfGivenNull(){
		assertEquals("wrongTurnPage", cityHomeController.updateAdminValuesForResourceProducer("100001", "Farm", "2", "2", modelMock));
	}
	
	@Test
	public void testThatdisplayAssignerFormWillDirectToResourceProducerAssignationPageJSP(){
		assertEquals("assignationPage", cityHomeController.displayAssignerForm("1", "Farm", modelMock));
	}
	
	@Test
	public void testThatDisplayAssignerFormWillDirectToRWrongTurnPageJSPIfGivenNull(){
		assertEquals("wrongTurnPage", cityHomeController.displayAssignerForm("1", null, modelMock));
	}
	
	@After
	public void closeSpring(){
		((ConfigurableApplicationContext)applicationContext).close();
	}
}

package com.fdmgroup.medievalmayor.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import org.springframework.ui.Model;


public class CityHomeControllerTest {
	private CityHomeController cityHomeControllerMock;
	private Model modelMock;

	@Before
	public void init(){
		cityHomeControllerMock = mock(CityHomeController.class);
		modelMock = mock(Model.class);
	}

	@Test
	public void testThatACityContainsAFarm(){
		when(cityHomeControllerMock.showCities(modelMock)).thenReturn("index");

	}
}

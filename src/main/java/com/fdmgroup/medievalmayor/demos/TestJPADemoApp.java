package com.fdmgroup.medievalmayor.demos;

import com.fdmgroup.medievalmayor.CRUD.CityJPACRUD;
import com.fdmgroup.medievalmayor.game.City;
import com.fdmgroup.medievalmayor.game.CityFactory;

public class TestJPADemoApp {

	public static void main(String[] args) {

		CityFactory cityFactory = new CityFactory();
		City city = cityFactory.getNewCity();
		
		CityJPACRUD cityJPACRUD = new CityJPACRUD();
		cityJPACRUD.create(city);
		
	}

}

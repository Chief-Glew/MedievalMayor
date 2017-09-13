package com.fdmgroup.medievalmayor;

import com.fdmgroup.medievalmayor.CRUD.CityJPACRUD;
import com.fdmgroup.medievalmayor.game.City;
import com.fdmgroup.medievalmayor.game.CityService;

public class TestApp {

	public static void main(String[] args) {
		CityService cityService = CityService.getInstance();
		CityJPACRUD<City> cityCrud= new CityJPACRUD<City>();
		
	//	cityCrud.create(city);
	}
}

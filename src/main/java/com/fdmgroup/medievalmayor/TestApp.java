package com.fdmgroup.medievalmayor;

import com.fdmgroup.medievalmayor.CRUD.CityJPACRUD;

public class TestApp {

	public static void main(String[] args) {
		CityService cityService = CityService.getInstance();
		CityJPACRUD<City> cityCrud= new CityJPACRUD<City>();
		
	//	cityCrud.create(city);
	}
}
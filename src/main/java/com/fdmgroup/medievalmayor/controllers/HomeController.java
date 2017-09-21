package com.fdmgroup.medievalmayor.controllers;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.medievalmayor.CRUD.GenericRead;
import com.fdmgroup.medievalmayor.CRUD.GenericWrite;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;

@Controller
public class HomeController {
	
	static final Logger logger = LogManager.getLogger("HomeController.class");


	private GenericRead<City> readCrud;
	
	private CityFactory cityFactory;
	private GenericWrite<City> writeCrud;

	public HomeController(GenericRead<City> readCrud, CityFactory cityFactory, GenericWrite<City> writeCrud) {
		this.readCrud = readCrud;
		this.writeCrud = writeCrud;
		this.cityFactory =cityFactory;
	}
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String showCities(Model model) {
		System.out.println("root");
		Set<City> cities = readCrud.readAll();
		model.addAttribute("cities", cities);
		logger.debug("ShowCities method used");
		return "index";
	}

	@RequestMapping(value = "/newCity", method = RequestMethod.GET)
	public String newCity(@RequestParam String cityName, Model model) {
		String safeCityName = cityName.replaceAll("/", "forwardSlash");
		safeCityName = safeCityName.replaceAll(" ", "");
		writeCrud.create(cityFactory.getNewCity(safeCityName));
		logger.debug("NewCity method used");
		return showCities(model);
	}
}

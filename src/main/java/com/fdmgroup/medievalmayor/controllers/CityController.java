package com.fdmgroup.medievalmayor.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.fdmgroup.medievalmayor.CRUD.CityJPACRUD;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

@Controller
public class CityController {
	
	private CityJPACRUD readCrud;
	private CityJPACRUD writeCrud;
	private ResourceProducerService buildingManager;
	private City city;
	private ClientCommand clientComand;
	private CityFactory cityFactory;
	

	public CityController() {
		buildingManager = new ResourceProducerService();
		clientComand = new ClientCommand();
		cityFactory = new CityFactory();
		city = cityFactory.getNewCity();
		readCrud = new CityJPACRUD();
		writeCrud = new CityJPACRUD();
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String showCities(Model model){
		System.out.println("root");
		Set<City> cities = readCrud.readAll();
		model.addAttribute("cities", cities);
		return "index";
	}
	@RequestMapping(value = "/newCity", method = RequestMethod.GET)
	public String newCity(Model model){
		city = cityFactory.getNewCity();
		writeCrud.create(city);
		return showCities(model);
	}

	@RequestMapping(value = { "/UserHomeServlet", "/userHome", "/home", "/Home" }, method = RequestMethod.POST)
	public String changeCity(@RequestParam("cityId") String cityId,Model model){
		writeCrud.update(city);
		city = readCrud.read(Long.valueOf(cityId));
		return displayCityStats(model); 
	}
	
	@RequestMapping(value = {"/NextTurn","/nextturn","/nextTurn"}, method = RequestMethod.POST)
	public String nextTurn(Model model){
		try {
			clientComand.nextTurn(city);
		} catch (GameOverException e) {
			e.printStackTrace();
			return "gameOverPage";
		}
		return displayCityStats(model); 
	}

	@RequestMapping(value = { "/UserHomeServlet", "/userHome", "/home", "/Home" }, method = RequestMethod.GET)
	public String displayCityStats(Model model){
		//city = readCrud.read(1);
		//the stuff from the form

		model.addAttribute("totalPopulation", city.getTotalPopulation());
		model.addAttribute("unnassignedPeople", city.getUnassignedPopulation());
		model.addAttribute("farmers", buildingManager.getPeopleInBuilding(city.getFarm()));
		model.addAttribute("miners", buildingManager.getPeopleInBuilding(city.getMine()));
		model.addAttribute("food", city.getFood());
		model.addAttribute("gold", city.getGold());

		return "userHome"; 
	} 
	
	@RequestMapping(value = { "/MineServiceServlet", "/mineService" }, method = RequestMethod.GET)
	public String displayMinerAsignerForm(Model model) {
		model.addAttribute("currentAssigned", buildingManager.getPeopleInBuilding(city.getMine()));
		int maxAssignable = buildingManager.getPeopleInBuilding(city.getMine()) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		return "mineServicePage";
	}
	
	@RequestMapping(value = { "/MineServiceServlet", "/mineService" }, method = RequestMethod.POST)
	public String submitNewMinerAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getMine(), newAssignedPopulation);
		return displayCityStats(model);
	}
	
	@RequestMapping(value = {  "/Farmservice", "/farmservice", "/farmService", "/FarmService" }, method = RequestMethod.GET)
	public String displayFarmerAsignerForm(Model model) {
		model.addAttribute("currentAssigned", buildingManager.getPeopleInBuilding(city.getFarm()));
		int maxAssignable = buildingManager.getPeopleInBuilding(city.getFarm()) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		return "farmServicePage";
	}
	
	@RequestMapping(value = {  "/Farmservice", "/farmservice", "/farmService", "/FarmService" }, method = RequestMethod.POST)
	public String submitNewFarmerAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getFarm(), newAssignedPopulation);
		return displayCityStats(model);
	}
	
	
}

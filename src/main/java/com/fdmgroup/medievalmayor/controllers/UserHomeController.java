package com.fdmgroup.medievalmayor.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.fdmgroup.medievalmayor.CRUD.CityJPACRUD;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

//@Controller
public class UserHomeController {
	
	private CityJPACRUD readCrud;
	private CityJPACRUD writeCrud;
	private ResourceProducerService resourceProducerService;
	private City city;
	private ClientCommand clientComand;
	private CityFactory cityFactory;
	

	public UserHomeController() {
		resourceProducerService = new ResourceProducerService();
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
		Map<String, Integer> workers = new HashMap<String, Integer>();
		for (ResourceProducer resourceProducer: city.getResourceGenerators()){
			workers.put(resourceProducer.resourceProducerName(), resourceProducerService.getPeopleInBuilding(resourceProducer));
		} 
		Map<String, Integer> resources = city.getResources();
		resources.remove("Population");

		model.addAttribute("totalPopulation", city.getTotalPopulation());
		model.addAttribute("unnassignedPeople", city.getUnassignedPopulation());
		model.addAttribute("workers", workers);
		model.addAttribute("resources", resources);

		return "userHome"; 
	} 
	
	@RequestMapping(value = { "/MineServiceServlet", "/mineService" }, method = RequestMethod.GET)
	public String displayMinerAsignerForm(Model model) {
		model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(Mine.class)));
		int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(Mine.class)) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		return "mineServicePage";
	}
	
	@RequestMapping(value = { "/MineServiceServlet", "/mineService" }, method = RequestMethod.POST)
	public String submitNewMinerAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceBuildingOfType(Mine.class), newAssignedPopulation);
		return displayCityStats(model);
	}
	
	@RequestMapping(value = {  "/Farmservice", "/farmservice", "/farmService", "/FarmService" }, method = RequestMethod.GET)
	public String displayFarmerAsignerForm(Model model) {
		model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(Farm.class)));
		int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(Farm.class)) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		return "farmServicePage";
	}
	  
	@RequestMapping(value = {  "/Farmservice", "/farmservice", "/farmService", "/FarmService" }, method = RequestMethod.POST)
	public String submitNewFarmerAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceBuildingOfType(Farm.class), newAssignedPopulation);
		return displayCityStats(model);
	}
	
	@RequestMapping(value = {  "/Forestservice", "/forestservice", "/forestService", "/ForestService" }, method = RequestMethod.GET)
	public String displayLumberJackAsignerForm(Model model) {
		model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(Forest.class)));
		int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(Forest.class)) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		return "forestServicePage";
	}
	  
	@RequestMapping(value = {  "/Forestservice", "/forestservice", "/forestService", "/ForestService" }, method = RequestMethod.POST)
	public String submitNewLumberJackAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceBuildingOfType(Forest.class), newAssignedPopulation);
		return displayCityStats(model);
	}
	
	@RequestMapping(value = {  "/lumberMillService", "/LumberMillservice", "/lumbermillservice", "/LumbermillService", "/LumberMillService" }, method = RequestMethod.GET)
	public String displayLumberMillWorkerAsignerForm(Model model) {
		model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(LumberMill.class)));
		int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(LumberMill.class)) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		return "lumberMillServicePage";
	}
	  
	@RequestMapping(value = {  "/lumberMillService", "/LumberMillservice", "/lumbermillservice", "/LumbermillService", "/LumberMillService" }, method = RequestMethod.POST)
	public String submitNewLumberMillWorkerAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceBuildingOfType(LumberMill.class), newAssignedPopulation);
		return displayCityStats(model);
	}
	
}

package com.fdmgroup.medievalmayor.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	static final Logger logger = LogManager.getLogger("UserHomeController.class");
	
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
		logger.debug("ShowCities method used");
		return "index";
	}
	@RequestMapping(value = "/newCity", method = RequestMethod.GET)
	public String newCity(Model model){
		city = cityFactory.getNewCity();
		writeCrud.create(city);
		logger.debug("NewCity method used");
		return showCities(model);
	}

	@RequestMapping(value = { "/UserHomeServlet", "/userHome", "/home", "/Home" }, method = RequestMethod.POST)
	public String changeCity(@RequestParam("cityId") String cityId,Model model){
		writeCrud.update(city);
		city = readCrud.read(Long.valueOf(cityId));
		logger.debug("ChangeCity method used");
		return displayCityStats(model); 
	} 
	
	@RequestMapping(value = {"/NextTurn","/nextturn","/nextTurn"}, method = RequestMethod.POST)
	public String nextTurn(Model model){
		try {
			clientComand.nextTurn(city);
		} catch (GameOverException e) {
			e.printStackTrace();
			logger.debug("GameOverException");
			return "gameOverPage";
		}
		logger.debug("NextTurn method used");
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
		logger.debug("DisplayCityStats method used");
		return "userHome"; 
	} 
	
	@RequestMapping(value = { "/MineServiceServlet", "/mineService" }, method = RequestMethod.GET)
	public String displayMinerAssignerForm(Model model) {
		model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceProducerOfType(Mine.class)));
		int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceProducerOfType(Mine.class)) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		logger.debug("displayMinerAssignerForm method used");
		return "mineServicePage";
	}
	
	@RequestMapping(value = { "/MineServiceServlet", "/mineService" }, method = RequestMethod.POST)
	public String submitNewMinerAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceProducerOfType(Mine.class), newAssignedPopulation);
		logger.debug("SubmitNewMinerAssignment method used");
		return displayCityStats(model);
	}
	
	@RequestMapping(value = {  "/Farmservice", "/farmservice", "/farmService", "/FarmService" }, method = RequestMethod.GET)
	public String displayFarmerAssignerForm(Model model) {
		model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceProducerOfType(Farm.class)));
		int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceProducerOfType(Farm.class)) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		logger.debug("DisplayFarmerAssignerForm method used");
		return "farmServicePage";
	}
	  
	@RequestMapping(value = {  "/Farmservice", "/farmservice", "/farmService", "/FarmService" }, method = RequestMethod.POST)
	public String submitNewFarmerAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceProducerOfType(Farm.class), newAssignedPopulation);
		logger.debug("SubmitNewFarmerAssignment method used");
		return displayCityStats(model);
	}
	
	@RequestMapping(value = {  "/Forestservice", "/forestservice", "/forestService", "/ForestService" }, method = RequestMethod.GET)
	public String displayLumberJackAssignerForm(Model model) {
		model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceProducerOfType(Forest.class)));
		int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceProducerOfType(Forest.class)) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		logger.debug("DisplayLumberJackAssignerForm method used");
		return "forestServicePage";
	}
	  
	@RequestMapping(value = {  "/Forestservice", "/forestservice", "/forestService", "/ForestService" }, method = RequestMethod.POST)
	public String submitNewLumberJackAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceProducerOfType(Forest.class), newAssignedPopulation);
		logger.debug("SubmitNewLumberJackAssigner method used");
		return displayCityStats(model);
	}
	
	@RequestMapping(value = {  "/lumberMillService", "/LumberMillservice", "/lumbermillservice", "/LumbermillService", "/LumberMillService" }, method = RequestMethod.GET)
	public String displayLumberMillWorkerAssignerForm(Model model) {
		model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceProducerOfType(LumberMill.class)));
		int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceProducerOfType(LumberMill.class)) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		logger.debug("DisplayLumberMillWorkerAssignerForm method used");
		return "lumberMillServicePage";
	}
	  
	@RequestMapping(value = {  "/lumberMillService", "/LumberMillservice", "/lumbermillservice", "/LumbermillService", "/LumberMillService" }, method = RequestMethod.POST)
	public String submitNewLumberMillWorkerAssignment(@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceProducerOfType(LumberMill.class), newAssignedPopulation);
		logger.debug("SubmitNewLumberMillWorkerAssignment method used");
		return displayCityStats(model);
	}
	
}

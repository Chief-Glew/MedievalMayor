package com.fdmgroup.medievalmayor.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.medievalmayor.CRUD.CityJPACRUD;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

@Controller
public class CityHomeController {

	private CityJPACRUD readCrud;
	private CityJPACRUD writeCrud;
	private ResourceProducerService resourceProducerService;
	private ClientCommand clientComand;
	private ProducerClassFromStringHandler stringToClassHandler;
	private CityFactory cityFactory;

	
	public CityHomeController() {
		cityFactory = new CityFactory();
		resourceProducerService = new ResourceProducerService();
		clientComand = new ClientCommand();
		readCrud = new CityJPACRUD();
		writeCrud = new CityJPACRUD();
		stringToClassHandler = new FarmStringHandler();
		stringToClassHandler.addToChain(new ForestStringHandler());
		stringToClassHandler.addToChain(new LumberMillStringHandler());
		stringToClassHandler.addToChain(new MineStringHandler());
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
		writeCrud.create(cityFactory.getNewCity());
		return showCities(model);
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}", method = RequestMethod.GET)
	public String displayCityStats(@PathVariable String cityId, Model model) {
		long cityIdValue = Long.valueOf(cityId);
		City city = readCrud.read(cityIdValue);
		model.addAttribute("city", city);
		
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
		
		writeCrud.update(city);
		return "newUserHome";
	}
	
	@RequestMapping(value = {"/{cityName}/{cityId}/NextTurn","/{cityName}/{cityId}/nextturn","/{cityName}/{cityId}/nextTurn"}, method = RequestMethod.POST)
	public String nextTurn(@PathVariable String cityId, Model model){
		long cityIdValue = Long.valueOf(cityId);
		City city = readCrud.read(cityIdValue);
		model.addAttribute("city", city);
		try {
			clientComand.nextTurn(city);
		} catch (GameOverException e) {
			e.printStackTrace();
			return "gameOverPage";
		}
		writeCrud.update(city);
		return displayCityStats(cityId, model); 
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.GET)
	public String displayMinerAsignerForm(@PathVariable String cityId, @PathVariable String producerName, Model model) {
		long cityIdValue = Long.valueOf(cityId);
		City city = readCrud.read(cityIdValue);
		model.addAttribute("city", city);
		model.addAttribute("producerName", producerName);
		
		model.addAttribute("currentAssigned", resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(stringToClassHandler.handle(producerName))));
		int maxAssignable = resourceProducerService.getPeopleInBuilding(city.getResourceBuildingOfType(stringToClassHandler.handle(producerName))) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		writeCrud.update(city);
		return "assignationPage";
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.POST)
	public String submitNewMinerAssignment(@PathVariable String cityId, @PathVariable String producerName, @RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		long cityIdValue = Long.valueOf(cityId);
		City city = readCrud.read(cityIdValue);
		model.addAttribute("city", city);
		
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceBuildingOfType(stringToClassHandler.handle(producerName)), newAssignedPopulation);
		writeCrud.update(city);
		return displayCityStats(cityId, model);
	}
}

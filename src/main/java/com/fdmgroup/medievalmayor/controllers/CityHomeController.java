package com.fdmgroup.medievalmayor.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.medievalmayor.CRUD.CityJPACRUD;
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.LumberMillAdminHandler;
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.ResourceProducerAdminHandler;
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.ResourceProducerHandler;
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.ResourceProducerUpgradeHandler;
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.URLStringHandler;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.command.CommandInvoker;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

@Controller
public class CityHomeController {

	private CityJPACRUD readCrud;
	private CityJPACRUD writeCrud;
	private ResourceProducerService resourceProducerService;
	private ClientCommand clientComand;
	private CityFactory cityFactory;
	private ProducerClassFromStringHandler stringToClassHandler;
	private URLStringHandler urlStringHandler;
	private ResourceProducerUpgradeHandler resourceProducerUpgradeHandler;

	@Autowired
	public CityHomeController(ResourceProducerUpgradeHandler resourceProducerUpgradeHandler, ClientCommand clientCommand, ProducerClassFromStringHandler stringToClassHandler, CityFactory cityFactory, ResourceProducerService resourceProducerService) {
		this.cityFactory = cityFactory;
		clientComand = clientCommand;
		readCrud = new CityJPACRUD();
		writeCrud = new CityJPACRUD();
		urlStringHandler = new ResourceProducerHandler();
		this.resourceProducerService = resourceProducerService;
		this.stringToClassHandler = stringToClassHandler;
		this.resourceProducerUpgradeHandler = resourceProducerUpgradeHandler;
		}
	
	private City addCityToModel(String cityId, Model model) {
		long cityIdValue = Long.valueOf(cityId);
		City city = readCrud.read(cityIdValue);
		model.addAttribute("city", city);
		return city;
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String showCities(Model model){
		System.out.println("root");
		Set<City> cities = readCrud.readAll();
		model.addAttribute("cities", cities);
		return "index";
	}
	@RequestMapping(value = "/newCity", method = RequestMethod.GET)
	public String newCity(@RequestParam String cityName, Model model){
		String safeCityName = cityName.replaceAll("/", "forwardSlash");
		writeCrud.create(cityFactory.getNewCity(safeCityName));
		return showCities(model);
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}", method = RequestMethod.GET)
	public String displayCityStats(@PathVariable String cityId, Model model) {
		City city = addCityToModel(cityId, model);
		
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
		City city = addCityToModel(cityId, model);
		try {
			clientComand.nextTurn(city);
		} catch (GameOverException e) {
			e.printStackTrace();
			return "gameOverPage";
		}
		writeCrud.update(city);
		return displayCityStats(cityId, model); 
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}/admin", method = RequestMethod.GET)
	public String displayAdminPage(@PathVariable String cityId, Model model) {
		City city = addCityToModel(cityId, model);
		Set<String> resourceProducers = new HashSet<String>();
		for (ResourceProducer resourceProducer: city.getResourceGenerators()){
			resourceProducers.add(resourceProducer.resourceProducerName());
		} 
		model.addAttribute("resourceProducers", resourceProducers);
		
		return "adminPage";
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}/admin/{producerName}", method = RequestMethod.GET)
	public String displayAdminPageForResourseProducer(@PathVariable String cityId, @PathVariable String producerName, Model model) {
		City city = addCityToModel(cityId, model);
		URLStringHandler handler = new LumberMillAdminHandler();
		handler.addToChain(new ResourceProducerAdminHandler());
		try {
			String jspName = handler.handle(city, producerName, model);
			writeCrud.update(city);
			return jspName;
			}
			catch(NullPointerException exception){
			return "wrongTurnPage";
			}
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}/admin/{producerName}", method = RequestMethod.POST)//TODO complete method
	public String updateAdminValuesForResourseProducer(@PathVariable String cityId, @PathVariable String producerName, Model model) {
		City city = addCityToModel(cityId, model);
		URLStringHandler handler = new LumberMillAdminHandler();
		handler.addToChain(new ResourceProducerAdminHandler());
		try {
			String jspName = handler.handle(city, producerName, model);
			writeCrud.update(city);
			return jspName;
			}
			catch(NullPointerException exception){
			return "wrongTurnPage";
			}
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.GET)
	public String displayAsignerForm(@PathVariable String cityId, @PathVariable String producerName, Model model) {
		City city = addCityToModel(cityId, model);
		try {
		String jspName = urlStringHandler.handle(city, producerName, model);
		writeCrud.update(city);
		return jspName;
		}
		catch(NullPointerException exception){
		return "wrongTurnPage";
		}
	}


	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.POST)
	public String submitNewAssignment(@PathVariable String cityId, @PathVariable String producerName, @RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		City city = addCityToModel(cityId, model);
		
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceProducerOfType(stringToClassHandler.handle(producerName)), newAssignedPopulation);
		writeCrud.update(city);
		return displayCityStats(cityId, model);
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}/update", method = RequestMethod.GET)
	public String submitNewMinerAssignment(@PathVariable String cityId, @PathVariable String producerName, @RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		City city = addCityToModel(cityId, model);
		
		resourceProducerUpgradeHandler.handle(city, producerName, model);
		writeCrud.update(city);
		return displayCityStats(cityId, model);
	}
}

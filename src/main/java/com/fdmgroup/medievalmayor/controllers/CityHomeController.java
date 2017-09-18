package com.fdmgroup.medievalmayor.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.URLStringHandler;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

@Controller
public class CityHomeController {
	
	static final Logger logger = LogManager.getLogger("CityHomeController.class");

	private CityJPACRUD readCrud;
	private CityJPACRUD writeCrud;
	private ResourceProducerService resourceProducerService;
	private ClientCommand clientComand;
	private CityFactory cityFactory;
	private ResourceProducerClassFromStringHandler stringToClassHandler;
	private URLStringHandler urlStringHandler;

	public CityHomeController() {
		cityFactory = new CityFactory();
		clientComand = new ClientCommand();
		readCrud = new CityJPACRUD();
		writeCrud = new CityJPACRUD();
		urlStringHandler = new ResourceProducerHandler();
		resourceProducerService = new ResourceProducerService();
		stringToClassHandler = new FarmStringHandler();
		stringToClassHandler.addToChain(new ForestStringHandler());
		stringToClassHandler.addToChain(new LumberMillStringHandler());
		stringToClassHandler.addToChain(new MineStringHandler());
		logger.debug("City Controller Instantiated");
		}
	
	private City addCityToModel(String cityId, Model model) {
		long cityIdValue = Long.valueOf(cityId);
		City city = readCrud.read(cityIdValue);
		model.addAttribute("city", city);
		logger.debug("AddCityToModel method used");
		return city;
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
	public String newCity(@RequestParam String cityName, Model model){
		String safeCityName = cityName.replaceAll("/", "forwardSlash");
		writeCrud.create(cityFactory.getNewCity(safeCityName));
		logger.debug("NewCity method used");
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
		logger.debug("DisplayCityStats method used");
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
		logger.debug("NextTurn method used");
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
		logger.debug("DisplayAdminPage method used");
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
			logger.debug("DisplayAdminPageForResourceProducer method used");
			return jspName;
			}
			catch(NullPointerException exception){
				logger.debug("Null Pointer Exception");
			return "wrongTurnPage";
			}
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}/admin/{producerName}", method = RequestMethod.POST)//TODO complete method
	public String updateAdminValuesForResourceProducer(@PathVariable String cityId, @PathVariable String producerName, Model model) {
		City city = addCityToModel(cityId, model);
		URLStringHandler handler = new LumberMillAdminHandler();
		handler.addToChain(new ResourceProducerAdminHandler());
		try {
			String jspName = handler.handle(city, producerName, model);
			writeCrud.update(city);
			logger.debug("UpdateAdminValuesForResourceProducer method used");
			return jspName;
			}
			catch(NullPointerException exception){
				logger.debug("Null Pointer Exception");
			return "wrongTurnPage";
			}
	}
	
	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.GET)
	public String displayMinerAssignerForm(@PathVariable String cityId, @PathVariable String producerName, Model model) {
		City city = addCityToModel(cityId, model);
		try {
		String jspName = urlStringHandler.handle(city, producerName, model);
		writeCrud.update(city);
		logger.debug("DisplayMinerAssignerForm method used");
		return jspName;
		}
		catch(NullPointerException exception){
			logger.debug("Null Pointer Exception");
		return "wrongTurnPage";
		}
	}


	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.POST)
	public String submitNewMinerAssignment(@PathVariable String cityId, @PathVariable String producerName, @RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		City city = addCityToModel(cityId, model);
		
		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getResourceProducerOfType(stringToClassHandler.handle(producerName)), newAssignedPopulation);
		writeCrud.update(city);
		logger.debug("SubmitNewMinerAssignment method used");
		return displayCityStats(cityId, model);
	}
}

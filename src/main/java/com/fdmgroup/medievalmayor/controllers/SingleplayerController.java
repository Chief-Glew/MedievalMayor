package com.fdmgroup.medievalmayor.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.medievalmayor.CRUD.GenericRead;
import com.fdmgroup.medievalmayor.CRUD.GenericWrite;
import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.exceptions.InvalidCityNameIDCombinationException;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.MultiplayerGame;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.events.RandomEventHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.ResourceProducerUpgradeHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.LumberMillAdminHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.ResourceProducerAdminHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.ResourceProducerHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.URLStringHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

@Controller
public class SingleplayerController {

	static final Logger logger = LogManager.getLogger("CityHomeController.class");

	private GenericRead<City> readCrud;
	private GenericWrite<City> writeCrud;
	private ClientCommand clientComand;
	private ResourceProducerClassFromStringHandler stringToClassHandler;
	private URLStringHandler urlStringHandler;
	private ResourceProducerUpgradeHandler resourceProducerUpgradeHandler;
	private RandomEventHandler randomEventHandler;

	@Autowired
	public SingleplayerController(ResourceProducerUpgradeHandler resourceProducerUpgradeHandler,
			ClientCommand clientCommand, ResourceProducerClassFromStringHandler stringToClassHandler,
			GenericRead<City> readCrud, GenericWrite<City> writeCrud, 
			GenericRead<MultiplayerGame> MultiReadCrud,
			GenericWrite<MultiplayerGame> MultiWriteCrud,
			RandomEventHandler randomEventHandler) {
		this.randomEventHandler = randomEventHandler;
		this.clientComand = clientCommand;
		this.readCrud = readCrud;
		this.writeCrud = writeCrud;
		urlStringHandler = new ResourceProducerHandler();
		this.stringToClassHandler = stringToClassHandler;
		this.resourceProducerUpgradeHandler = resourceProducerUpgradeHandler;
		logger.debug("City Controller Instantiated");
	}

	public City addCityToModel(String cityId, String cityName, Model model) throws InvalidCityNameIDCombinationException {
		long cityIdValue = Long.valueOf(cityId);
		City city = readCrud.read(cityIdValue);
		if(!city.getCityName().equals(cityName)){
			throw new InvalidCityNameIDCombinationException("This is not a valid City Name and Id Combination");
		}
		model.addAttribute("city", city);
		logger.debug("AddCityToModel method used");
		return city;
	}

	@RequestMapping(value = "/{cityName}/{cityId}", method = RequestMethod.GET)
	public String displayCityStats(@PathVariable String cityId, @PathVariable String cityName, Model model) {
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			return "wrongTurnPage";
		}

		Set<ResourceProducer> resourceProducers = city.getResourceGenerators();
		Map<String, Integer> resources = city.getResources();
		resources.remove("Population");

		model.addAttribute("totalPopulation", city.getTotalPopulation());
		model.addAttribute("unnassignedPeople", city.getUnassignedPopulation());
		model.addAttribute("resourceProducers", resourceProducers);
		model.addAttribute("resources", resources);

		writeCrud.update(city);
		logger.debug("DisplayCityStats method used");
		return "newUserHome";
	}

	@RequestMapping(value = { "/{cityName}/{cityId}/NextTurn", "/{cityName}/{cityId}/nextturn", "/{cityName}/{cityId}/nextTurn",
	"/{cityName}/{cityId}/nextTurn" }, method = RequestMethod.POST)
	public String nextTurn(@PathVariable String cityId, @PathVariable String cityName, Model model) {
		City city;
		List<String> events = new ArrayList<String>();
		try {
			city = addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			return "wrongTurnPage";
		}
		try {
			clientComand.nextTurn(city); //TODO make next turn record events
		} catch (GameOverException e) {
			e.printStackTrace();
			return "gameOverPage";
		}
		randomEventHandler.handle(city, events);
		writeCrud.update(city);
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println(events);
		model.addAttribute("events", events);
		logger.debug("NextTurn method used");
		return displayCityStats(cityId, cityName, model);
	}

	@RequestMapping(value = "/{cityName}/{cityId}/admin", method = RequestMethod.GET)
	public String displayAdminPage(@PathVariable String cityId, @PathVariable String cityName, Model model) {
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			return "wrongTurnPage";
		}
		Set<String> resourceProducers = new HashSet<String>();
		for (ResourceProducer resourceProducer : city.getResourceGenerators()) {
			resourceProducers.add(resourceProducer.getResourceProducerName());
		}
		model.addAttribute("resourceProducers", resourceProducers);
		logger.debug("DisplayAdminPage method used");
		return "adminPage";
	}

	@RequestMapping(value = "/{cityName}/{cityId}/admin/{producerName}", method = RequestMethod.GET)
	public String displayAdminPageForResourseProducer(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			Model model) {
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			return "wrongTurnPage";
		}
		URLStringHandler handler = new LumberMillAdminHandler();
		handler.addToChain(new ResourceProducerAdminHandler());
		try {
			String jspName = handler.handle(city, producerName, model);
			writeCrud.update(city);
			logger.debug("DisplayAdminPageForResourceProducer method used");
			return jspName;
		} catch (NullPointerException exception) {
			logger.debug("Null Pointer Exception");
			return "wrongTurnPage";
		}
	}

	@RequestMapping(value = "/{cityName}/{cityId}/admin/{producerName}", method = RequestMethod.POST)																							
	public String updateAdminValuesForResourceProducer(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			@RequestParam("baseProduction") String baseProduction, @RequestParam("upgradeMultiplier") String upgradeMultiplier, Model model) {
		int baseProductionInt = Integer.valueOf(baseProduction);
		int upgradeMultiplierInt = Integer.valueOf(upgradeMultiplier);
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //TODO fix this;
		ResourceProducerClassFromStringHandler resourceProducerClass = applicationContext.getBean(ResourceProducerClassFromStringHandler.class);
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			return "wrongTurnPage";
		}
		logger.debug("UpdateAdminValuesForResourceProducer method used");
		try {
			city.getResourceProducerOfType(resourceProducerClass.handle(producerName)).setBaseResourceProduction(baseProductionInt);;
			city.getResourceProducerOfType(resourceProducerClass.handle(producerName)).setUpgradeMultiplier(upgradeMultiplierInt);;
			writeCrud.update(city);
			String jspName = urlStringHandler.handle(city, producerName, model);
			writeCrud.update(city);
			logger.debug("DisplayAssignerForm method used");
			return jspName;
		} catch (NullPointerException exception) {
			logger.debug("Null Pointer Exception");
			return "wrongTurnPage";
		}
	}
	
	//TODO FIX THIS HORRIBLE REPEATING NIGHTMARE
	@RequestMapping(value = "/{cityName}/{cityId}/admin/Lumber Mill", method = RequestMethod.POST)																							
	public String updateAdminValuesForLumberMill(@PathVariable String cityId, @PathVariable String cityName,
			@RequestParam("baseProduction") String baseProduction, @RequestParam("upgradeMultiplier") String upgradeMultiplier, @RequestParam("conversionFactor") String conversionFactor, Model model) {
		String producerName = "Lumber Mill";
		int baseProductionInt = Integer.valueOf(baseProduction);
		int upgradeMultiplierInt = Integer.valueOf(upgradeMultiplier);
		int conversionFactorInt = Integer.valueOf(conversionFactor);
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //TODO fix this;
		ResourceProducerClassFromStringHandler resourceProducerClass = applicationContext.getBean(ResourceProducerClassFromStringHandler.class);
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			return "wrongTurnPage";
		}
		logger.debug("UpdateAdminValuesForResourceProducer method used");
		try {
			city.getResourceProducerOfType(resourceProducerClass.handle(producerName)).setBaseResourceProduction(baseProductionInt);;
			city.getResourceProducerOfType(resourceProducerClass.handle(producerName)).setUpgradeMultiplier(upgradeMultiplierInt);;
			city.getResourceProducerOfType(LumberMill.class).setProducerLevel(conversionFactorInt);
			writeCrud.update(city);
			String jspName = urlStringHandler.handle(city, producerName, model);
			writeCrud.update(city);
			logger.debug("DisplayAssignerForm method used");
			return jspName;
		} catch (NullPointerException exception) {
			logger.debug("Null Pointer Exception");
			return "wrongTurnPage";
		}
	}

	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.GET)
	public String displayAssignerForm(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			Model model) {
		logger.debug("DisplayAssignerForm method used");
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			return "wrongTurnPage";
		}
		try {
			String jspName = urlStringHandler.handle(city, producerName, model);
			writeCrud.update(city);
			
			return jspName;
		} catch (NullPointerException exception) {
			logger.debug("Null Pointer Exception1");
			return "wrongTurnPage";
		}
	}

	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.POST)
	public String submitNewAssignment(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			return "wrongTurnPage";
		}

		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city,
				city.getResourceProducerOfType(stringToClassHandler.handle(producerName)), newAssignedPopulation);
		writeCrud.update(city);
		return displayCityStats(cityId, cityName, model);
	}

	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}/upgrade", method = RequestMethod.GET)
	public String submitNewMinerAssignment(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			Model model) {
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			return "wrongTurnPage";
		}
		resourceProducerUpgradeHandler.handle(city, producerName, model);
		writeCrud.update(city);
		logger.debug("SubmitNewMinerAssignment method used");
		return displayCityStats(cityId, cityName, model);
	}
}

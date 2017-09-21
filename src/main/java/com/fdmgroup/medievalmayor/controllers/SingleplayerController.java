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
import com.fdmgroup.medievalmayor.game.command.usercommands.ClientCommand;
import com.fdmgroup.medievalmayor.game.events.RandomEventHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.ResourceProducerUpgradeHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.LumberMillAdminHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.ResourceProducerAdminHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.ResourceProducerHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.URLStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.NormalWeatherHandler;
import com.fdmgroup.medievalmayor.game.handlers.weatherhandler.UpdateWeatherHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

@Controller
public class SingleplayerController {

	static final Logger logger = LogManager.getLogger("SingleplayerController.class");

	private GenericRead<City> readCrud;
	private GenericWrite<City> writeCrud;
	private ClientCommand clientComand;
	private ResourceProducerClassFromStringHandler stringToClassHandler;
	private URLStringHandler urlStringHandler;
	private ResourceProducerUpgradeHandler resourceProducerUpgradeHandler;
	private RandomEventHandler randomEventHandler;
	private UpdateWeatherHandler updateWeatherHandler;

	@Autowired
	public SingleplayerController(ResourceProducerUpgradeHandler resourceProducerUpgradeHandler,
			ClientCommand clientCommand, ResourceProducerClassFromStringHandler stringToClassHandler,
			GenericRead<City> readCrud, GenericWrite<City> writeCrud, UpdateWeatherHandler updateWeatherHandler, 
			GenericRead<MultiplayerGame> MultiReadCrud,
			GenericWrite<MultiplayerGame> MultiWriteCrud,
			RandomEventHandler randomEventHandler) {
		this.randomEventHandler = randomEventHandler;
		this.updateWeatherHandler = updateWeatherHandler;
		this.clientComand = clientCommand;
		this.readCrud = readCrud;
		this.writeCrud = writeCrud;
		urlStringHandler = new ResourceProducerHandler();
		this.stringToClassHandler = stringToClassHandler;
		this.resourceProducerUpgradeHandler = resourceProducerUpgradeHandler;
		logger.info("City Controller Instantiated");
	}

	public City addCityToModel(String cityId, String cityName, Model model) throws InvalidCityNameIDCombinationException {
		logger.info("AddCityToModel method used in SinglePlayerController Class");
		long cityIdValue = Long.valueOf(cityId);
		City city = readCrud.read(cityIdValue);
		if(!city.getCityName().equals(cityName)){
			logger.debug("InvalidCityNameIDCombinationException thrown in addCityToModel method");
			throw new InvalidCityNameIDCombinationException("This is not a valid City Name and Id Combination");
		}
		model.addAttribute("city", city);
		return city;
	}

	@RequestMapping(value = "/{cityName}/{cityId}", method = RequestMethod.GET)
	public String displayCityStats(@PathVariable String cityId, @PathVariable String cityName, Model model) {
		logger.info("DisplayCityStats method used in SinglePlayerController class");
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
			logger.trace(city+" addedToModel in DisplayCityStats method");
		} catch (InvalidCityNameIDCombinationException e) {
			logger.debug("InvalidCityNameIDCombination exception in DisplayCityStats method");
			return "wrongTurnPage";
		}

		Set<ResourceProducer> resourceProducers = city.getResourceProducers();
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
		logger.info("NextTurn method used in SinglePlayerController Class");
		City city;
		List<String> events = new ArrayList<String>();
		String weatherEvent = "blank";
		try {
			city = addCityToModel(cityId, cityName, model);
			logger.trace(city+" addedToModel in nextTurn method");
		} catch (InvalidCityNameIDCombinationException e) {
			logger.debug("InvalidCityNameIDCombination exception in NextTurn method");
			return "wrongTurnPage";
		}
		try {
			clientComand.nextTurn(city); //TODO make next turn record events
			logger.trace("NextTurn command used in NextTurn method");
		} catch (GameOverException e) {
			logger.debug("GameOverException in NextTurn method");
			e.printStackTrace();
			return "gameOverPage";
		}
		randomEventHandler.handle(city, events);
		updateWeatherHandler.handle(city, 1, weatherEvent);
		System.out.println(weatherEvent);
		writeCrud.update(city);
		logger.info("Events of the Year: "+events);
		model.addAttribute("events", events);
		model.addAttribute("weatherEvent", weatherEvent);
		logger.debug("NextTurn method used");
		return displayCityStats(cityId, cityName, model);
	}

	@RequestMapping(value = "/{cityName}/{cityId}/admin", method = RequestMethod.GET)
	public String displayAdminPage(@PathVariable String cityId, @PathVariable String cityName, Model model) {
		logger.info("DisplayAdminPage method used in SinglePlayerController class");
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
			logger.trace(city+" addedToModel in displayAdminPage method");
		} catch (InvalidCityNameIDCombinationException e) {
			logger.info("DisplayAdminPage method used in SinglePlayerController class");
			return "wrongTurnPage";
		}
		Set<String> resourceProducers = new HashSet<String>();
		for (ResourceProducer resourceProducer : city.getResourceProducers()) {
			resourceProducers.add(resourceProducer.getResourceProducerName());
		}
		model.addAttribute("resourceProducers", resourceProducers);
		return "adminPage";
	}

	@RequestMapping(value = "/{cityName}/{cityId}/admin/{producerName}", method = RequestMethod.GET)
	public String displayAdminPageForResourseProducer(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			Model model) {
		logger.info("DisplayAdminPageForResourseProducer method used in SinglePlayerController class");
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
			logger.trace(city+" addedToModel in displayAdminPageForResourseProducer method");
		} catch (InvalidCityNameIDCombinationException e) {
			logger.debug("InvalidCityNameIDCombination exception in DisplayAdminPageForResourseProducer method");
			return "wrongTurnPage";
		}
		URLStringHandler handler = new LumberMillAdminHandler();
		handler.addToChain(new ResourceProducerAdminHandler());
		try {
			String jspName = handler.handle(city, producerName, model);
			writeCrud.update(city);
			logger.trace("LumberMillAdminHandler addedToChain in DisplayAdminPageForResourseProducer method");
			return jspName;
		} catch (NullPointerException exception) {
			logger.debug("NullPointerException in DisplayAdminPageForResourseProducer method");
			return "wrongTurnPage";
		}
	}

	@RequestMapping(value = "/{cityName}/{cityId}/admin/{producerName}", method = RequestMethod.POST)																							
	public String updateAdminValuesForResourceProducer(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			@RequestParam("baseProduction") String baseProduction, @RequestParam("upgradeMultiplier") String upgradeMultiplier, Model model) {
		logger.info("UpdateAdminValuesForResourceProducer method used in SinglePlayerController class");
		int baseProductionInt = Integer.valueOf(baseProduction);
		int upgradeMultiplierInt = Integer.valueOf(upgradeMultiplier);
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //TODO fix this;
		ResourceProducerClassFromStringHandler resourceProducerClass = applicationContext.getBean(ResourceProducerClassFromStringHandler.class);
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
			logger.trace(city+" addedToModel in updateAdminValuesForResourceProducer method");
		} catch (InvalidCityNameIDCombinationException e) {
			logger.debug("InvalidCityNameIDCombination exception in updateAdminValuesForResourceProducer method");
			return "wrongTurnPage";
		}
		try {
			city.getResourceProducerOfType(resourceProducerClass.handle(producerName)).setBaseResourceProduction(baseProductionInt);;
			city.getResourceProducerOfType(resourceProducerClass.handle(producerName)).setUpgradeMultiplier(upgradeMultiplierInt);;
			writeCrud.update(city);
			String jspName = urlStringHandler.handle(city, producerName, model);
			writeCrud.update(city);
			logger.info("Resource admin values updated in updateAdminValuesForResourceProducer method");
			return jspName;
		} catch (NullPointerException exception) {
			logger.debug("Null Pointer Exception in updateAdminValuesForResourceProducer method");
			return "wrongTurnPage";
		}
	}

	//TODO FIX THIS HORRIBLE REPEATING NIGHTMARE
	@RequestMapping(value = "/{cityName}/{cityId}/admin/Lumber Mill", method = RequestMethod.POST)																							
	public String updateAdminValuesForLumberMill(@PathVariable String cityId, @PathVariable String cityName,
			@RequestParam("baseProduction") String baseProduction, @RequestParam("upgradeMultiplier") String upgradeMultiplier, @RequestParam("conversionFactor") String conversionFactor, Model model) {
		logger.info("UpdateAdminValuesForLumberMill method used in SinglePlayerController class");
		String producerName = "Lumber Mill";
		int baseProductionInt = Integer.valueOf(baseProduction);
		int upgradeMultiplierInt = Integer.valueOf(upgradeMultiplier);
		int conversionFactorInt = Integer.valueOf(conversionFactor);

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //TODO fix this;
		ResourceProducerClassFromStringHandler resourceProducerClass = applicationContext.getBean(ResourceProducerClassFromStringHandler.class);
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
			logger.trace(city+" addedToModel in updateAdminValuesForLumberMill method");
		} catch (InvalidCityNameIDCombinationException e) {
			logger.debug("InvalidCityNameIDCombination exception in updateAdminValuesForLumberMill method");
			return "wrongTurnPage";
		}

		try {
			city.getResourceProducerOfType(resourceProducerClass.handle(producerName)).setBaseResourceProduction(baseProductionInt);;
			city.getResourceProducerOfType(resourceProducerClass.handle(producerName)).setUpgradeMultiplier(upgradeMultiplierInt);;
			city.getResourceProducerOfType(LumberMill.class).setProducerLevel(conversionFactorInt);
			writeCrud.update(city);
			String jspName = urlStringHandler.handle(city, producerName, model);
			writeCrud.update(city);
			logger.trace("Resource admin values for lumbermill updated in updateAdminValuesForLumberMill method");
			return jspName;
		} catch (NullPointerException exception) {
			logger.debug("Null Pointer Exception in updateAdminValuesForLumberMill method");
			return "wrongTurnPage";
		}
	}

	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.GET)
	public String displayAssignerForm(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			Model model) {
		logger.info("DisplayAssignerForm method used in SinglePlayerController class");
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
			logger.trace(city+" addedToModel in displayAssignerForm method");
		} catch (InvalidCityNameIDCombinationException e) {
			logger.debug("InvalidCityNameIDCombination exception in displayAssignerForm method");
			return "wrongTurnPage";
		}
		try {
			String jspName = urlStringHandler.handle(city, producerName, model);
			writeCrud.update(city);
			logger.trace("City added to urlStringHandler method in displayAssignerForm method");
			return jspName;
		} catch (NullPointerException exception) {
			logger.debug("Null Pointer Exception in displayAssignerForm method");
			return "wrongTurnPage";
		}
	}

	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.POST)
	public String submitNewAssignment(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		logger.info("SubmitNewAssignment method used in SinglePlayerController class");
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
			logger.trace(city+" addedToModel in submitNewAssignment method");
		} catch (InvalidCityNameIDCombinationException e) {
			logger.debug("InvalidCityNameIDCombination exception in submitNewAssignment method");
			return "wrongTurnPage";
		}

		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city,
				city.getResourceProducerOfType(stringToClassHandler.handle(producerName)), newAssignedPopulation);
		writeCrud.update(city);
		logger.trace("setNumberOfWorkersInResourceBuildingForCity method used in submitNewAssignment method for: "+city);
		return displayCityStats(cityId, cityName, model);
	}

	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}/upgrade", method = RequestMethod.GET)
	public String submitUpgrade(@PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName,
			Model model) {
		logger.info("SubmitNewMinerAssignment method used in SinglePlayerController class");
		City city;
		try {
			city = addCityToModel(cityId, cityName, model);
			logger.trace(city+" addedToModel in submitNewMinerAssignment method");
		} catch (InvalidCityNameIDCombinationException e) {
			logger.debug("InvalidCityNameIDCombination exception in submitNewMinerAssignment method");
			return "wrongTurnPage";
		}

		model.addAttribute("resourceProducerName", producerName);

		String upgrade = resourceProducerUpgradeHandler.handle(city, producerName, model);
		writeCrud.update(city);
		logger.trace("resourceProducerUpgradeHandler method used in submitNewMinerAssignment method for: "+city);
		return upgrade;
	}
}

package com.fdmgroup.medievalmayor.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.medievalmayor.CRUD.GenericRead;
import com.fdmgroup.medievalmayor.CRUD.GenericWrite;
import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.city.MultiplayerGame;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.ResourceProducerUpgradeHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.LumberMillAdminHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.ResourceProducerAdminHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.ResourceProducerHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.URLStringHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

@Controller
public class CityHomeController {

	static final Logger logger = LogManager.getLogger("CityHomeController.class");

	private GenericRead<City> readCrud;
	private GenericWrite<City> writeCrud;
	private GenericRead<MultiplayerGame> MultiReadCrud;
	private GenericWrite<MultiplayerGame> MultiWriteCrud;
	private ResourceProducerService resourceProducerService;
	private ClientCommand clientComand;
	private CityFactory cityFactory;
	private ResourceProducerClassFromStringHandler stringToClassHandler;
	private URLStringHandler urlStringHandler;
	private ResourceProducerUpgradeHandler resourceProducerUpgradeHandler;

	@Autowired
	public CityHomeController(ResourceProducerUpgradeHandler resourceProducerUpgradeHandler,
			ClientCommand clientCommand, ResourceProducerClassFromStringHandler stringToClassHandler,
			CityFactory cityFactory, ResourceProducerService resourceProducerService, GenericRead<City> readCrud,
			GenericWrite<City> writeCrud, GenericRead<MultiplayerGame> MultiReadCrud,
			GenericWrite<MultiplayerGame> MultiWriteCrud) {
		this.cityFactory = cityFactory;
		this.clientComand = clientCommand;
		this.readCrud = readCrud;
		this.writeCrud = writeCrud;
		this.MultiReadCrud = MultiReadCrud;
		this.MultiWriteCrud = MultiWriteCrud;
		urlStringHandler = new ResourceProducerHandler();
		this.resourceProducerService = resourceProducerService;
		this.stringToClassHandler = stringToClassHandler;
		this.resourceProducerUpgradeHandler = resourceProducerUpgradeHandler;
		logger.debug("City Controller Instantiated");
	}

	public City addCityToModel(String cityId, Model model) {
		long cityIdValue = Long.valueOf(cityId);
		City city = readCrud.read(cityIdValue);
		model.addAttribute("city", city);
		logger.debug("AddCityToModel method used");
		return city;
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
		writeCrud.create(cityFactory.getNewCity(safeCityName));
		logger.debug("NewCity method used");
		return showCities(model);
	}

	@RequestMapping(value = "/{cityName}/{cityId}", method = RequestMethod.GET)
	public String displayCityStats(@PathVariable String cityId, Model model) {
		City city = addCityToModel(cityId, model);

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

	@RequestMapping(value = { "/{cityName}/{cityId}/NextTurn", "/{cityName}/{cityId}/nextturn",
	"/{cityName}/{cityId}/nextTurn" }, method = RequestMethod.POST)
	public String nextTurn(@PathVariable String cityId, Model model) {
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
		for (ResourceProducer resourceProducer : city.getResourceGenerators()) {
			resourceProducers.add(resourceProducer.getResourceProducerName());
		}
		model.addAttribute("resourceProducers", resourceProducers);
		logger.debug("DisplayAdminPage method used");
		return "adminPage";
	}

	@RequestMapping(value = "/{cityName}/{cityId}/admin/{producerName}", method = RequestMethod.GET)
	public String displayAdminPageForResourseProducer(@PathVariable String cityId, @PathVariable String producerName,
			Model model) {
		City city = addCityToModel(cityId, model);
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
	public String updateAdminValuesForResourceProducer(@PathVariable String cityId, @PathVariable String producerName,
			@RequestParam("baseProduction") String baseProduction, @RequestParam("upgradeMultiplier") String upgradeMultiplier, Model model) {
		int baseProductionInt = Integer.valueOf(baseProduction);
		int upgradeMultiplierInt = Integer.valueOf(upgradeMultiplier);
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //TODO fix this;
		ResourceProducerClassFromStringHandler resourceProducerClass = applicationContext.getBean(ResourceProducerClassFromStringHandler.class);
		City city = addCityToModel(cityId, model);
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

	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.GET)
	public String displayAssignerForm(@PathVariable String cityId, @PathVariable String producerName, Model model) {
		City city = addCityToModel(cityId, model);
		try {
			String jspName = urlStringHandler.handle(city, producerName, model);
			writeCrud.update(city);
			logger.debug("DisplayAssignerForm method used");
			return jspName;
		} catch (NullPointerException exception) {
			logger.debug("Null Pointer Exception");
			return "wrongTurnPage";
		}
	}

	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}", method = RequestMethod.POST)
	public String submitNewAssignment(@PathVariable String cityId, @PathVariable String producerName,
			@RequestParam("newAssignedPopulation") String assignedPopulation, Model model) {
		City city = addCityToModel(cityId, model);

		int newAssignedPopulation = Integer.valueOf(assignedPopulation);
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city,
				city.getResourceProducerOfType(stringToClassHandler.handle(producerName)), newAssignedPopulation);
		writeCrud.update(city);
		return displayCityStats(cityId, model);
	}

	@RequestMapping(value = "/{cityName}/{cityId}/{producerName}/upgrade", method = RequestMethod.GET)
	public String submitNewMinerAssignment(@PathVariable String cityId, @PathVariable String producerName,
			Model model) {
		City city = addCityToModel(cityId, model);

		resourceProducerUpgradeHandler.handle(city, producerName, model);
		writeCrud.update(city);
		logger.debug("SubmitNewMinerAssignment method used");
		return displayCityStats(cityId, model);
	}
}

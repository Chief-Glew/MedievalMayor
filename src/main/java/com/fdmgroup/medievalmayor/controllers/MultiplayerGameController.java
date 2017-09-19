package com.fdmgroup.medievalmayor.controllers;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.medievalmayor.CRUD.GenericRead;
import com.fdmgroup.medievalmayor.CRUD.GenericWrite;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.city.MultiplayerGame;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.ResourceProducerUpgradeHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.ResourceProducerHandler;
import com.fdmgroup.medievalmayor.game.handlers.urlstringhandlers.URLStringHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducerService;

@Controller
@RequestMapping("multiPlayer")
public class MultiplayerGameController {

	static final Logger logger = LogManager.getLogger("CityHomeController.class");

	private GenericRead<MultiplayerGame> MultiReadCrud;
	private GenericWrite<MultiplayerGame> MultiWriteCrud;
	private CityHomeController cityHomeController;

	@Autowired
	public MultiplayerGameController(CityHomeController cityHomeController, GenericRead<MultiplayerGame> MultiReadCrud,
			GenericWrite<MultiplayerGame> MultiWriteCrud) {
		this.MultiReadCrud = MultiReadCrud;
		this.MultiWriteCrud = MultiWriteCrud;
		this.cityHomeController = cityHomeController;
	}
	
	private MultiplayerGame addMultiplayerGameToModel(String multiplayerGameId, Model model) {
		Long multiplayerGameIdValue = Long.valueOf(multiplayerGameId);
		MultiplayerGame multiplayerGame = MultiReadCrud.read(multiplayerGameIdValue);
		model.addAttribute("multiplayerGame", multiplayerGame);
		return multiplayerGame;
	}
	
	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/", method = RequestMethod.GET)
	public String showAllMultiplayerGames(@PathVariable String multiplayerGameId, @PathVariable String cityId, Model model){
		MultiplayerGame multiplayerGame = addMultiplayerGameToModel(multiplayerGameId, model);
		City city = cityHomeController.addCityToModel(cityId, model);
		cityHomeController.displayCityStats(cityId, model);
		Set<City> otherCities = multiplayerGame.getCities();
		otherCities.remove(city);
		model.addAttribute("otherCities", otherCities);
		model.addAttribute("isReady", multiplayerGame.isReady(city));
		return "multiplayerCity";
	}
	
	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/nextturn", method = RequestMethod.GET)

	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/", method = RequestMethod.GET)

	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/", method = RequestMethod.GET)

	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/", method = RequestMethod.GET)

	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/", method = RequestMethod.GET)

}

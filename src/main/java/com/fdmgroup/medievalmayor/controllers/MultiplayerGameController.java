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
import com.fdmgroup.medievalmayor.exceptions.GameOverException;
import com.fdmgroup.medievalmayor.exceptions.InvalidCityNameIDCombinationException;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.MultiplayerGame;

@Controller
public class MultiplayerGameController {

	static final Logger logger = LogManager.getLogger("CityHomeController.class");

	private GenericRead<MultiplayerGame> MultiReadCrud;
	private GenericWrite<MultiplayerGame> MultiWriteCrud;
	private SingleplayerController singleplayerController;

	@Autowired
	public MultiplayerGameController(SingleplayerController singleplayerController, GenericRead<MultiplayerGame> MultiReadCrud,
			GenericWrite<MultiplayerGame> MultiWriteCrud) {
		this.MultiReadCrud = MultiReadCrud;
		this.MultiWriteCrud = MultiWriteCrud;
		this.singleplayerController = singleplayerController;
	}
	
	private MultiplayerGame addMultiplayerGameToModel(String multiplayerGameId, Model model) {
		Long multiplayerGameIdValue = Long.valueOf(multiplayerGameId);
		MultiplayerGame multiplayerGame = MultiReadCrud.read(multiplayerGameIdValue);
		model.addAttribute("multiplayerGame", multiplayerGame);
		return multiplayerGame;
	}
	
	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/", method = RequestMethod.GET)
	public String showAllCities(@PathVariable String multiplayerGameId, @PathVariable String cityId, @PathVariable String cityName, Model model){
		singleplayerController.displayCityStats(cityId, cityName, model);
		MultiplayerGame multiplayerGame = addMultiplayerGameToModel(multiplayerGameId, model);
		City city;
		try {
			city = singleplayerController.addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			e.printStackTrace();
			return "wrongTurnPage";
		}
		Set<City> otherCities = multiplayerGame.getCities();
		otherCities.remove(city);
		model.addAttribute("otherCities", otherCities);
		model.addAttribute("isReady", multiplayerGame.isReady(city));
		return "multiplayerCity";
	}
	
	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/nextturn", method = RequestMethod.GET)
	public String nextTurn(@PathVariable String multiplayerGameId, @PathVariable String cityId, @PathVariable String cityName, Model model){
		MultiplayerGame multiplayerGame = addMultiplayerGameToModel(multiplayerGameId, model);
		City city;
		try {
			city = singleplayerController.addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e1) {
			e1.printStackTrace();
			return "wrongTurnPage";
		}
		try {
			multiplayerGame.setReady(city);
			return showAllCities(multiplayerGameId, cityId, cityName, model);
		} catch (GameOverException e) {
			e.printStackTrace();
			return "gameOver";
		}
		
	}
	
	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/{producerName}", method = RequestMethod.GET)
	public String displayAssignerForm(@PathVariable String multiplayerGameId, @PathVariable String cityId, @PathVariable String cityName, @PathVariable String producerName, Model model) {
		addMultiplayerGameToModel(multiplayerGameId, model);
		String jspFile = singleplayerController.displayAssignerForm(cityId, producerName, cityName, model);
		try {
			singleplayerController.addCityToModel(cityId, cityName, model);
		} catch (InvalidCityNameIDCombinationException e) {
			e.printStackTrace();
			return "wrongTurnPage";
		}
		return "multi"+jspFile;
	}
	//	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/", method = RequestMethod.GET)
//
//	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/", method = RequestMethod.GET)
//
//	@RequestMapping(value="/{multiplayerGameName}/{multiplayerGameId}/{cityName}/{cityId}/", method = RequestMethod.GET)


}

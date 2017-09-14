package com.fdmgroup.medievalmayor.servlets;

import java.util.HashMap;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import com.fdmgroup.medievalmayor.CRUD.GenericRead;
import com.fdmgroup.medievalmayor.CRUD.GenericWrite;
import com.fdmgroup.medievalmayor.game.City;
import com.fdmgroup.medievalmayor.game.CityFactory;
import com.fdmgroup.medievalmayor.game.building.BuildingManager;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
@Controller
public class CityController {
	@Autowired(required = false)
	private GenericRead<City> readCrud;
	@Autowired(required = false)
	private GenericWrite<City> writeCrud;
	private BuildingManager buildingManager = new BuildingManager();
	private City city;
	private ClientCommand clientComand = new ClientCommand();
	private CityFactory cityFactory = new CityFactory();
	//TODO make this not bad

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String showCities(Model model){
		System.out.println("root");
		Set<City> cities = readCrud.readAll();
		model.addAttribute("cities", cities);
		return "index";
	}

	@RequestMapping(value = { "/UserHomeServlet", "/userHome", "/home", "/Home" }, method = RequestMethod.POST)
	public String changeCity(Model model){
		//TODO get atributes from spring form
		//city = cityFactory.getNewCity();//the stuff from the form
		clientComand.nextTurn(city);
		return showAllToDoItems(model); 
	} 

	@RequestMapping(value = { "/UserHomeServlet", "/userHome", "/home", "/Home" }, method = RequestMethod.GET)
	public String showAllToDoItems(Model model){
		//city = readCrud.read(1);
		city = cityFactory.getNewCity();//the stuff from the form

		model.addAttribute("totalPopulation", city.getTotalPopulation());
		model.addAttribute("unnassignedPeople", city.getUnassignedPopulation());
		model.addAttribute("farmers", buildingManager.getPeopleInBuilding(city.getFarm()));
		model.addAttribute("miners", buildingManager.getPeopleInBuilding(city.getMine()));
		model.addAttribute("food", city.getFood());
		model.addAttribute("gold", city.getGold());

		return "userHome"; 
	} 
	
	@RequestMapping(value = { "/MineServiceServlet", "/mineService" }, method = RequestMethod.GET)
	public String displayMinerAsignerForm(Model model) {
		model.addAttribute("currentAssigned", buildingManager.getPeopleInBuilding(city.getMine()));
		int maxAssignable = buildingManager.getPeopleInBuilding(city.getMine()) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		return "mineServicePage";
	}
	
	@RequestMapping(value = { "/MineServiceServlet", "/mineService" }, method = RequestMethod.POST)
	public String submitNewMinerAssignment(@RequestBody HashMap<String,String> formData) {
		int newAssignedPopulation = Integer.valueOf(formData.get("newAssignedPopulation"));
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getMine(), newAssignedPopulation);
		return "userHome";
	}
	
	@RequestMapping(value = {  "/Farmservice", "/farmservice", "/farmService", "/FarmService" }, method = RequestMethod.GET)
	public String displayFarmerAsignerForm(Model model) {
		model.addAttribute("currentAssigned", buildingManager.getPeopleInBuilding(city.getFarm()));
		int maxAssignable = buildingManager.getPeopleInBuilding(city.getFarm()) + city.getUnassignedPopulation();
		model.addAttribute("maxAssignable", maxAssignable);
		return "farmServicePage";
	}
	
	@RequestMapping(value = {  "/Farmservice", "/farmservice", "/farmService", "/FarmService" }, method = RequestMethod.POST)
	public String submitNewFarmerAssignment(@RequestBody HashMap<String,String> formData) {
		int newAssignedPopulation = Integer.valueOf(formData.get("newAssignedPopulation"));
		clientComand.setNumberOfWorkersInResourceBuildingForCity(city, city.getFarm(), newAssignedPopulation);
		return "userHome";
	}
	
}

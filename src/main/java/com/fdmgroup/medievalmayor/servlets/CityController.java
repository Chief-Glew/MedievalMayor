package com.fdmgroup.medievalmayor.servlets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.medievalmayor.City;
import com.fdmgroup.medievalmayor.CRUD.GenericRead;
import com.fdmgroup.medievalmayor.CRUD.GenericWrite;
import com.fdmgroup.medievalmayor.building.BuildingManager;

public class CityController {
	@Autowired
	private GenericRead<City> readCrud;
	@Autowired
	private GenericWrite<City> writeCrud;
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private City city;
  
	@RequestMapping(value = { "/UserHomeServlet", "/userHome", "/home", "/Home" }, method = RequestMethod.GET)
	 public String showAllToDoItems(Model model){
		city = readCrud.read(1);
		
		model.addAttribute("totalPopulation", city.getTotalPopulation());
		model.addAttribute("unnassignedPeople", city.getUnassignedPopulation());
		model.addAttribute("farmers", buildingManager.getPeopleInBuilding(city.getFarm()));
		model.addAttribute("miners", buildingManager.getPeopleInBuilding(city.getMine()));
		model.addAttribute("food", city.getFood());
		model.addAttribute("gold", city.getGold());
		
		return "userHome"; 
	} 
	
//    @RequestMapping(value = "/addItem", method = RequestMethod.GET)
//    public ModelAndView addItem() {
//        return new ModelAndView("addItem", "toDoItem", new ToDoItem());
//    }
//	
//    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
//    public String submit(@ModelAttribute("toDoItem") final ToDoItem toDoItem, final BindingResult result, final ModelMap model) {
//        if (result.hasErrors()) {
//            return "error";
//        }
//    	model.addAttribute("name", toDoItem.getName());
//        model.addAttribute("description", toDoItem.getDescription());
//        model.addAttribute("dueDate", toDoItem.getDueDate());
//        writeCrud.addItemToList(toDoItem);
//        return "showall";
//    }	 TODO

}

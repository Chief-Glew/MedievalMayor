package com.fdmgroup.medievalmayor.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.medievalmayor.game.City;
import com.fdmgroup.medievalmayor.game.CityService;
import com.fdmgroup.medievalmayor.game.building.BuildingManager;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.FarmService;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

/**
 * Servlet implementation class FarmServiceServlet
 */
@WebServlet({ "/Farmservice{}", "/farmservice", "/farmService", "/FarmService" })
public class FarmServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FarmService farmService;  
	private BuildingManager buildingManager;
	private CityService cityService;
	private City city;
	private Farm farm;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FarmServiceServlet() {
		super(); 
		farmService = FarmService.getInstance();
		buildingManager = BuildingManager.getInstance();
		cityService = CityService.getInstance();
		
	} 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("currentAssigned", buildingManager.getPeopleInBuilding(farmService));
		int maxAssignable = buildingManager.getPeopleInBuilding(farmService) + cityService.getUnassignedPopulation();
		request.setAttribute("maxAssignable", maxAssignable);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./WEB-INF/farmService.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int newAssignedPopulation = Integer.valueOf(request.getParameter("newAssignedPopulation"));
		try {
			buildingManager.assignPeopleToBuilding(newAssignedPopulation, farmService);
		} catch (InsufficentPopulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AssignedNegativeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./userHome");
	}

}

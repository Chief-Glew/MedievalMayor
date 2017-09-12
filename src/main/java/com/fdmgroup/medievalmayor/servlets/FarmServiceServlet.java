package com.fdmgroup.medievalmayor.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.medievalmayor.City;
import com.fdmgroup.medievalmayor.building.BuildingManager;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

/**
 * Servlet implementation class FarmServiceServlet
 */
@WebServlet({ "/Farmservice", "/farmservice", "/farmService", "/FarmService" })
public class FarmServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Farm farm;  
	private BuildingManager buildingManager;
	private City city;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FarmServiceServlet() {
		super(); 
		farm = Farm.getInstance();
		buildingManager = BuildingManager.getInstance();
		city = City.getInstance();
	} 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("currentAssigned", buildingManager.getPeopleInBuilding(farm));
		int maxAssignable = buildingManager.getPeopleInBuilding(farm) + city.getUnassignedPopulation();
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
			buildingManager.assignPeopleToBuilding(newAssignedPopulation, farm);
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

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
import com.fdmgroup.medievalmayor.building.resourcebuilding.Farms;
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mines;

/**
 * Servlet implementation class UserHomeServlet
 */
@WebServlet({ "/UserHomeServlet", "/userHome", "/home", "/Home", "/UserHome", "/Userhome", "/userhome" })
public class UserHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private City city;
    private BuildingManager buildingManager;
    private Farms farms;
    private Mines mines;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserHomeServlet() {
        super();
        city = City.getInstance();
        buildingManager = BuildingManager.getInstance();
        farms = Farms.getInstance();
        mines = Mines.getInstance();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("unnassignedPeople", city.getUnassignedPopulation());
		request.setAttribute("farmers", buildingManager.getPeopleInBuilding(farms));
		request.setAttribute("miners", buildingManager.getPeopleInBuilding(mines));
		request.setAttribute("food", city.getFood());
		request.setAttribute("gold", city.getGold());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./WEB-INF/userHome.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		city.updateResources();
		doGet(request, response);
	}

}

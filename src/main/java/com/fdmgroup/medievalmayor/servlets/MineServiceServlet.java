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
import com.fdmgroup.medievalmayor.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.exceptions.InsufficentPopulationException;

/**
 * Servlet implementation class MineServiceServlet
 */
@WebServlet({ "/MineServiceServlet", "/mineService" })
public class MineServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Mine mine;
    private BuildingManager buildingManager;
	private City city;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MineServiceServlet() {
        super();
        mine = Mine.getInstance();
        buildingManager = BuildingManager.getInstance();
		city = City.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("currentAssigned", buildingManager.getPeopleInBuilding(mine));
		int maxAssignable = buildingManager.getPeopleInBuilding(mine) + city.getUnassignedPopulation();
		request.setAttribute("maxAssignable", maxAssignable);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./WEB-INF/mineService.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int newAssignedPopulation = Integer.valueOf(request.getParameter("newAssignedPopulation"));
		try {
			buildingManager.assignPeopleToBuilding(newAssignedPopulation, mine);
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

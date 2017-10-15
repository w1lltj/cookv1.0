package cook1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cook1.model.*;

/**
 * Servlet implementation class CookUserDashboardSearchMenuServlet
 */
@WebServlet("/CookUserDashboardSearchMenuServlet")
public class CookUserDashboardSearchMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserDashboardSearchMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		/* acquire the existing session*/
		HttpSession session = request.getSession(false);
		String cookuser_found_cookUserId = (String)session.getAttribute("cookuser_found_cookUserId");
		
		/* retrieve all the params passed by dashboard.jsp (executed by btnSearchSubmit)*/
		String dashboard_search_menuDate = request.getParameter("dashboard_search_menuDate");
		String dashboard_search_menuName = request.getParameter("dashboard_search_menuName");
		String dashboard_search_menuCategory = request.getParameter("dashboard_search_menuCategory");
		double dashboard_search_menuPrice = Double.parseDouble(request.getParameter("dashboard_search_menuPrice"));
		String dashboard_search_menuArea = request.getParameter("dashboard_search_menuArea");
		
		/*testing*/
		/*System.out.println("session acquired: " + cookuser_found_cookUserId);
		System.out.println(dashboard_search_menuDate);
		System.out.println(dashboard_search_menuName);
		System.out.println(dashboard_search_menuCategory);
		System.out.println(dashboard_search_menuPrice);
		System.out.println(dashboard_search_menuArea);*/
		
		/* compose all the retrieved params into a cookproduct object(cookuserDashboardSearchRequest)*/
		cookproduct cookuserDashboardSearchRequest = 
				new cookproduct(0, cookuser_found_cookUserId, dashboard_search_menuDate, dashboard_search_menuName, 
						dashboard_search_menuCategory, null, dashboard_search_menuArea, null, dashboard_search_menuPrice);
		
		
		/* set all the attributes/objects to pass to CookUserDashboardTableViewServlet for further process*/
		/* you can set multiple attributes into the request (but forward to only one resource: servlet, jsp, etc.)*/
		//request.setAttribute("cookuser_found_id", cookuser_found_cookUserId);
		request.setAttribute("cookuserDashboardSearchRequest", cookuserDashboardSearchRequest);
		RequestDispatcher rd = request.getRequestDispatcher("CookUserDashboardTableViewServlet");
		rd.forward(request, response);	
		
	}

}

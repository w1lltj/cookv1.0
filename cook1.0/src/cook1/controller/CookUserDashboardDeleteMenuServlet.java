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
 * Servlet implementation class CookUserDashboardDeleteMenuServlet
 */
@WebServlet("/CookUserDashboardDeleteMenuServlet")
public class CookUserDashboardDeleteMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserDashboardDeleteMenuServlet() {
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
		
		/* get cookuser id from session*/
		HttpSession session = request.getSession(false);
		String cookuser_found_cookUserId = (String)session.getAttribute("cookuser_found_cookUserId");
		/* get the selected record(s)*/
		String[] dashboard_tableview_checkbox = request.getParameterValues("dashboard_tableview_checkbox");
		
		
		cookproductDB cookuserDashboardDeletemenuRequest = new cookproductDB();
		int cookuserDashboardDeletemenuResponse = cookuserDashboardDeletemenuRequest.cookUserDeleteMenu(
				cookuser_found_cookUserId, dashboard_tableview_checkbox);
		
		
		request.setAttribute("cookuserDashboardDeletemenuResponse", cookuserDashboardDeletemenuResponse);
		RequestDispatcher rd = request.getRequestDispatcher("CookUserDashboardTableViewServlet");
		rd.forward(request, response);
		
	}

}

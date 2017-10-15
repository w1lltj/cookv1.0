package cook1.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cook1.model.*;

/**
 * Servlet implementation class CookUserDashboardAddMenuViewServlet
 */
@WebServlet("/CookUserDashboardAddMenuViewServlet")
public class CookUserDashboardAddMenuViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserDashboardAddMenuViewServlet() {
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
		/* get counter value (initially passed from dashboard.jsp which is "0")*/
		int addmenu_counter = Integer.parseInt(request.getParameter("addmenu_counter"));
		
		/* cookuserMenuCategoryResponse to store category information*/
		cookproductDB cookuserDashboardAddmenuRequest = new cookproductDB();
		ArrayList<String> cookuserMenuCategoryResponse = cookuserDashboardAddmenuRequest.cookUserMenuCategory(cookuser_found_cookUserId);
		
		
		request.setAttribute("cookuserMenuCategoryResponse", cookuserMenuCategoryResponse);
		request.setAttribute("addmenu_counter", addmenu_counter);
		RequestDispatcher rd = request.getRequestDispatcher("addmenu.jsp");
		rd.forward(request, response);
		
	}

}

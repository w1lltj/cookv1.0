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
 * Servlet implementation class CookUserDashboardTableViewServlet
 */
@WebServlet("/CookUserDashboardTableViewServlet")
public class CookUserDashboardTableViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserDashboardTableViewServlet() {
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
		/* get searched params passed by CookUserDashboardSearchMenuServlet(if any)*/
		cookproduct cookuserDashboardSearchRequest = (cookproduct)request.getAttribute("cookuserDashboardSearchRequest");
		/* get number of deleted rows passed by CookUserDashboardDeleteMenuServlet(if any)*/
		//String cookuserDashboardDeletemenuResponse = request.getParameter("cookuserDashboardDeletemenuResponse");
		
		/* cookuserDashbaordRequest           ... to pass any request from dashboard.jsp
		 * cookuserDashboardTableViewResponse ... to store the result of the request and forward back to dashboard.jsp*/
		cookproductDB cookuserDashboardRequest = new cookproductDB();
		ArrayList<cookproduct> cookuserDashboardTableViewResponse = new ArrayList<cookproduct>();
				
		/* cookuserDashboardTableViewResponse has 2 types of response to dashboard.jsp
		 * 1) if any search request from dashboard.jsp, respond with the result of the searched request (cookuserDashboardRequest.cookUserSearch)
		 * 2) if no search request from dashboard.jsp, respond with the result of all available record (cookuserDashboardRequest.cookUserDashboardTableView)*/
		if(cookuserDashboardSearchRequest!=null){
			cookuserDashboardTableViewResponse = 
					cookuserDashboardRequest.cookUserSearchMenu(cookuserDashboardSearchRequest.getUser_id(), 
							cookuserDashboardSearchRequest.getMenu_date(), cookuserDashboardSearchRequest.getMenu_name(), 
							cookuserDashboardSearchRequest.getMenu_category(), cookuserDashboardSearchRequest.getMenu_price(), 
							cookuserDashboardSearchRequest.getMenu_area());
		}else{
			cookuserDashboardTableViewResponse = cookuserDashboardRequest.cookUserDashboardTableView(cookuser_found_cookUserId);
		}
		/* cookuserMenuCategoryResponse to store all the categories of the particular cookuser id*/
		ArrayList<String> cookuserMenuCategoryResponse = cookuserDashboardRequest.cookUserMenuCategory(cookuser_found_cookUserId);
		/* cookuserMenuMaxPriceResponse to store the Max price of the particular cookuser id*/
		double cookuserMenuMaxPriceResponse = cookuserDashboardRequest.cookUserMenuMaxPrice(cookuser_found_cookUserId);
		
		
		/* set all the attributes/objects to pass to dashboard.jsp*/
		/* you can set multiple attributes into the request (but forward to only one resource: servlet, jsp, etc.)*/
		request.setAttribute("cookuserDashboardTableViewResponse", cookuserDashboardTableViewResponse);
		request.setAttribute("cookuserMenuCategoryResponse", cookuserMenuCategoryResponse);
		request.setAttribute("cookuserMenuMaxPriceResponse", cookuserMenuMaxPriceResponse);
		/*if(cookuserDashboardDeletemenuResponse!=null){
			request.setAttribute("cookuserDashboardDeletemenuResponse", cookuserDashboardDeletemenuResponse);
		}*/
		
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
				
	}

}

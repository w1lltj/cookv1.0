package cook1.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cook1.model.cookproduct;
import cook1.model.cookproductDB;

/**
 * Servlet implementation class DummyServlet
 */
@WebServlet("/DummyServlet")
public class DummyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DummyServlet() {
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
		
		
		/*Dashboard Menu Search Testing*/
		/*===================================================================================================================*/
		/*cookproduct cookuserDashboardSearchRequest = (cookproduct)request.getAttribute("cookuserDashboardSearchRequest");
		
		System.out.println(cookuserDashboardSearchRequest.getUser_id());
		System.out.println(cookuserDashboardSearchRequest.getMenu_date());
		System.out.println(cookuserDashboardSearchRequest.getMenu_name());
		System.out.println(cookuserDashboardSearchRequest.getMenu_category());
		System.out.println(cookuserDashboardSearchRequest.getMenu_price());
		System.out.println(cookuserDashboardSearchRequest.getMenu_area());
		
		cookproductDB cookuserDashboardRequest = new cookproductDB();
		ArrayList<cookproduct> cookuserDashboardTableViewResponse = new ArrayList<cookproduct>();
		
		System.out.println(cookuserDashboardTableViewResponse);
		for(cookproduct obj: cookuserDashboardTableViewResponse){
			System.out.println(obj.getMenu_area()+"testarea");
		}
		
		cookuserDashboardTableViewResponse = 
				cookuserDashboardRequest.cookUserSearch(cookuserDashboardSearchRequest.getUser_id(), 
						cookuserDashboardSearchRequest.getMenu_date(), cookuserDashboardSearchRequest.getMenu_name(), 
						cookuserDashboardSearchRequest.getMenu_category(), cookuserDashboardSearchRequest.getMenu_price(), 
						cookuserDashboardSearchRequest.getMenu_area());
		
		for(cookproduct obj: cookuserDashboardTableViewResponse){
			System.out.println(obj.getMenu_area());
		}*/
		
		/*===================================================================================================================*/
		
		
		
		
		
	}

}

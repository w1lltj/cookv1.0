package cook1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cook1.model.*;

/**
 * Servlet implementation class CookUserAddMenuServlet
 */
@WebServlet("/CookUserAddMenuServlet")
public class CookUserAddMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserAddMenuServlet() {
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
		
		int addmenu_counter = Integer.parseInt(request.getParameter("addmenu_counter"));
		String addmenu_userId = request.getParameter("addmenu_userId");
		String addmenu_menuDate = request.getParameter("addmenu_menuDate");
		String addmenu_menuName = request.getParameter("addmenu_menuName");
		String addmenu_menuCategory = request.getParameter("addmenu_menuCategory");
		String addmenu_menuPic = request.getParameter("addmenu_menuPic");
		double addmenu_menuPrice = Double.parseDouble(request.getParameter("addmenu_menuPrice"));
		String addmenu_menuArea = request.getParameter("addmenu_menuArea");
		String addmenu_menuComment = request.getParameter("addmenu_menuComment");
		
		cookproductDB cookuserAddmenuRequest = new cookproductDB();
		int cookuserAddmenuResponse = cookuserAddmenuRequest.cookUserAddMenu(
				addmenu_userId, addmenu_menuDate, addmenu_menuName, addmenu_menuCategory, addmenu_menuPic, addmenu_menuPrice, 
				addmenu_menuArea, addmenu_menuComment);
		
		if(cookuserAddmenuResponse>0){
			addmenu_counter += cookuserAddmenuResponse;
		}
		
		request.setAttribute("addmenu_counter", addmenu_counter);
		RequestDispatcher rd = request.getRequestDispatcher("addmenu.jsp");
		rd.forward(request, response);
		
	}

}

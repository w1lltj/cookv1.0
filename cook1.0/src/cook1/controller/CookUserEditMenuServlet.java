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
 * Servlet implementation class CookUserEditMenuServlet
 */
@WebServlet("/CookUserEditMenuServlet")
public class CookUserEditMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserEditMenuServlet() {
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
		/* get all the information passed from editmenu.jsp*/
		int editmenu_menuId = Integer.parseInt(request.getParameter("editmenu_menuId"));
		String editmenu_menuDate = request.getParameter("editmenu_menuDate");
		String editmenu_menuName = request.getParameter("editmenu_menuName");
		String editmenu_menuCategory = request.getParameter("editmenu_menuCategory");
		String editmenu_menuPic = request.getParameter("editmenu_menuPic");
		double editmenu_menuPrice = Double.parseDouble(request.getParameter("editmenu_menuPrice"));
		String editmenu_menuArea = request.getParameter("editmenu_menuArea");
		String editmenu_menuComment = request.getParameter("editmenu_menuComment");
		
		/* save the edited information (if succeed, return "1", else "0")*/
		cookproductDB cookuserEditmenuRequest = new cookproductDB();
		int cookuserEditmenuResponse = cookuserEditmenuRequest.cookUserEditMenu(
				editmenu_menuId, cookuser_found_cookUserId, editmenu_menuDate, editmenu_menuName, editmenu_menuCategory, editmenu_menuPic, 
				editmenu_menuPrice, editmenu_menuArea, editmenu_menuComment);
		/*testing*/
		//System.out.println("cookuserEditmenuResponse: " + cookuserEditmenuResponse);
		
		/* pass the information to CookUserDashboardTableViewServlet which will direct to dashboard.jsp*/
		request.setAttribute("cookuserEditmenuResponse", cookuserEditmenuResponse);
		RequestDispatcher rd = request.getRequestDispatcher("CookUserDashboardTableViewServlet");
		rd.forward(request, response);
	}

}

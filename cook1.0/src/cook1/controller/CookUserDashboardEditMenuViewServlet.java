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
 * Servlet implementation class CookUserDashboardEditMenuServlet
 */
@WebServlet("/CookUserDashboardEditMenuViewServlet")
public class CookUserDashboardEditMenuViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserDashboardEditMenuViewServlet() {
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
		/* get the selected record (menu id) to be edited*/
		String dashboard_tableview_editmenu = request.getParameter("dashboard_tableview_editmenu");
		/* retrieve editmenu_menuPicUpload_menuId & editmenu_menuPicUpload_picName from CookUserEditMenuUploadPicServlet*/
		String[] cookuserEditmenuUploadPicResponse = (String[])request.getAttribute("cookuserEditmenuUploadPicResponse");
		/* to store the new pic filename (if the pic is changed)*/
		String editmenu_menuPicUpload_picName = new String();
		
		/*testing*/
		System.out.println("dashboard_tableview_editmenu: " + dashboard_tableview_editmenu);
		System.out.println("cookuserEditmenuResponse: " + cookuserEditmenuUploadPicResponse);
		
		if(cookuserEditmenuUploadPicResponse!=null){
			dashboard_tableview_editmenu = cookuserEditmenuUploadPicResponse[0];
			editmenu_menuPicUpload_picName = cookuserEditmenuUploadPicResponse[1];
		}
		
		
		cookproductDB cookuserDashboardEditmenuRequest = new cookproductDB();
		cookproduct cookuserDashboardEditmenuResponse = cookuserDashboardEditmenuRequest.cookUserEditMenuView(
				cookuser_found_cookUserId, Integer.parseInt(dashboard_tableview_editmenu));
		System.out.println("picname: "+cookuserDashboardEditmenuResponse.getMenu_pic());
		
		request.setAttribute("cookuserDashboardEditmenuResponse", cookuserDashboardEditmenuResponse);
		request.setAttribute("editmenu_menuPicUpload_picName", editmenu_menuPicUpload_picName);
		RequestDispatcher rd = request.getRequestDispatcher("editmenu.jsp");
		rd.forward(request, response);
		
	}

}

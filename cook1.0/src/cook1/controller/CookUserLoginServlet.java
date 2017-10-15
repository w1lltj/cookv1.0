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
 * Servlet implementation class CookUserLoginServlet
 */
@WebServlet("/CookUserLoginServlet")
public class CookUserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookUserLoginServlet() {
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
		System.out.println("OK");
		String cookUserId = request.getParameter("login_cookUserId");
		String cookUserPw = request.getParameter("login_cookUserPw");
		
		cookuserDB cookuser_check = new cookuserDB();
		cookuser cookuser_found = cookuser_check.cookUserLoginCheck(cookUserId, cookUserPw);
		
		if(cookuser_found!=null){
			HttpSession session = request.getSession();
			session.invalidate(); //destroy the initial created session for security purpose
			session = request.getSession(); //re-get the session
			session.setAttribute("cookuser_found_cookUserId", cookuser_found.getUser_id());
			session.setMaxInactiveInterval(60*30);
			
			
			/* setattribute for user id is not required because user id can be obtained from session*/
			//request.setAttribute("cookuser_found_id", cookuser_found.getUser_id());
			RequestDispatcher rd = request.getRequestDispatcher("CookUserDashboardTableViewServlet");
			rd.forward(request, response);
			
			/*difference: sendRedirect vs forward request
			forward() method
			1) The forward() method works at server side.
			2) It sends the same request and response objects to another servlet.	
			3) It can work within the server only.	
			- Example: request.getRequestDispacher("servlet2").forward(request,response);	
			sendRedirect() method
			1) The sendRedirect() method works at client side.
			2) It always sends a new request.
			3) It can be used within and outside the server.
			- Example: response.sendRedirect("servlet2");
			Which is why, for servlet which needs to do request & response, we need to use forward() instead of sendRedirect()*/
			
		}else{
			response.sendRedirect("login.jsp?loginMsg=User ID/ Password is NOT Correct!");
		}
		
	}

}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, java.util.Date, java.text.SimpleDateFormat, cook1.model.*, java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cook Dashboard</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>

<%
//out.println(request.getContextPath() + "<br/>");
//out.println(getServletContext().getRealPath("/"));
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

String cookuser_found_cookUserId = (String)session.getAttribute("cookuser_found_cookUserId");

@SuppressWarnings("unchecked") //to silence the warning of casting from non-generic to a generic type (the following ArrayList)
ArrayList<cookproduct> cookuserDashboardTableViewResponse = (ArrayList<cookproduct>)request.getAttribute("cookuserDashboardTableViewResponse");

@SuppressWarnings("unchecked") //to silence the warning of casting from non-generic to a generic type (the following ArrayList)
ArrayList<String> cookuserMenuCategoryResponse = (ArrayList<String>)request.getAttribute("cookuserMenuCategoryResponse");

double cookuserMenuMaxPriceResponse = (double)request.getAttribute("cookuserMenuMaxPriceResponse");

//String cookuserDashboardDeletemenuResponse = (String)request.getAttribute("cookuserDashboardDeletemenuResponse");

if(cookuser_found_cookUserId!=null){
		
%>
	<body>
		
		<div id="dashboard-header">
			<div id="dashboard-header-top" style="text-align:right">
				<div id="dashboard-header-top-userid">Welcome, <span style="color:blue; font-weight:bold"><%=cookuser_found_cookUserId %></span> !!</div>
				<div id="dashboard-header-top-logout">
					<form action="CookUserLogoutServlet" method="post">
						<input type="submit" name="btnLogoutSubmit" value="Logout" />
					</form>
				</div>
			</div>
			<%-- deleted row(s): <%=(cookuserDashboardDeletemenuResponse!=null)?Integer.parseInt(cookuserDashboardDeletemenuResponse):0 %> --%>
		</div>
		
		<div id="dashboard-body">
			<h2>Cook Dashboard</h2>
			<hr></hr>
			
			<div id="dashboard-body-navi">
				<div id="dashboard-body-navi-searchmenu" style="">
					<fieldset>
						<legend>Search Menu</legend>
						<form action="CookUserDashboardSearchMenuServlet" method="post">
							<div id="dashboard-body-navi-searchmenu-date">Date:</div>
							<input type="text" name="dashboard_search_menuDate" />
							<div id="dashboard-body-navi-searchmenu-name">Name:</div>
							<input type="text" name="dashboard_search_menuName" />
							<div id="dashboard-body-navi-searchmenu-category">Category:</div>
							<select name="dashboard_search_menuCategory">
								<option value="All">All</option>
								<%
								for(String obj: cookuserMenuCategoryResponse){
									%>
									<!-- need to surround attribute value with QUOTES.Otherwise the white space will be interpreted as HTML 
									element attribute separator and the next String(right after white space) will be interpreted as another HTML 
									element attribute -->
									<option value="<%=obj %>"><%=obj %></option>
									<%
								}
								%>
							</select>
							<div id="dashboard-body-navi-searchmenu-maxprice">Max. Price:</div>
							<input type="text" name="dashboard_search_menuPrice" value=<%=cookuserMenuMaxPriceResponse %> />
							<div id="dashboard-body-navi-searchmenu-pickup">Pickup:</div>
							<input type="text" name="dashboard_search_menuArea" />
							<br/><br/>
							<input type="submit" name="btnSearchSubmit" value="Search" />
						</form>
					</fieldset>
				</div>
				
				<br/>
				
				<div id="dashboard-body-navi-modify">
					<div id="dashboard-body-navi-modify-addmenu" style="">
						<form action="CookUserDashboardAddMenuViewServlet" method="post">
							<input type="hidden" name="addmenu_counter" value="0" />
							<input type="submit" name="btnAddSubmit" value="Add Menu" />
						</form>
					</div>
					
					<br/>
					
					<div id="dashboard-body-navi-modify-deletemenu" style="">
						<form id="formDeleteMenu" action="CookUserDashboardDeleteMenuServlet" method="post">
							<input type="submit" name="btnDeleteSubmit" value="Delete Menu" />
						</form>
					</div>
				</div>
			</div>
					
			<div id="dashboard-body-main" style="clear:left">
				<br/>
				<div id="dashboard-body-main-tableview">
					<table border=1 width=100% style="border-collapse:collapse">
						<tr>
							<th>
								<input type="checkbox" />
							</th>
							<th></th>
							<th>Date</th>
							<th>Menu Name</th>
							<th>Category</th>
							<th>Picture</th>
							<th>Price</th>
							<th>Pickup</th>
							<th>Comments</th>
						</tr>
						<%
						
						for(cookproduct obj: cookuserDashboardTableViewResponse){
							%>		
							<tr>
								<td>
									<div class="dashboard-body-main-tableview-checkbox" style="text-align:center">
										<input form="formDeleteMenu" type="checkbox" name="dashboard_tableview_checkbox" value=<%=obj.getMenu_id() %> />
									</div>
								</td>
								<td>
									<div class="dashboard-body-main-tableview-editmenu" style="text-align:center">
										<form action="CookUserDashboardEditMenuViewServlet" method="post">
											<input type="hidden" name="dashboard_tableview_editmenu" value=<%=obj.getMenu_id() %> />
											<input type="submit" name="btnEditSubmit" value="Edit" />
										</form>
									</div>
								</td>
								<td><%=obj.getMenu_date() %></td>
								<td><%=obj.getMenu_name() %></td>
								<td><%=obj.getMenu_category() %></td>
								<td>
									<div class="dashboard-body-main-tableview-menupic" style="height:100px; text-align:center">
										<img height=100% src=<%=request.getContextPath() + "/img/" + obj.getMenu_pic() %> />
									</div>
								</td>
								<td><%=obj.getMenu_price() %></td>
								<td><%=obj.getMenu_area() %></td>
								<td><%=obj.getMenu_comment() %></td>
							</tr>
							<%
						}	
						%>
					</table>
				</div>
			</div>
		</div>
		
		<div id="dashboard-footer">
		</div>
	</body>
<%
}else{
	
	response.sendRedirect("login.jsp?loginMsg=fail-logging-in");
	
}

%>

</html>
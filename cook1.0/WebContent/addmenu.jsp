<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Cook Menu</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>

<%
/* retrieve uploaded pic filename passed from CookUserDashboardUploadPicServlet */
String addmenu_menuPicUpload_picName = (String)request.getAttribute("addmenu_menuPicUpload_picName");
/* get user id from session */
String cookuser_found_cookUserId = (String)session.getAttribute("cookuser_found_cookUserId");

@SuppressWarnings("unchecked")
ArrayList<String> cookuserMenuCategoryResponse = (ArrayList<String>)request.getAttribute("cookuserMenuCategoryResponse");

int addmenu_counter = (int)request.getAttribute("addmenu_counter");

if(cookuser_found_cookUserId!=null){
	
%>

	<body>
		
		<div id="addmenu-header">
			<div id="addmenu-header-top" style="text-align:right">
				<div id="addmenu-header-top-userid">Welcome, <span style="color:blue; font-weight:bold"><%=cookuser_found_cookUserId %></span> !!</div>
				<div id="addmenu-header-top-logout">
					<form action="CookUserLogoutServlet" method="post">
						<input type="submit" name="addmenu_btnLogoutSubmit" value="Logout" />
					</form>
				</div>
			</div>	
		</div>
		
		
		<div id="addmenu-body">
			<h2>Add Cook Menu</h2>
			<hr></hr>
			<div id="addmenu-body-main">
				<div id="addmenu-body-main-uploadpic">
					<form action="CookUserDashboardUploadPicServlet" method="post" enctype="multipart/form-data">
						<input type="hidden" name="addmenu_counter" value=<%=addmenu_counter %> />
						Choose Picture:<br/>
						<input type="file" name="addmenu_menuPicUpload" accept="image/*" />
						<input type="submit" name="btnUploadPicSubmit" value="Upload" />
					</form>
				</div>
				
				<div id="addmenu-body-main-addmenudata">
					<form action="CookUserAddMenuServlet" method="post">
						<input type="hidden" name="addmenu_counter" value=<%=addmenu_counter %> />
						<input type="hidden" name="addmenu_userId" value="<%=cookuser_found_cookUserId %>" /><br/>
						Date:<br/>
						<input type="text" name="addmenu_menuDate" /><br/>
						Name:<br/>
						<input type="text" name="addmenu_menuName" /><br/>
						Category:<br/>
						<input type="text" name="addmenu_menuCategory" /><br/>
						
						<!-- NEXT TASK - Put Options "Add New Category" or "Choose from Existing Category" -->
						<%-- <input type="radio" name="menuCategory" value="" />Choose Existing:<br/>
							<select name="addmenu_menuCategory">
								<%
								for(String obj: cookuserMenuCategoryResponse){
									%>
									<option value=<%=obj %>><%=obj %></option>
									<%
								}
								%>
							</select><br/>
						<input type="radio" name="menuCategory" value="" />Add New:<br/>
							<input type="text" name="" /><br/> --%>
						
						Picture:<br/>
						<input type="text" name="addmenu_menuPic" readonly="readonly" value=
						<%=((addmenu_menuPicUpload_picName!=null && addmenu_menuPicUpload_picName.length()>0)? addmenu_menuPicUpload_picName: "-") %> /><br/>
						Preview:<br/>
						<img style="width:15%" src=<%=request.getContextPath() + "/img/" + 
						((addmenu_menuPicUpload_picName!=null && addmenu_menuPicUpload_picName.length()>0)? 
								addmenu_menuPicUpload_picName: "No_image_available.png") %> ><br/>
						Price:<br/>
						<input type="text" name="addmenu_menuPrice" /><br/>
						Pickup Address:<br/>
						<input type="text" name="addmenu_menuArea" /><br/>
						Comments:<br/>
						<textarea rows="10" cols="40" name="addmenu_menuComment" ></textarea>
						<br/><br/>
						<input type="submit" name="btnAddSubmit" value="Save" />
						<input type="reset" name="btnClear" value="Clear" />
						
					</form>
				</div>
			</div>
		</div>
	
		<div id="addmenu-footer">
		</div>
	</body>

<%

}else{
	
	response.sendRedirect("login.jsp?loginMsg=fail-logging-in");
	
}

%>

</html>
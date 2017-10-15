<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="cook1.model.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Cook Menu</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>

<%
/* get user id from session */
String cookuser_found_cookUserId = (String)session.getAttribute("cookuser_found_cookUserId");
/* retrieve data from CookUserDashboardEditMenuViewServlet */
cookproduct cookuserDashboardEditmenuResponse = (cookproduct)request.getAttribute("cookuserDashboardEditmenuResponse");
/* retrieve new pic filename (if the picture is changed) from CookUserEditMenuUploadPicServlet */
String editmenu_menuPicUpload_picName = (String)request.getAttribute("editmenu_menuPicUpload_picName");

if(cookuser_found_cookUserId!=null){
	
%>

	<body>
			
		<div id="editmenu-header">
			<div id="editmenu-header-top" style="text-align:right">
				<div id="editmenu-header-top-userid">Welcome, <span style="color:blue; font-weight:bold"><%=cookuser_found_cookUserId %></span> !!</div>
				<div id="editmenu-header-top-logout">
					<form action="CookUserLogoutServlet" method="post">
						<input type="submit" name="editmenu_btnLogoutSubmit" value="Logout" />
					</form>
				</div>
			</div>		
		</div>
	
		<div id="editmenu-body">
			<h2>Edit Cook Menu</h2>
			<hr></hr>
			<div id="editmenu-body-main">
				<div id="editmenu-body-main-uploadpic">
					<form action="CookUserEditMenuUploadPicServlet" method="post" enctype="multipart/form-data">
						<input type="hidden" name="editmenu_menuPicUpload_menuId" value="<%=cookuserDashboardEditmenuResponse.getMenu_id() %>" />
						Choose Picture:<br/>
						<input type="file" name="editmenu_menuPicUpload" accept="image/*" />
						<input type="submit" name="btnChangePicSubmit" value="Change" />
					</form>
				</div>
				
				<div id="editmenu-body-main-editmenudata">
					<form action="CookUserEditMenuServlet" method="post">
						<input type="hidden" name="editmenu_counter" value="" />
						<input type="hidden" name="editmenu_menuId" value="<%=cookuserDashboardEditmenuResponse.getMenu_id() %>" /><br/>
						Date:<br/>
						<input type="text" name="editmenu_menuDate" value="<%=cookuserDashboardEditmenuResponse.getMenu_date() %>" /><br/>
						Name:<br/>
						<input type="text" name="editmenu_menuName" value="<%=cookuserDashboardEditmenuResponse.getMenu_name() %>" /><br/>
						Category:<br/>
						<input type="text" name="editmenu_menuCategory" value="<%=cookuserDashboardEditmenuResponse.getMenu_category() %>" /><br/>
						
						<!-- NEXT TASK - Put Options "edit New Category" or "Choose from Existing Category" -->
						<%-- <input type="radio" name="menuCategory" value="" />Choose Existing:<br/>
							<select name="editmenu_menuCategory">
								<%
								for(String obj: cookuserMenuCategoryResponse){
									%>
									<option value=<%=obj %>><%=obj %></option>
									<%
								}
								%>
							</select><br/>
						<input type="radio" name="menuCategory" value="" />edit New:<br/>
							<input type="text" name="" /><br/> --%>
							
						Picture:<br/>
						<input type="text" name="editmenu_menuPic" readonly="readonly" value=
						"<%=((editmenu_menuPicUpload_picName!=null && editmenu_menuPicUpload_picName.length()>0)? 
								editmenu_menuPicUpload_picName: cookuserDashboardEditmenuResponse.getMenu_pic()) %>" /><br/>
						Preview:<br/>
						<img style="width:15%" src=
						"<%=request.getContextPath()+"/img/"+((editmenu_menuPicUpload_picName!=null && editmenu_menuPicUpload_picName.length()>0)? 
						editmenu_menuPicUpload_picName: cookuserDashboardEditmenuResponse.getMenu_pic()) %>" ><br/>
						Price:<br/>
						<input type="text" name="editmenu_menuPrice" value="<%=cookuserDashboardEditmenuResponse.getMenu_price() %>" /><br/>
						Pickup Address:<br/>
						<input type="text" name="editmenu_menuArea" value="<%=cookuserDashboardEditmenuResponse.getMenu_area() %>" /><br/>
						Comments:<br/>
						<textarea rows="10" cols="40" name="editmenu_menuComment"><%=cookuserDashboardEditmenuResponse.getMenu_comment() %></textarea>
						<br/><br/>
						<input type="submit" name="btneditSubmit" value="Save" />
						<input type="reset" name="btnClear" value="Clear" />
						
					</form>
				</div>
			</div>
		</div>
		
		<div id="editmenu-footer">
		
		</div>
	</body>

<%
}else{
	
	response.sendRedirect("login.jsp?loginMsg=fail-logging-in");
	
}

%>

</html>
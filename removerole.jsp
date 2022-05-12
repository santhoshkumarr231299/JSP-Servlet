<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="demologininner.LoginDao"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<style>
body{
font-family: 'verdana';
}
</style>
<script>
if ( window.history.replaceState ) {
  window.history.replaceState( null, null, window.location.href );
}
window.addEventListener( "pageshow", function ( event ) {
    var perfEntries = performance.getEntriesByType("navigation");
    if (perfEntries[0].type === "back_forward") {
      location.reload();
    }
   });
</script>
</head>
<body>

<%
		//response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		if(session == null)
			response.sendRedirect("frontpage.jsp");
		else if(session.getAttribute("username")==null)
			response.sendRedirect("frontpage.jsp");
		else if(session.getAttribute("username")!=null && session.getAttribute("admin")==null)
			response.sendRedirect("homepage.jsp");
		else if(session.getAttribute("username")==null && session.getAttribute("admin")==null)
			response.sendRedirect("frontpage.jsp");
		
		
		LoginDao ld = new LoginDao();
		ResultSet rs = ld.getRoles();
%>

<h2 align = "center">ADMIN - REMOVE A ROLE</h2>
<form action = "removerole" method = "post">
<table align = "center" bgcolor = "#EFC050">
<tr><td></>Select the role you want to remove : </td>
<td>
<select name = "role">
<option selected>Select</option>

<% while(rs.next()) { %>
<option><%= rs.getString("Nroles") %></option>
<% } %>
</select>

</td>
</tr>
<tr><td></td><td><input type = "submit" value = "Remove Role"></td></tr>
</table>
</form>
<br>
<center><font color = "red">${alert}</font></center>
</body>
</html>
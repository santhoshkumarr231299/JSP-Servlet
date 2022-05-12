<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="demologininner.LoginDao"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
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
<style>
body{
font-family: 'verdana';
}
</style>

</head>
<body>

<%
       if(session == null)
			response.sendRedirect("frontpage.jsp");
	     if(session.getAttribute("username")==(null))
	   		response.sendRedirect("frontpage.jsp");
	     else if(session.getAttribute("username")!=(null) && session.getAttribute("admin")==null)
	    	 response.sendRedirect("homepage.jsp");
	     LoginDao ld = new LoginDao();
		       ResultSet resultset = ld.allUsers();
%>

<h2 align ="center">ADMIN - ADD ROLE</h2>
<br>
<form action = "addrole" method = "post">
<table align = "center" bgcolor = "#EFC050">
<tr><td>Enter the Role You want to Add:</td><td><input type = "text" name = "adminaddrole" required></td></tr> 
<tr><td></td><td><input type = "submit" value = "Add Role" required></td></tr>
</table>
</form>

</body>
</html>
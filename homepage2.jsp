<%@ page import="java.sql.*" %>
<%@page import="demologininner.LoginDao"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<html>
       <head>
       <title>Home Page</title>
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
       <div align = "center">
       <h1> APPLICATION FORM - ADMIN</h1>
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
       <form action = "delete" method = "post">
      <table border = "1">
      <tr><td>Select</td><td>Photo</td><td>First Name</td><td>Last Name</td><td>Role</td><td>Date of Birth</td><td>Phone Number</td><td>College</td><td>Year of Passing</td><td>Roll Number</td><td>College CGPA</td>
      <td>10th Percentage</td><td>12th Percentage</td><td>Diploma Percentage</td><td>PG CGPA</td><td>Resume</td>
      </tr>
      
      <% while(resultset.next()){ %>
      <tr>
      <td align = "center"><input type = "checkbox" name = "users" value = "<%= resultset.getString("resume") %>"></td>
      <td align = "center"><img src = "images/<%= resultset.getString("photo") %>" width="30" height="30"> </td>
      <td><%= resultset.getString("firstName") %></td>
      <td><%= resultset.getString("lastName") %> </td>
      <td><%= resultset.getString("role") %></td>
      <td><%= resultset.getString("dob") %></td>
      <td><%= resultset.getString("phoneNumber") %></td>
      <td><%= resultset.getString("college") %></td>
      <td><%= resultset.getInt("yearOfPassing") %></td>
      <td><%= resultset.getString("rollNumber") %></td>
      <td><%= resultset.getDouble("clgCGPA") %></td>
      <td><%= resultset.getDouble("tenthPercentage") %></td>
      <td><%= resultset.getDouble("twelvethPercentage") %></td>
      <td><%= resultset.getDouble("diplomaPercentage") %></td>
      <td><%= resultset.getDouble("pg") %></td>
      <td><%-- <a href = "http://localhost:7050/demo/docs/<%= resultset.getString("resume") %>" download>Download</a><br>--%>
      <%-- <a href = "docs/<%= resultset.getString("resume") %>">view</a>  --%>
       <iframe src="docs/<%= resultset.getString("resume") %>"></iframe>
       </td></tr>
       <% } %>
       </table>
       <br>
       <table align = "center">
       <tr><td><input type = "submit" value = "Delete">
       </form>
       </td>
       
       <form action = "logout" method = "post">
       <td><input type = "submit" value = "Logout">
       </form></td>
       
       <form action = "addrole.jsp">
       <td><input type = "submit" value = "Add Role">
       </form>
       </td>
       <td>
       <form action = "removerole.jsp">
       <td><input type = "submit" value = "Remove Role">
       </form>
       </td>
       </tr></table>
     
     <br>
     <font color = "red">${message}</font>
     </div>
     </body>
</html>
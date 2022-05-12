<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="demologininner.LoginDao"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page - ${username}</title>
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

</script>
</head>
<body>
<%
		//response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		if(session == null)
			response.sendRedirect("frontpage.jsp");
		if(session.getAttribute("username")!=null && session.getAttribute("admin")!=null)
			response.sendRedirect("homepage2.jsp");
		else if(session.getAttribute("username")==null)
			response.sendRedirect("frontpage.jsp");
		
		LoginDao ld = new LoginDao();
		ResultSet rs = ld.getRoles();
%>     
<center>
<form action = "application" method = "post" enctype = "multipart/form-data">
<table bgcolor = "#EFC050">
<tr><td><h2>Application Form</h2></td><td><u>Welcome <%= session.getAttribute("username") %>!!!</u></td></tr>
<tr><td>Photo : </td><td><input type = "file" name = "photo" accept = "image/jpg" required ></td></tr>
<tr><td>First Name : </td><td><input type = "text" name = "firstName" required></td></tr> 
<tr><td>Last Name : </td><td><input type = "text" name = "lastName" required></td></tr>
<tr><td>Date of Birth : </td><td><input type="date" name="dob" required></td></tr>
<tr><td>Phone Number : </td><td><input type = "text" name = "phoneNumber" required></td></tr>
<tr><td>Address : </td><td><input type = "text" name = "address" required></td></tr>
<tr><td>College : </td><td><input type = "text" name = "college" required></td></tr>
<tr><td>Year of Passing : </td><td><input type = "text" name = "yearOfPassing" required></td></tr>
<tr><td>College Roll Number : </td><td><input type = "text" name = "rollNumber" required></td></tr>
<tr><td>College CGPA : </td><td><input type = "text" name = "clgCGPA" required></td></tr>
<tr><td>10th Percentage : </td><td><input type = "text" name = "tenthPercentage" required></td></tr>
<tr><td>12th Percentage : </td><td><input type = "text" name = "twelvethPercentage" required></td></tr>
<tr><td>Diploma Percentage if no type '0' : </td><td><input type = "text" name = "diplomaPercentage" required></td></tr>
<tr><td>PG CGPA if no type '0' : </td><td><input type = "text" name = "pg"></td></tr>
<tr><td>Resume : </td><td><input type = "file" name = "resume" accept = "application/pdf" required></td></tr>
<tr><td>Role</td><td>
<select name = "role">
<option selected>Select</option>
<% while(rs.next()) { %>
<option><%= rs.getString("Nroles") %></option>
<% } %>
</select>
</td></tr>
<tr><td></td><td align = "right"><input type = "submit" value = "submit"></td></tr>
</table>
</form>
		<form action = "logout" method = "post">
       <tr><td></td><td align = "center"><br>
       <input type = "submit" value = "logout"></td></tr>
       </form>
        <br>
     <font color = "red">${message}</font>
</center>
</body>
</html>
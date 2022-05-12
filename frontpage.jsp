<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">																
<title>Login Page</title>
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
if(session.getAttribute("username") != null && session.getAttribute("admin") != null)
{
	response.sendRedirect("homepage2.jsp");
}
else if(session.getAttribute("username") != null)
{
	response.sendRedirect("homepage.jsp");
}
%>
<form action = "login" method = "post">
<table bgcolor = "#EFC050" align = "center">
<tr><td align = "center"><h2>LOGIN</h2></td></tr>
<tr><td>Username : </td><td><input type = "text" name = "username" required></td></tr>
<tr><td>Password : </td><td><input type = "password" name = "password" required> </td> </tr>
<tr><td><a href = "newuser.jsp">New User</a></td><td align = "right"><a href = "forgotpass.jsp">Forgot Password</a></td></tr>
<tr><td></td><td align = "right"><input type = "submit" value = "login"></td></tr>
</form>
</table>
<br>
<center><font color = "red">${alert}</font></center>
<%
request.removeAttribute("alert");
%>
</body>
</html>

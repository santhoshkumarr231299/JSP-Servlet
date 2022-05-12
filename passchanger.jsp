<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password Change - ${username}</title>
<style>
body{
font-family: 'verdana';
}
</style>
<script>
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
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		if(session.getAttribute("user") == null)
			response.sendRedirect("frontpage.jsp");
%>  
<form action = "passchanger" method = "post">
<table bgcolor = "#EFC050" align = "center">
<tr><td align = "center"><h2>ZOHO</h2></td></tr>
<tr><td>New Password : </td><td><input type = "text" name = "newpass" required></td></tr>
<tr><td>Confirm New Password : </td><td><input type = "password" name = "reenterpass" required> </td> </tr>
<tr><td></td><td align = "right"><input type = "submit" value = "login"></td></tr>
</table>
</form>
<br>
<center><font color = "red">${alert}</font></center>
</body>
</html>
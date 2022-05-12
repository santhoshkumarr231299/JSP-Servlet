<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User Registration</title>
<style>
body{
font-family: 'verdana';
}
</style>
</head>
<body>
<center>
<form action ="newuser" method = "post">
<table bgcolor = "#EFC050">
<tr><td><h2>New User Registration</h2></td></tr>
<tr><td>Username : </td><td><input type = "text" name = "username" required></td></tr> 
<tr><td>Email : </td><td><input type = "email" name = "email" required></td></tr>
<tr><td>Password : </td><td><input type = "password" name = "password" required></td></tr>
<tr><td><a href = "frontpage.jsp">go to login page</a></td><td></td></tr>
<tr><td></td><td align = "right"><input type = "submit" value = "sign up"></td></tr>
</table>
</center>
</form>
<br>

<center><font color = "red">${alert}</font></center>
</body>
</html>
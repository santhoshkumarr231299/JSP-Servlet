<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">																
<title>Forgot Password</title>
<style>
body{
font-family: 'verdana';
}
</style>
</head>
<body>
<form action = "passchangevalidate" method = "post">
<table bgcolor = "#EFC050" align = "center">
<tr><td align = "center"><h2>Forgot Password</h2></td></tr>
<tr><td>Username : </td><td><input type = "text" name = "username" required></td></tr>
<tr><td>Email : </td><td><input type = "email" name = "email" required> </td> </tr>
<tr><td></td><td align = "right"><input type = "submit" value = "change password"></td></tr>
</form>
</table>
<br>
<center><font color = "red">${alert}</font></center>

</body>
</html>
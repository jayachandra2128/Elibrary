<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Elibrary</title>
<link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
<%@ include file="header.jsp" %> 
</head>
<body style="background-image:url(images/lib.jpg)">
<form id=form1 action="LibrarianLogin" method="post">  
<div id="content">
			<h2 class=l>Librarian Login</h2>
</div>
<table>
	<tr><td class=l>Email:</td><td><input type="email" name="email" required></td></tr> <br> 
	<tr><td class=l>Password:</td><td><input type="password" name="password" required></td></tr><br>
	</table>
<input type="submit" value="login"/>  
</form> 
</body>
</html>
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
<script type="text/javascript" src="javascript/validation.js"></script>
<%@ include file="headernavadmin.jsp" %> 
</head>
<body>
<%
		if (session != null) {
			if (session.getAttribute("email") != null) {
				String name = (String) session.getAttribute("email");
				
			} else {
				response.sendRedirect("index.jsp");
			}
		}
	%>

<form id=form1 action="AddLibrarian" method="post" onsubmit="return validate()">  
<div id="content">
			<h2>Add Librarian</h2>
</div>
<table>
	<tr><td>Name:</td><td><input type="text" name="name" required></td></tr> <br> 
	<tr><td>Email:</td><td><input type="email" name="email" required></td></tr><br>
	<tr><td>Password:</td><td><input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required></td></tr> <br> 
	<tr><td>Mobile Number:</td><td><input type="tel" name="mobile" required></td></tr><br>
	<tr><td>Admin Id:</td><td><input type="text" name="adminid" required></td></tr><br>
	
       
	</table>
<input type="submit" value="Add"/>  
</form> 

</body>
</html>
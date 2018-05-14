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
<%@ include file="headernavlibrarian.jsp" %> 
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
<form id=form1 action="AddBook" method="post" >  
<div id="content">
			<h2>Add Book</h2>
</div>
<table>
	<tr><td>Aile No:</td><td><input type="text" name="aileno" required></td></tr> <br> 
	<tr><td>Name:</td><td><input type="text" name="bookname" required></td></tr><br>
	<tr><td>Author:</td><td><input type="text" name="author" required></td></tr> <br> 
	<tr><td>Publisher:</td><td><input type="text" name="publisher" required></td></tr><br>
	<tr><td>Quantity:</td><td><input type="text" name="quantity" required></td></tr><br>
	
       
	</table>
<input type="submit" value="Add"/>  
</form> 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Header</title>

<link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
</head>
<body>
<div id="header" >
    <h1> ELibrary</h1>
</div>
<header id="menu-wrapper">
		<nav id="menu" >
			<ul class="topnav">
				<li><a href="navlibrarian.jsp">Home</a></li>
				<li><a href="addBook.jsp">Add Book</a></li>
				<li><a href="ViewBook">View Books</a></li>
				<li><a href="IssueBookForm">Issue Book</a></li>
				<li><a href="ViewIssuedBook">View Issued Books</a></li>
				<li><a href="ReturnBookForm">Return Book</a></li>
				<li><form method="post" action="logout">
        		<input type="submit" value="Logout"  />
        		 </form></a></li>
				
             </ul>
		</nav>
</header>
</body>
</html>
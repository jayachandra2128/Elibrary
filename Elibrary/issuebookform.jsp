<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<form id=form1 action="IssueBook" method="post" >  
<div id="content">
			<h2>Add Book</h2>
</div>
<table>
	<tr><td>Aile No:</td><td><input type="text" name="aileno" required></td></tr> <br> 
	<tr><td>Student ID:</td><td><input type="text" name="studentid" required></td></tr><br>
	<tr><td>Librarian ID:</td><td><input type="text" name="librarianid" required></td></tr> <br> 
	
	
	
       
	</table>
<input type="submit" value="Issue"/>  
</form> 
</body>
</html>
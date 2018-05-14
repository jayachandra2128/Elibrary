<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<form id=form1 action="ReturnBook" method="post" >  
<div id="content">
			<h2>Add Book</h2>
</div>
<table>
	<tr><td>Aile No:</td><td><input type="text" name="aileno" required></td></tr> <br> 
	<tr><td>Student ID:</td><td><input type="text" name="studentid" required></td></tr><br>
	
	
	
	
       
	</table>
<input type="submit" value="Return Book"/>  
</form> 
</body>
</html>
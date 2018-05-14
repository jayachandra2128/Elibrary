package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReturnBookForm
 */
@WebServlet("/ReturnBookForm")
public class ReturnBookForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Servlet that redirects user to Return Book Form(returnbookform.jsp)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Issue Book</title>");
		out.println("<link rel='stylesheet' href='styles/main.css'/>");
		out.println("</head>");
		out.println("<body>");
	    request.getRequestDispatcher("headernavlibrarian.jsp").include(request, response);
	    
	    out.println("<div class='container'>");
	    request.getRequestDispatcher("returnbookform.jsp").include(request, response);
	    out.println("</div>");
	    
	    
	    out.close();
	}
}

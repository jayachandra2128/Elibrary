package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//URL annotation
@WebServlet("/IssueBookForm")
public class IssueBookForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public IssueBookForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    //Servlet that redirects user to Issue Book Form(issuebookform.jsp)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    	//Session tracking
    			HttpSession session = request.getSession();
    			if (session != null) {
    				if (session.getAttribute("email") != null) {
    					String name = (String) session.getAttribute("email");
    					
    				} else {
    					response.sendRedirect("index.jsp");
    				}
    			}
    	
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
	    request.getRequestDispatcher("issuebookform.jsp").include(request, response);
	    out.println("</div>");
	    
	    
	    out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

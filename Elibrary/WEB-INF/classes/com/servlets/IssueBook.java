package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import com.beans.IssueBookBean;
import com.dao.BookDao;

//URL annotation
@WebServlet("/IssueBook")
public class IssueBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public IssueBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	//Servlet thats takes values from jsp and calls Dao function to issue book to students.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			// TODO Auto-generated method stub
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.println("<head>");
			out.println("<title>Add Book Form</title>");
			out.println("<link rel='stylesheet' href='styles/main.css'/>");
			out.println("</head>");
			out.println("<body>");
			request.getRequestDispatcher("headernavlibrarian.jsp").include(request, response);
			out.println("<div class='container'>");
			String aileno=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("aileno"), "HTTPParameterValue", 200,
					false);
			int studentid=Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("studentid"), "HTTPParameterValue", 200,
					false));
			int librarianid=Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("librarianid"), "HTTPParameterValue", 200,
					false));
			IssueBookBean bean=new IssueBookBean(aileno,studentid,librarianid);
			int i=BookDao.issueBook(bean);
			if(i>0){
				out.println("<h3>Book issued successfully</h3>");
			}else{
				out.println("<h3>Sorry, unable to issue book.</h3>");
			}
			out.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
			out.close();
		} catch (ValidationException e) {
			
			return;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return;
		}
		
	}

}

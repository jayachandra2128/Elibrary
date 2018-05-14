package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

import com.dao.BookDao;
import com.dao.LibrarianDao;
import java.io.PrintWriter;

//URL annotation
@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Servlet that takes Book aile no and calls Dao function to delete the book from database and redirects to required page.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			/* NOTE: ESIDE has created the following try catch blocks.
			 * If the generated input validation code detects a problem
			 * (e.g., malicious characters entered by user) an exception is thrown.
			 * Doing so will skip the rest of the try block code and go directly to
			 * one of the generated catch blocks below.
			 * */
			try {
				BookDao.delete(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("aileno"), "HTTPParameterValue", 200,
						false));
				response.sendRedirect("ViewBook");
			} catch (IntrusionException e) {
				/* This catch block will be executed when advanced 
				 * intrusion behavior is detected in ESIDE's try block statement. */ 
				
				return;
			}
		} catch (ValidationException e) {
			
			return;
		}
	}

	

}

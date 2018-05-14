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
import com.beans.BookBean;
import com.dao.BookDao;

//URL Annotation
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//Servlet that takes Book details and calls Dao function to save book into database and redirects to required page.
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
			String aileno=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("aileno"), "HTTPParameterValue", 200,
					false);
			String name=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("bookname"), "HTTPParameterValue", 200,
					false);
			String author=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("author"), "HTTPParameterValue", 200,
					false);
			String publisher=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("publisher"), "HTTPParameterValue", 200,
					false);
			int quantity=Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("quantity"), "HTTPParameterValue", 200,
					false));
			BookBean bean=new BookBean(aileno, name, author, publisher,quantity);
			BookDao.save(bean);
			request.getRequestDispatcher("addBook.jsp").include(request, response);
			out.print("<h4>Book added successfully</h4>");
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

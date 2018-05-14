package com.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import com.dao.AdminLoginDao;
import com.dao.LibrarianDao;
import com.util.*;
//URL annotation
@WebServlet("/LibrarianLogin")
public class LibrarianLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LibrarianLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//Servlet takes Librarian login credentials and calls Dao function to validate if credentials are correct and redirects to required page.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String n=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("email"), "Email", 200, false);
			String p=PasswordUtil.hashPassword(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("password"), "HTTPParameterValue", 200,
					false));
		
			if(LibrarianDao.authenticate(n, p)){  
				HttpSession session=request.getSession(true); 
				session.setAttribute("email", n);
			    RequestDispatcher rd=request.getRequestDispatcher("navlibrarian.jsp");  
			    rd.forward(request,response);  
			}  
			else{  
			    RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
			    rd.include(request,response);  
			    out.println("<p>Sorry username or password error<p>");
			}
			out.close();
		} catch (ValidationException e) {
			
			return;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}

}

package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import com.beans.LibrarianBean;
import com.dao.LibrarianDao;
import com.util.MailUtilGmail;

//URL annotation
@WebServlet("/EditLibrarian")
public class EditLibrarian extends HttpServlet {
	//Servlet that takes edited librarian details and call Dao function to update librarian details in database and redirects to required page. 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			String sid=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("id"), "HTTPParameterValue", 200,
					false);
			int id=Integer.parseInt(sid);
			String name=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("name"), "HTTPParameterValue", 200,
					false);
			String email=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("email"), "Email", 200, false);
			String password=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("password"), "HTTPParameterValue", 200,
					false);
			String smobile=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("mobile"), "HTTPParameterValue", 200,
					false);
			long mobile=Long.parseLong(smobile);
			LibrarianBean bean=new LibrarianBean(id,name, email, password, mobile);
			LibrarianDao.update(bean);
			//Email functionality. To send email to Librarian stating his login credentials.
			String to = email;
			String from = "jkaminenreddy@gmail.com";
			String subject = "UNC Charlotte Librarian";
			String body = "Hello"+" "+name+". You Password has been changed. Your credentials are Email:"+email+" Password:"+password+". Please login using credentials. Regards, Jayachandra Kamineni, UNC CHARLOTTE";
			boolean isBodyHTML = false;
			try
			{
			  MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);


			}
			catch (MessagingException e)
			{

			  String errorMessage = "ERROR: Unable to send email. Check Tomcat logs for details.<br>NOTE: You may need to configure your system as described in chapter 14.<br>ERROR MESSAGE: " + e.getMessage();
			  request.setAttribute("errorMessage", errorMessage);
			  System.out.println("Unable to send email");
			}
			response.sendRedirect("ViewLibrarian");
		} catch (ValidationException e) {
			
			return;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return;
		}
	}

}
package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
@WebServlet("/AddLibrarian")
public class AddLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddLibrarian() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	//Servlet that takes Librarian details and calls Dao function to save Librarian into database and redirects to required page.
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
			String name=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("name"), "SafeString", 200, false);
			String email=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("email"), "Email", 200, false);
			String password=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("password"), "HTTPParameterValue", 200,
					false);
			String smobile=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("mobile"), "SafeString", 200, false);
			long mobile=Long.parseLong(smobile);
			int adminid = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("adminid"), "SafeString", 200, false));
			LibrarianBean bean=new LibrarianBean(name, email, password, mobile,adminid);
			LibrarianDao.save(bean);
			
		    //Email functionality. To send email to Librarian stating his login credentials.
		    String to = email;
		    String from = "jkaminenreddy@gmail.com";
		    String subject = "UNC Charlotte Librarian";
		    String body = "Hello"+" "+name+". You are added as Librarian. Your credentials are Email:"+email+" Password:"+password+". Please login using credentials. Regards, Jayachandra Kamineni, UNC CHARLOTTE";
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
		  

			if(LibrarianDao.flag){
			request.getRequestDispatcher("addlibrarian.jsp").include(request, response);
			out.print("<h4>Librarian added successfully</h4>");
			
			out.close();
			}
			else{
				request.getRequestDispatcher("addlibrarian.jsp").include(request, response);
				out.print("<h4>Unsuccesfully. Wrong Admin Id entered </h4>");	
				
			}
		} catch (ValidationException e) {
			/* This catch block is executed when ESIDE finds input that did
			 * not match validation rules (e.g., bad user input). */
			
			/* This is a sample Exception Handling code that might need to be modified by the developers based on the code*/
			
			/* PrintWriter errorout = response.getWriter();
			   String referer;
			   try {
			 	    referer = ESAPI.validator().getValidInput("replace ME with validation context",request.getHeader("Referer"), "URL", 200, false);
			      errorout.print("<h1>ESIDE default exception handling</h1>");
			      errorout.print("<b>The input contains invalid characters or in wrong format! <a href="+ ESAPI.encoder().encodeForHTML(referer));
			      errorout.print(">GoBack</a> and try again!</b>");
			   }catch (ValidationException e1){ }
			*/
			
			
			return;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return;
		}
	}

}

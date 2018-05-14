package com.dao;

import java.sql.Connection;
import com.util.*;

import java.sql.*; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import com.beans.LibrarianBean;
import com.dao.DB;


import java.io.PrintWriter;

public class LibrarianDao {
	
	public static boolean flag=false;

	//Function to insert librarian details into database 
	public static int save(LibrarianBean bean) {
		int status=0;
		flag=false;
		
		try{
			
			Connection con=DB.getCon();
			PreparedStatement ps1=con.prepareStatement("select * from nbad.adminlogin where admin_id=?");
			ps1.setInt(1,bean.getAdmin_id());
			ResultSet rs=ps1.executeQuery();
			if(rs.next()){
			PreparedStatement ps=con.prepareStatement("insert into nbad.e_librarian(name,email,password,mobile,admin_id) values(?,?,?,?,?)");
			ps.setString(1,bean.getName());
			ps.setString(2,bean.getEmail());
			ps.setString(3,PasswordUtil.hashPassword(bean.getPassword()));
			ps.setLong(4,bean.getMobile());
			ps.setInt(5,bean.getAdmin_id());
			status=ps.executeUpdate();
			con.close();
			flag=true;
			
			}
			else{
				
				throw new ArithmeticException("Admin id does not exist");
				
			}
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	
	//Function to edit Librarian details in database
	public static int update(LibrarianBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update nbad.e_librarian set name=?,email=?,password=?,mobile=? where id=?");
			ps.setString(1,bean.getName());
			ps.setString(2,bean.getEmail());
			ps.setString(3,PasswordUtil.hashPassword(bean.getPassword()));
			ps.setLong(4,bean.getMobile());
			ps.setInt(5,bean.getId());
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	
	//Functionality to view librarians from database
	public static List<LibrarianBean> view(){
		List<LibrarianBean> list=new ArrayList<LibrarianBean>();
		try {
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from nbad.e_librarian");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LibrarianBean bean=new LibrarianBean();
				bean.setId(rs.getInt("id"));
				bean.setName(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString("name"), "SafeString", 200,
						false));
				bean.setEmail(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString("email"), "Email", 200, false));
				bean.setPassword(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString("password"), "HTTPParameterValue",
						200, false));
				bean.setMobile(rs.getLong("mobile"));
				list.add(bean);
			}
			con.close();
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
			
			
			return null;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	//Functionality View Librarian By ID from database
	public static LibrarianBean viewById(int id){
		LibrarianBean bean=new LibrarianBean();
		try {
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from nbad.e_librarian where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setId(rs.getInt(1));
				bean.setName(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(2), "HTTPParameterValue",
						200, false));
				bean.setPassword(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(3), "HTTPParameterValue",
						200, false));
				bean.setEmail(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(4), "HTTPParameterValue",
						200, false));
				bean.setMobile(rs.getLong(5));
				bean.setAdmin_id(rs.getInt(6));
			}
			con.close();
		} catch (ValidationException e) {
			
			return null;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return bean;
	}
	
	//Functionality to delete librarian from database
	public static int delete(int id){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from nbad.e_librarian where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}

	//Validate Librarian login functionality
	public static boolean authenticate(String email,String password){
		boolean status=false;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from nbad.e_librarian where email=? and password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
}

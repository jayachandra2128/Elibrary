package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import com.beans.BookBean;
import com.beans.IssueBookBean;
import com.beans.LibrarianBean;
import java.io.PrintWriter;

public class BookDao {

	// Functionality to save book into database
	public static int save(BookBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into nbad.e_book values(?,?,?,?,?,?)");
			ps.setString(1,bean.getAileno());
			ps.setString(2,bean.getName());
			ps.setString(3,bean.getAuthor());
			ps.setString(4,bean.getPublisher());
			ps.setInt(5,bean.getQuantity());
			ps.setInt(6,0);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	
	//Functionality to View Books from database
	public static List<BookBean> view(){
		List<BookBean> list=new ArrayList<BookBean>();
		try {
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from nbad.e_book");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				BookBean bean=new BookBean();
				bean.setAileno(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString("aileno"), "HTTPParameterValue",
						200, false));
				bean.setName(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString("name"), "HTTPParameterValue",
						200, false));
				bean.setAuthor(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString("author"), "HTTPParameterValue",
						200, false));
				bean.setPublisher(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString("publisher"), "HTTPParameterValue",
						200, false));
				bean.setQuantity(rs.getInt("quantity"));
				bean.setIssued(rs.getInt("issued"));
				
				
				list.add(bean);
			}
			con.close();
		} catch (ValidationException e) {
			
			return null;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	//functionality to Delete Book from database
	public static int delete(String aileno){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from nbad.e_book where aileno=?");
			ps.setString(1,aileno);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	
	//Function to retrieve no of books issued
	public static int getIssued(String aileno){
		int issued=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from nbad.e_book where aileno=?");
			ps.setString(1,aileno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				issued=rs.getInt("issued");
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return issued;
	}
	
	public static boolean checkIssue(String aileno){
		boolean status=false;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from nbad.e_book where aileno=? AND quantity>issued");
			ps.setString(1,aileno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	
	//Functionality to View Issued Books from database
	public static List<IssueBookBean> viewIssuedBooks(){
		List<IssueBookBean> list=new ArrayList<IssueBookBean>();
		try {
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from nbad.e_issuebook order by isuuedate desc");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				IssueBookBean bean=new IssueBookBean();
				bean.setAileno(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString("aileno"), "HTTPParameterValue",
						200, false));
				bean.setStudentid(rs.getInt("studentid"));
				bean.setLibrarianid(rs.getInt("lib_id"));
				bean.setIssueddate(rs.getDate("isuuedate"));
				bean.setReturnstatus(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString("returnstatus"), "HTTPParameterValue",
						200, false));
				list.add(bean);
			}
			con.close();
		} catch (ValidationException e) {
			
			return null;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	//Functionality to Update database on returning book
	public static int returnBook(String aileno,int studentid){
		int status=0;
			try{
				Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("update nbad.e_issuebook set returnstatus='yes' where aileno=? and studentid=?");
				ps.setString(1,aileno);
				ps.setInt(2,studentid);
				
				status=ps.executeUpdate();
				if(status>0){
					PreparedStatement ps2=con.prepareStatement("update nbad.e_book set issued=? where aileno=?");
					ps2.setInt(1,getIssued(aileno)-1);
					ps2.setString(2,aileno);
					status=ps2.executeUpdate();
				}
				con.close();
				
			}catch(Exception e){System.out.println(e);}
			
			return status;
	}
	
	//Function to Update database after issuing book to student.
	public static int issueBook(IssueBookBean bean){
		String aileno=bean.getAileno();
		boolean checkstatus=checkIssue(aileno);

		if(true){
			int status=0;
			try{
				Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("insert into nbad.e_issuebook values(?,?,?,?,?)");
				ps.setString(1,bean.getAileno());
				ps.setInt(2,bean.getStudentid());
				ps.setInt(3,bean.getLibrarianid());
				
				java.sql.Date currentDate=new java.sql.Date(System.currentTimeMillis());
				ps.setDate(4,currentDate);
				ps.setString(5,"no");
				
				status=ps.executeUpdate();
				if(status>0){
					PreparedStatement ps2=con.prepareStatement("update nbad.e_book set issued=? where aileno=?");
					ps2.setInt(1,getIssued(aileno)+1);
					ps2.setString(2,aileno);
					status=ps2.executeUpdate();
				}
				con.close();
				
			}catch(Exception e){System.out.println(e);}
			
			return status;
		}else{
			return 0;
		}
	}
	

}


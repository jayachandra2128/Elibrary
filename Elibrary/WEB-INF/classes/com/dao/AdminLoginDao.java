package com.dao;

import java.sql.*;  

public class AdminLoginDao {  

//Admin Login Validation used during Admin login 
public static boolean validate(String name,String pass){  
boolean status=false;  
try{  
Connection con=DB.getCon();      
PreparedStatement ps=con.prepareStatement(  
"select * from nbad.adminlogin where email=? and password=?");  
ps.setString(1,name);  
ps.setString(2,pass);  
ResultSet rs=ps.executeQuery();  
if(rs.next()){
	status=true;
}
con.close();

}catch(Exception e){System.out.println(e);}

return status; 
}  
}  

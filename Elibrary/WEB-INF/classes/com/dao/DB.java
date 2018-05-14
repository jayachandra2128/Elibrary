package com.dao;

import java.sql.*;

public class DB {
	
	public static Connection getCon() throws ClassNotFoundException, SQLException
	{
	   Connection conn=null;
	   Class.forName("com.mysql.jdbc.Driver");
	   String dbName = "nbad";
	   String userName = "root";
	   String password = "kamineniJAYA";
	   String hostname = "nbad1.cpyaa8acewu0.us-east-2.rds.amazonaws.com";
	   String port = "3306";
	   String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
	   conn= DriverManager.getConnection(jdbcUrl);
	   //System.out.println("connection" + conn);
	   
	   return conn;
	   
	   }

}

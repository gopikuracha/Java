package com.kriss.sample.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	// JDBC driver name and database URL
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/People";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "Kishy646$$";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver - Not required after JDBC 4.0 in Java 1.7
			//Class.forName(JDBC_DRIVER);

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM Person";
			ResultSet rs = stmt.executeQuery(sql);
  
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("person_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");

				//Display values
				System.out.print("ID: " + id);
				System.out.print(", First: " + first_name);
				System.out.println(", Last: " + last_name);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try { if(stmt!=null) stmt.close(); } catch(SQLException se2) {}
			try { if(conn!=null) conn.close(); } catch(SQLException se) {se.printStackTrace();}
		}//end try
		System.out.println("Goodbye!");
	}
	
}
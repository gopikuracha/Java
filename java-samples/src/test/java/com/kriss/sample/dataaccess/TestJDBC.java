package com.kriss.sample.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TestJDBC {

	static final String DB_URL = "jdbc:mysql://localhost/People";
	static final String USER = "root";
	static final String PASS = "Kishy646$$";
	
	static Connection conn = null;
	static Statement stmt = null;
			
	@BeforeClass
	public static void init() {
		try {
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJDBCConnection() {
		try {
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM Person";
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
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void exit() {
		try { if(stmt != null) stmt.close(); } catch(SQLException e) {e.printStackTrace();}
		try { if(conn != null) conn.close(); } catch(SQLException e) {e.printStackTrace();}
	}
}

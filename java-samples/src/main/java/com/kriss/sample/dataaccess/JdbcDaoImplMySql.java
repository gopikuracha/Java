package com.kriss.sample.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDaoImplMySql {
	// JDBC driver name
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	// Default DB values
	public static final String DEFAULT_DB_URL = "jdbc:mysql://localhost/People";
	public static final String DEFAULT_USER = "root";
	public static final String DEFAULT_PASS = "Kishy646$$";
	public static final String DEFAULT_SQL = "SELECT * FROM Person";
	
	private String url;
	private String user;
	private String pswd;
	
	private Connection conn = null;
	private Statement stmt = null;
	
	public JdbcDaoImplMySql() {}
	
	public JdbcDaoImplMySql(String url, String userName, String password) {
		this.url = url;
		this.user = userName;
		this.pswd = password;
	}
	
	public void connect() {
		try {
			//Register JDBC driver - Not required after JDBC 4.0 in Java 1.7
			//Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			url = (url==null) ? DEFAULT_DB_URL : url;
			conn = DriverManager.getConnection(url);
		} catch(SQLException e) {
			e.printStackTrace();
			connectWithCredentials();
		}
	}
	
	public void connectWithCredentials() {
		try {
			System.out.println("Connecting to database with credentials...");
			url = (url==null) ? DEFAULT_DB_URL : url;
			user = (user==null) ? DEFAULT_USER : user;
			pswd = (pswd==null) ? DEFAULT_PASS : pswd;
			conn = DriverManager.getConnection(url,user,pswd);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeStatement() {
		executeStatement(null);
	}
	
	public void executeStatement(String sql) {
		connect();
		try {
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			sql = (sql==null) ? DEFAULT_SQL : sql;
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			for (int i=1; i<=metaData.getColumnCount(); i++) {
				System.out.print(metaData.getColumnName(i));
				System.out.print("\t");
			}
			System.out.println();
			//Extract data from result set
			while(rs.next()){
				//Retrieve by column index
				for (int i=1; i<=metaData.getColumnCount(); i++) {
					System.out.print(rs.getObject(i));
					System.out.print("\t");
				}
				System.out.println();
				
				/*int id  = rs.getInt("person_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");*/
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(stmt!=null) stmt.close(); } catch(SQLException se) {se.printStackTrace();}
			try { if(conn!=null) conn.close(); } catch(SQLException se) {se.printStackTrace();}
		}
	}
	
	public static void main(String[] args) {
		JdbcDaoImplMySql dao = new JdbcDaoImplMySql();
		dao.connect();
		dao.executeStatement();
	}
	
}
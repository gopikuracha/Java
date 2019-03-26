package com.kriss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.kriss.collection.adt.DynamicTDS;
import com.kriss.collection.adt.ObjectTDS;
import com.kriss.collection.adt.StaticTDS;

public class ExtractMySQLData {
	
	// JDBC driver name
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private String url;
	private String user;
	private String pswd;
	
	public boolean keepConnectionAlive;
	public boolean printMetaData = true;
	public boolean printMetaDataOnce;
	
	protected Connection conn = null;
	protected Statement stmt = null;
	protected PreparedStatement pstmt = null;
	
	public ExtractMySQLData() {}
	
	public ExtractMySQLData(String url, String userName, String password) {
		this.url = url;
		this.user = userName;
		this.pswd = password;
	}
	
	public void connectWithCredentials() {
		try {
			System.out.println("Connecting to database with credentials...");
			if (conn == null) conn = DriverManager.getConnection(url,user,pswd);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeStatement(String sql) {
		if (sql == null) { 
			System.out.println("Statement Cannot be null...");
			return;
		}
		try {
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			if (printMetaData) {
				printResultSetMetaData(metaData, columnCount);
				if (printMetaDataOnce) printMetaData = false;
			}
			
			while(rs.next()){
				for (int i=1; i<=columnCount; i++) {
					System.out.print(rs.getObject(i));
					System.out.print("\t");
				}
				System.out.println();
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}
	
	public StaticTDS extractStaticData(String sql) {

		if (sql == null) { 
			System.out.println("Statement Cannot be null...");
			return null;
		}
		connectWithCredentials();
		StaticTDS tds = null;
		try {
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			if (printMetaData) {
				printResultSetMetaData(metaData, columnCount);
				if (printMetaDataOnce) printMetaData = false;
			}
			
			tds = new StaticTDS(1888, columnCount, false);
			
			int row = -1;
			while(rs.next()){
				row++;
				for (int i=1; i<=columnCount; i++) {
					tds.setValue(row, i-1, rs.getObject(i));
				}
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tds;
	
	}

	public DynamicTDS extractData(String sql) {
		if (sql == null) { 
			System.out.println("Statement Cannot be null...");
			return null;
		}
		connectWithCredentials();
		DynamicTDS tds = null;
		try {
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			if (printMetaData) {
				printResultSetMetaData(metaData, columnCount);
				if (printMetaDataOnce) printMetaData = false;
			}
			
			tds = new DynamicTDS(columnCount, true);
			
			int row = -1;
			while(rs.next()){
				row++;
				for (int i=1; i<=columnCount; i++) {
					tds.setValue(row, i-1, rs.getObject(i));
				}
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tds;
	}
	
	public <T> ObjectTDS<T> extractObjectData(T obj, String sql) {
		if (sql == null) { 
			System.out.println("Statement Cannot be null...");
			return null;
		}
		connectWithCredentials();
		ObjectTDS<T> tds = null;
		try {
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			if (printMetaData) {
				printResultSetMetaData(metaData, columnCount);
				if (printMetaDataOnce) printMetaData = false;
			}
			
			tds = new ObjectTDS<T>(obj);
			
			int row = -1;
			while(rs.next()){
				row++;
				for (int i=1; i<=columnCount; i++) {
					//tds.setValue(row, i-1, rs.getObject(i));
				}
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tds;
	}
	
	public int printResultSetMetaData(ResultSetMetaData metaData, int columnCount) {
		try {
			System.out.println("Resulting Columns: " + columnCount);
			for (int i=1; i<=columnCount; i++) {
				System.out.print(metaData.getColumnName(i));
				System.out.print("\t");
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnCount;
	}
	
	
	public void closeResources() {
		try { if(stmt!=null) stmt.close(); } catch(SQLException se) {se.printStackTrace();}
		try { if(pstmt!=null) pstmt.close(); } catch(SQLException se) {se.printStackTrace();}
		if (!keepConnectionAlive) {
			System.out.println("Closing Connection...");
			try { if(conn!=null) conn.close(); } catch(SQLException se) {se.printStackTrace();}
		}
	}
	
	public PreparedStatement getPreparedStatement(String sql) {
		try { if(pstmt!=null) pstmt.close(); } catch(SQLException se) {se.printStackTrace();}
		try { pstmt = conn.prepareStatement(sql); } catch(SQLException se) {se.printStackTrace();}
		return pstmt;
	}
	
}
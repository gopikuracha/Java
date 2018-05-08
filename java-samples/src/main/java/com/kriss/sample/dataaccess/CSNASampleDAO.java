package com.kriss.sample.dataaccess;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CSNASampleDAO extends JdbcDAOMySQLImpl {
	
	public CSNASampleDAO() {}
	
	public CSNASampleDAO(String url, String userName, String password) {
		setUrl(url);
		setUser(userName);
		setPswd(password);
	}
	
	public static void main(String[] args) {
		CSNASampleDAO dao = new CSNASampleDAO("jdbc:mysql://vwawcrntmyp003.ctlejsd73m0w.us-east-1.rds.amazonaws.com:3306/csna", "krishgo", "MxB2K!g2HK");
		try {
			dao.connectWithCredentials();
			dao.executeStatement(DAOConstants.CSNA_SAMPLE_SQL);
		} finally {
			dao.closeConnection();
		}
	}
	
	public void executeStatement(String sql) {
		try {
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			for (int x=0; x<10; x++) {
				sql = DEFAULT_SQL;
				sql = sql.replace("%REPLACE%", Integer.toString(x));
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData metaData = rs.getMetaData();
				for (int i=1; i<=metaData.getColumnCount(); i++) {
					System.out.print(metaData.getColumnName(i));
					System.out.print("\t");
				}
				//System.out.println();
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
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

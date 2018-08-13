package com.kriss.sample.dataaccess;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author krishgo
 *
 */
/**
 * @author krishgo
 *
 */
public class AccsSubsFixFromVztDump extends JdbcDAOMySQLImpl {
	
	private List<Account> accounts = new ArrayList<Account>();
	
	private Map<String,String> missingAccs = new HashMap<String, String>();
	
	private Writer writer = null;
	private Writer writer1 = null;
	private Writer writer2 = null;
	
	public AccsSubsFixFromVztDump() {}
	
	public AccsSubsFixFromVztDump(String url, String userName, String password) {
		setUrl(url);
		setUser(userName);
		setPswd(password);
	}
	
	public static void main(String[] args) {
		AccsSubsFixFromVztDump dao = 
				new AccsSubsFixFromVztDump("jdbc:mysql://vwawcrntmyp003.ctlejsd73m0w.us-east-1.rds.amazonaws.com:3306/csna","krishgo","MxB2K!g2HK");
		try {
			dao.connectWithCredentials();
			dao.execute();
		} finally {
			dao.closeConnection();
		}
	}
	
	public void execute() {
		System.out.println(System.currentTimeMillis());
		getAccountsFromDump();
		
		try {
			
			writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("C:/AMS/subs-fix-report.csv"), "utf-8"));
			writer2 = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("C:/AMS/subs-fix-inserts-asu.txt"), "utf-8"));
			writer1 = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("C:/AMS/subs-fix-CVA-ACC-Missing.txt"), "utf-8"));
			
			for (Account acc : accounts) {
				PreparedStatement ps = conn.prepareStatement(DAOConstants.SQL_CVA_FROM_CSNA);
				try {
					ps.setInt(1, acc.dumpAccNum);
					ps.setString(2, acc.dumpSubsName);
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()) {
						acc.cvaAccNum = rs.getInt(1);
						acc.cvaIdent = rs.getInt(2);
						acc.cvaVcvIdent = rs.getInt(3);
						acc.cvaAccStatus = rs.getString(4);
						
						acc.asuIdent = rs.getInt(5);
						acc.asuSubsNum = rs.getString(6);
						acc.asuSstStatus = rs.getString(7);
						acc.asuSprIdent = rs.getInt(8);
						acc.asuSubsStartDate = rs.getDate(9);
						acc.asuSubsEndDate = rs.getDate(10);
					}
					
					while (rs.next()) {
						System.out.println("Multiple Active Similar Product for : " + acc.dumpAccNum + " : " + acc.dumpSubsName);
					}

					if(acc.cvaAccNum == 0) getCvaIdentFromAccount(acc);
					if(acc.cvaIdent != 0 && acc.asuIdent == 0 && "ACTIVE".equals(acc.cvaAccStatus)) prepareInsertForASU(acc);
					
					if (acc.cvaVcvIdent == 0) getVcvIdentFromCustomer(acc);
					
					StringBuffer buffer = new StringBuffer();
					buffer.append(acc.dumpAccNum).append(",").append(acc.dumpVin).append(",").append(acc.dumpSubsName).append(",").append(acc.dumpEmail);
					
					buffer.append(",").append(acc.cvaIdent).append(",").append(acc.cvaVcvIdent).append(",").append(acc.vcvIdent).append(",").append(acc.cvaAccStatus);
					
					buffer.append(",").append(acc.asuIdent).append(",").append(acc.asuSstStatus).append(",").append(acc.asuSprIdent);
					
					buffer.append(",").append(acc.dumpAgrStartDate).append(",").append(acc.asuSubsStartDate).append(",").append(acc.dumpAgrEndDate).append(",").append(acc.asuSubsEndDate);
					
					buffer.append(",").append(acc.dumpAgrNum).append(",").append(acc.asuSubsNum);
					
					writer.write(buffer.toString());
					writer.write("\n");
					
					rs.close();
				} catch(SQLException sqle) { 
					sqle.printStackTrace(); 
				} finally {
					try { if(ps!=null) ps.close(); } catch(SQLException se) {se.printStackTrace();}
				}
			}
			
			for (Map.Entry<String, String> entry : missingAccs.entrySet()) {
				writer1.write(entry.getKey() + "," + entry.getValue());
			}

			writer.flush();
			writer1.flush();
			writer2.flush();
			System.out.println(System.currentTimeMillis());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			try { if(writer!=null) writer.close(); } catch(IOException ie) {ie.printStackTrace();}
			try { if(writer1!=null) writer1.close(); } catch(IOException ie) {ie.printStackTrace();}
			try { if(writer2!=null) writer2.close(); } catch(IOException ie) {ie.printStackTrace();}
		}
	}
	
	
	/**
	 * 
	 */
	public void getAccountsFromDump() {
		try {
			ResultSet rs = getPreparedStatement(DAOConstants.SQL_ACC_FROM_DUMP).executeQuery();
			while(rs.next()) {
				Account acc = new Account();
				acc.dumpAccNum = rs.getInt(1);
				acc.dumpVin = rs.getString(2);
				acc.dumpAgrNum = rs.getString(3);
				acc.dumpSubsName = rs.getString(4);
				acc.dumpAgrStartDate = rs.getDate(5);
				acc.dumpAgrEndDate = rs.getDate(6);
				acc.dumpEmail = rs.getString(7);
				accounts.add(acc);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public void getCvaIdentFromAccount(Account acc) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(DAOConstants.SQL_CVA);
			ps.setInt(1, acc.dumpAccNum);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				acc.cvaIdent = rs.getInt(1);
				acc.cvaVcvIdent = rs.getInt(2);
				acc.cvaAccStatus = rs.getString(3);
			}
			rs.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try { if(ps!=null) ps.close(); } catch(SQLException se) {se.printStackTrace();}
		}
	}
	
	public void getVcvIdentFromCustomer(Account acc) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(DAOConstants.SQL_VCV_FROM_CSNA);
			ps.setString(1, acc.dumpEmail);
			ps.setString(2, acc.dumpVin);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				acc.vcvIdent = rs.getInt(1);
				missingAccs.put(Integer.toString(acc.dumpAccNum), Integer.toString(acc.vcvIdent));
			}
			if(rs.next()) System.out.println("Result Set has more than 1 record for VCV, Acc : " + acc.dumpAccNum);
			rs.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try { if(ps!=null) ps.close(); } catch(SQLException se) {se.printStackTrace();}
		}
	}
	
	public void getSprIdentForProduct(Account acc) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(DAOConstants.SQL_SPR);
			ps.setString(1, acc.dumpSubsName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				acc.asuSprIdent = rs.getInt(1);
			}
			if(rs.next()) System.out.println("Result Set has more than 1 SPR Ident for Product, Name : " + acc.dumpSubsName);
			rs.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try { if(ps!=null) ps.close(); } catch(SQLException se) {se.printStackTrace();}
		}
	}
	
	public void prepareInsertForASU(Account acc) {
		try {
			if (acc.dumpAgrStartDate == null) return;
			getSprIdentForProduct(acc);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String argStart = format.format(acc.dumpAgrStartDate);
			StringBuffer buffer = new StringBuffer(DAOConstants.SQL_INSERT_TO_ASU_CSNA);
			buffer.append(acc.asuSprIdent).append(", 1, ").append(acc.cvaIdent).append(", '").append(acc.dumpAgrNum).append("', '").append(argStart).append("', ");
			if (acc.dumpAgrEndDate == null) { 
				buffer.append("NULL");
			} else {
				String argEnd = format.format(acc.dumpAgrEndDate);
				buffer.append("'" + argEnd + "'");
			}
			buffer.append(", 0, NULL, 'AMS', 'AMS', 'VZT_DUMP', 1);");
			writer2.write(buffer.toString());
			writer2.write("\n");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
}

class Account {
	
	int dumpAccNum;
	String dumpVin;
	String dumpAgrNum;
	String dumpSubsName;
	Date dumpAgrStartDate;
	Date dumpAgrEndDate;
	String dumpEmail;
	
	int cvaAccNum;
	int cvaIdent;
	int cvaVcvIdent;
	int vcvIdent;
	String cvaAccStatus;
	
	int asuIdent;
	String asuSubsNum;
	String asuSstStatus;
	int asuSprIdent;
	Date asuSubsStartDate;
	Date asuSubsEndDate;
	
	

}


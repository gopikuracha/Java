package com.kriss.sample.dataaccess;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSNADAO4 extends JdbcDAOMySQLImpl {

	public static final String SQL_ACC_FROM_DUMP = "select distinct dump.ACCOUNT_NUM, dump.AGREEMENT_NUMBER from test_csna.vzt_dump dump "
										+ "where dump.AGREEMENT_END_DATE is null or dump.AGREEMENT_END_DATE > curtime() limit 30000,30000";
	public static final String SQL_CVA_FROM_CSNA = "select distinct dump.ACCOUNT_NUM, dump.AGREEMENT_NUMBER "
										+ ", cva.CVA_IDENT, dump.email_addr, cva.VCV_IDENT "
										+ ", ast.ACCT_STAT_TYP "
										+ ", asu.ASU_IDENT, sst.AGRMNT_STAT_TYP "
										+ ", dump.AGREEMENT_START_DATE, asu.SUBS_START_DTE "
										+ ", dump.AGREEMENT_END_DATE, asu.SUBS_END_DTE, spri.SPR_IDENT "
										+ "from test_csna.vzt_dump dump "
										+ "left join csna.cva_cust_veh_acct cva on cva.ACCT_NUM = dump.ACCOUNT_NUM "
										+ "left join csna.ast_acct_stat_type ast on ast.AST_IDENT = cva.AST_IDENT "
										+ "left join csna.asu_acct_subs asu on asu.SUBS_NUM = dump.AGREEMENT_NUMBER "
										+ "left join csna.spr_subs_product spr on spr.SPR_IDENT = asu.SPR_IDENT "
										+ "left join csna.sst_subs_stat_type sst on sst.SST_IDENT = asu.SST_IDENT "
										+ "left join csna.spr_subs_product spri on spri.SUBS_PROD_NME = dump.SUBSCRIPTION_NAME "
										+ "where dump.ACCOUNT_NUM = ? and dump.AGREEMENT_NUMBER = ?";
	
	public static final String SQL_VCV_FROM_CSNA = "select vcv.VCV_IDENT from csna.cus_customer cus "
										+ "inner join csna.vcv_cust_veh vcv on vcv.CUS_IDENT = cus.CUS_IDENT "
										+ "where vcv.VCV_IDENT = vcv.PRIMARY_VCV_IDENT and cus.PERNL_EMAIL_ADDR = ?";
	
	public static final String SQL_INSERT_TO_ASU_CSNA = "INSERT INTO `asu_acct_subs` "
										+ "(`SPR_IDENT`, `SST_IDENT`, `CVA_IDENT`, `SUBS_NUM`, `SUBS_START_DTE`, `SUBS_END_DTE`, `SUBS_MNTH_CNT`, `SUBS_PAIRED_DEVICES_CNT`, "
										+ "`ASU_ADD_BY_USER`, `ASU_UPD_BY_USER`, `ASU_REC_SRC_CDE`, `ACT_IDENT`) VALUES (";
	
	
	private String[][] accounts = new String[30000][2];
	
	private Writer writer = null;
	private Writer writer1 = null;
	
	public CSNADAO4() {}
	
	public CSNADAO4(String url, String userName, String password) {
		setUrl(url);
		setUser(userName);
		setPswd(password);
	}
	
	public static void main(String[] args) {
		CSNADAO4 dao = new CSNADAO4();
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
			int accPrev = 0;
			int accNow = 0;
			int accCounter = 1;
			
			writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("C:/AMS/subs-fix.csv"), "utf-8"));
			writer1 = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("C:/AMS/subs-fix-inserts-asu.txt"), "utf-8"));
			
			for (int x=0; x<30000; x++) {
				System.out.println("X : " + x);
				String vcv_ident = null;
				PreparedStatement ps = conn.prepareStatement(SQL_CVA_FROM_CSNA);
				try {
					ps.setString(1, accounts[x][0]);
					ps.setString(2, accounts[x][1]);
					ResultSet rs = ps.executeQuery();
					rs.next();
					
					String accountNum = String.valueOf(rs.getObject(1));
					String agrNum = String.valueOf(rs.getObject(2));
					String cvaIdent = String.valueOf(rs.getObject(3));
					String email = String.valueOf(rs.getObject(4));
					String cvaVcvIdent = String.valueOf(rs.getObject(5));
					String accountStatType = String.valueOf(rs.getObject(6));
					String asuIdent = String.valueOf(rs.getObject(7));
					String agrStatType = String.valueOf(rs.getObject(8));
					String agrStart = String.valueOf(rs.getObject(9));
					String subsStart = String.valueOf(rs.getObject(10));
					String agrEnd = String.valueOf(rs.getObject(11));
					String subsEnd = String.valueOf(rs.getObject(12));
					String sstIdent = String.valueOf(rs.getObject(13));
					
					StringBuffer buffer = new StringBuffer(Integer.toString(x));
					buffer.append(",").append(accountNum).append(",").append(agrNum).append(",").append(cvaIdent).append(",").append(email).append(",").append(cvaVcvIdent);
					
					if ("null".equals(cvaVcvIdent)) vcv_ident = getVcvIdentFromCustomer(email);
					else if ("null".equals(asuIdent)) prepareInsertForASU(sstIdent, cvaIdent, agrNum, agrStart, agrEnd);
					
					buffer.append(",").append(vcv_ident).append(",").append(accountStatType).append(",").append(asuIdent).append(",").append(agrStatType)
								.append(",").append(agrStart).append(",").append(subsStart).append(",").append(agrEnd).append(",").append(subsEnd).append(",").append(sstIdent);
					
					accPrev = accNow;
					accNow = Integer.parseInt(accountNum);
					if (accPrev == accNow) accCounter++;
					else accCounter = 1;
					
					buffer.append(",").append(accCounter);
					writer.write(buffer.toString());
					writer.write("\n");
					
					if(rs.next()) System.out.println("Result Set has more than 1 record");
					rs.close();
				} catch(SQLException sqle) { 
					sqle.printStackTrace(); 
				} finally {
					try { if(ps!=null) ps.close(); } catch(SQLException se) {se.printStackTrace();}
				}
			}
			writer.flush();
			writer1.flush();
			System.out.println(System.currentTimeMillis());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			try { if(writer!=null) writer.close(); } catch(IOException ie) {ie.printStackTrace();}
			try { if(writer1!=null) writer1.close(); } catch(IOException ie) {ie.printStackTrace();}
		}
	}
	
	public void getAccountsFromDump() {
		PreparedStatement ps = null;
		try {
			int accNum = 0;
			int count = 0;
			String agrNum = null;
			ps = conn.prepareStatement(SQL_ACC_FROM_DUMP);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accNum = rs.getInt(1);
				agrNum = rs.getString(2);
				accounts[count][0] = Integer.toString(accNum);
				accounts[count][1] = agrNum;
				count++;
			}
			rs.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try { if(ps!=null) ps.close(); } catch(SQLException se) {se.printStackTrace();}
		}
	}
	
	public String getVcvIdentFromCustomer(String email) {
		System.out.println("Getting VCV Ident for a Customer");
		PreparedStatement ps = null;
		String vcv_ident = null;
		try {
			ps = conn.prepareStatement(SQL_VCV_FROM_CSNA);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) vcv_ident = rs.getString(1);
			else vcv_ident = "null";
			if(rs.next()) System.out.println("Result Set has more than 1 record for VCV");
			rs.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try { if(ps!=null) ps.close(); } catch(SQLException se) {se.printStackTrace();}
		}
		return vcv_ident;
	}
	
	public void prepareInsertForASU(String sstIdent, String cvaIdent, String agrNum, String agrStart, String agrEnd) {
		try {
			if ("null".equals(agrStart)) return;
			String[] agrStarts = agrStart.split(" ");
			StringBuffer buffer = new StringBuffer(SQL_INSERT_TO_ASU_CSNA);
			buffer.append(sstIdent).append(", 1, ").append(cvaIdent).append(", '").append(agrNum).append("', '").append(agrStarts[0]).append("', ");
			if ("null".equals(agrEnd)) { 
				buffer.append("NULL");
			} else {
				String[] agrEnds = agrEnd.split(" ");
				buffer.append("'" + agrEnds[0] + "'");
			}
			buffer.append(", 0, NULL, 'AMS', 'AMS', 'VZT_DUMP', 1);");
			writer1.write(buffer.toString());
			writer1.write("\n");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}	
	}
	
}


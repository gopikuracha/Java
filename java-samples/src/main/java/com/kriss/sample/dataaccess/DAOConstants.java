package com.kriss.sample.dataaccess;

public class DAOConstants {

	public static final String SQL_ACC_FROM_DUMP = "select distinct dump.ACCOUNT_NUM, dump.VIN, dump.AGREEMENT_NUMBER, dump.SUBSCRIPTION_NAME, "
			+ "dump.AGREEMENT_START_DATE, dump.AGREEMENT_END_DATE, dump.EMAIL_ADDR from test_csna.vzt_dump dump "
			+ "where dump.AGREEMENT_END_DATE is null or dump.AGREEMENT_END_DATE > curtime() limit 47140,60000";

	public static final String SQL_CVA_FROM_CSNA = "select cva.ACCT_NUM, cva.CVA_IDENT, cva.VCV_IDENT"
			+ ", ast.ACCT_STAT_TYP"
			+ ", asu.ASU_IDENT, asu.SUBS_NUM"
			+ ", sst.AGRMNT_STAT_TYP"
			+ ", spr.SPR_IDENT"
			+ ", asu.SUBS_START_DTE, asu.SUBS_END_DTE "
			+ "from csna.cva_cust_veh_acct cva "
			+ "left join csna.asu_acct_subs asu on asu.CVA_IDENT = cva.CVA_IDENT "
			+ "left join csna.ast_acct_stat_type ast on ast.AST_IDENT = cva.AST_IDENT "
			+ "left join csna.spr_subs_product spr on spr.SPR_IDENT = asu.SPR_IDENT "
			+ "left join csna.sst_subs_stat_type sst on sst.SST_IDENT = asu.SST_IDENT "
			+ "where cva.ACCT_NUM = ? and spr.SUBS_PROD_NME = ? and sst.SST_IDENT = 1";

	public static final String SQL_VCV_FROM_CSNA = "select vcv.VCV_IDENT from csna.cus_customer cus "
			+ "inner join csna.vcv_cust_veh vcv on vcv.CUS_IDENT = cus.CUS_IDENT "
			+ "inner join csna.veh_vehicle veh on veh.VEH_IDENT = vcv.VEH_IDENT "
			+ "where vcv.VCV_IDENT = vcv.PRIMARY_VCV_IDENT "
			+ "and cus.PERNL_EMAIL_ADDR = ? and veh.VIN = ?";

	public static final String SQL_CVA = "select cva.CVA_IDENT, cva.VCV_IDENT, ast.ACCT_STAT_TYP from csna.cva_cust_veh_acct cva "
			+ "inner join csna.ast_acct_stat_type ast on cva.AST_IDENT = ast.AST_IDENT where cva.ACCT_NUM = ?";

	public static final String SQL_SPR = "select spr.SPR_IDENT from csna.spr_subs_product spr where spr.SUBS_PROD_NME = ?";

	public static final String SQL_INSERT_TO_ASU_CSNA = "INSERT INTO `asu_acct_subs` "
			+ "(`SPR_IDENT`, `SST_IDENT`, `CVA_IDENT`, `SUBS_NUM`, `SUBS_START_DTE`, `SUBS_END_DTE`, `SUBS_MNTH_CNT`, `SUBS_PAIRED_DEVICES_CNT`, "
			+ "`ASU_ADD_BY_USER`, `ASU_UPD_BY_USER`, `ASU_REC_SRC_CDE`, `ACT_IDENT`) VALUES (";
	
	public static final String CSNA_SAMPLE_SQL = "select cva.CVA_IDENT, ast.ACCT_STAT_TYP, cva.VCV_IDENT, cva.ACCT_NUM, asu.ASU_IDENT, spr.SUBS_PROD_NME, sst.AGRMNT_STAT_TYP, "
			+ "asu.SUBS_NUM, asu.SUBS_START_DTE, asu.SUBS_END_DTE, asu.SUBS_MNTH_CNT, act.ACCT_CLSUR_TYP from csna.cva_cust_veh_acct cva "
			+ "inner join csna.asu_acct_subs asu on asu.CVA_IDENT = cva.CVA_IDENT "
			+ "inner join csna.spr_subs_product spr on spr.SPR_IDENT = asu.SPR_IDENT "
			+ "inner join csna.sst_subs_stat_type sst on sst.SST_IDENT = asu.SST_IDENT "
			+ "inner join csna.ast_acct_stat_type ast on cva.AST_IDENT = ast.AST_IDENT "
			+ "inner join csna.act_acct_close_type act on act.ACT_IDENT = asu.ACT_IDENT "
			+ "where cva.ACCT_NUM = (select dump.ACCOUNT_NUM from test_csna.vzt_dump dump where dump.AGREEMENT_END_DATE is null or dump.AGREEMENT_END_DATE > curtime() limit %REPLACE%,1) "
			+ "and asu.SUBS_NUM = (select dump.AGREEMENT_NUMBER from test_csna.vzt_dump dump where dump.AGREEMENT_END_DATE is null or dump.AGREEMENT_END_DATE > curtime() limit %REPLACE%,1)";
}

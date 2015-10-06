/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;
import java.sql.*;
import oracle.jdbc.driver.*; 

public class JDBC_example
{
     public static void main(String[] args)
     {
        String url = null;
        String login = null;
        String password = null;
        if (args.length >= 2) 
        { 
            url = "jdbc:oracle:thin:@rtl-mom-db-2d.konzum.hr:1521:mom2d"; 
            login = args[0];
            password = args[1];
        } else {
            System.out.println("USAGE: JDBC_example login password");
            return;
        }
        try {
            DriverManager.registerDriver ( new oracle.jdbc.driver.OracleDriver());
        }
        catch (Exception e) 
        { return; }
        try {
            Connection cn =  DriverManager.getConnection (url,login,password);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT * from KWS.CUSTOMER_MASTER where rownum<11");
        while (rs.next()) {
            System.out.println("NO_VAT_REASON=" + rs.getString(68) + " " +
            "VAT_NUMBER=" + rs.getString(66)  + " " +
            "VAT_LIABLE=" + rs.getString(67) + " " +
            "CUSTOMER_PLUSM_IND=" + rs.getString(69) + " " +
            "EDI_INVOICE_BY_MAIL_IND=" + rs.getString(70) + " " + 
            "CUSTOMER=" + rs.getString(1) + " " +
            "CUSTOMER_NAME=" + rs.getString(2) + " " +
            "CUSTOMER_NAME2=" + rs.getString(3) + " " +
            "CUSTOMER_NAME3=" + rs.getString(4) + " " +
            "CUSTOMER_NAME4=" + rs.getString(5) + " " +
            "CUSTOMER_STATUS=" + rs.getString(6) + " " +
            "PREV_STATUS=" + rs.getString(7) + " " +
            "CUSTOMER_TYPE=" + rs.getString(8) + " " +
            "CHANNEL=" + rs.getString(9) + " " +
            "SUBCHANNEL=" + rs.getString(10) + " " +
            "SUBCHANNEL_TYPE=" + rs.getString(11) + " " +
            "CUSTOMER_GROUP=" + rs.getString(12) + " " +
            "CUSTOMER_HIERARCHY=" + rs.getString(13) + " " +
            "CUSTOMER_PARENT_ID=" + rs.getString(14) + " " +
            "BILL_TO_CUSTOMER=" + rs.getString(15) + " " +
            "LANG=" + rs.getString(16) + " " +
            "RECYCLING_IND=" + rs.getString(17) + " " +
            "MAIN_LOCATION=" + rs.getString(18) + " " +
            "MULTI_LOC_IND=" + rs.getString(19) + " " +
            "ACCOUNT_GROUP=" + rs.getString(20) + " " +
            "TAX_NUMBER=" + rs.getString(21) + " " +
            "CUST_ACCOUNT_NUM=" + rs.getString(22) + " " +
            "OIB=" + rs.getString(23) + " " +
            "CURRENCY_CODE=" + rs.getString(24) + " " +
            "ORDER_CURRENCY=" + rs.getString(25) + " " +
            "AGR_GROUP_IND=" + rs.getString(26) + " " +
            "KAM_ID=" + rs.getString(27) + " " +
            "SA_ID=" + rs.getString(28) + " " +
            "CONS_INV_IND=" + rs.getString(29) + " " +
            "CONS_TIMEFRAME=" + rs.getString(30) + " " +
            "INVOICE_LAYOUT=" + rs.getString(31) + " " +
            "SHIPPMENT_LAYOUT=" + rs.getString(32) + " " +
            "PROOF_OF_DEL_IND=" + rs.getString(33) + " " +
            "ASSORT_ITEM_IND=" + rs.getString(34) + " " +
            "RET_PACKAGE_IND=" + rs.getString(35) + " " +
            "ASSORT_FPL_IND=" + rs.getString(36) + " " +
            "RET_ORDER_IND=" + rs.getString(37) + " " +
            "POD_PRICE_IND=" + rs.getString(38) + " " +
            "USAGE_ITEM_IND=" + rs.getString(39) + " " +
            "EDI_ORDER_IND=" + rs.getString(40) + " " +
            "EDI_INVOICE_IND=" + rs.getString(41) + " " +
            "EDI_RETURN_IND=" + rs.getString(42) + " " +
            "EDI_PRICE_LIST_IND=" + rs.getString(43) + " " +
            "EDI_DISPATCH_ADV_IND=" + rs.getString(44) + " " +
            "EDI_ASN_IND=" + rs.getString(45) + " " +
            "EDI_ORDER_CONF_IND=" + rs.getString(46) + " " +
            "EDI_PROOF_OF_DEL_IND=" + rs.getString(47) + " " +
            "INVOICE_VAT_IND=" + rs.getString(48) + " " +
            "X=" + rs.getString(49) + " " +
            "Y=" + rs.getString(50) + " " +
            "INSURANCE_INSTITUTION=" + rs.getString(51) + " " +
            "COMMENTS=" + rs.getString(52) + " " +
            "AUX_COMPANY_CODE=" + rs.getString(53) + " " +
            "AUX_GL_ACCOUNT=" + rs.getString(54) + " " +
            "AUX_SHORT_NAME=" + rs.getString(55) + " " +
            "CRE_DATE=" + rs.getString(56) + " " +
            "CRE_ID=" + rs.getString(57) + " " +
            "APPROVAL_DATE=" + rs.getString(58) + " " +
            "APPROVAL_ID=" + rs.getString(59) + " " +
            "INACTIVE_DATE=" + rs.getString(60) + " " +
            "INACTIVE_ID=" + rs.getString(61) + " " +
            "MOD_DATE=" + rs.getString(62) + " " +
            "MOD_ID=" + rs.getString(63) + " " +
            "BLOCK_DATE=" + rs.getString(64) + " " +
            "BLOCK_ID=" + rs.getString(65));
        }

        st.close();
        cn.close();
    }
    catch (Exception e) { return; }
    finally { System.out.println("Finished!"); }
    }
}

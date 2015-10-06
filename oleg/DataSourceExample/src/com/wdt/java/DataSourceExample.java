/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
/**
 *
 * @author user
 */
public class DataSourceExample {
    public static DataSource getOracleDataSource(){
        Properties props = new Properties();
        FileInputStream fis = null;
        OracleDataSource oracleDS = null;
        try {
            fis = new FileInputStream("db.properties");
            props.load(fis);
            oracleDS = new OracleDataSource();
            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }
    private static void executeDataSource() {
        DataSource ds = DataSourceExample.getOracleDataSource();
         
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * from KWS.CUSTOMER_MASTER where rownum<11");
            while(rs.next()){
                System.out.println("CUSTOMER=" + rs.getString(1) + " " +
                                    "CUSTOMER_NAME=" + rs.getString(2) + " " +
                                    "CUSTOMER_NAME2=" + rs.getString(3) + " " +
                                    "CUSTOMER_NAME3=" + rs.getString(4) + " " +
                                    "CUSTOMER_NAME4=" + rs.getString(5) + " " +
                                    "CUSTOMER_STATUS=" + rs.getString(6) + " " +
                                    "PREV_STATUS=" + rs.getString(7) + " " +
                                    "CUSTOMER_TYPE=" + rs.getString(8) + " " +
                                    "CHANNEL=" + rs.getString(9) + " " +
                                    "SUBCHANNEL=" + rs.getString(10));
                                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
                try {
                    if(rs != null) rs.close();
                    if(stmt != null) stmt.close();
                    if(con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        executeDataSource();
    }
    
}

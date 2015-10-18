/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java.cachedrowset;

import com.sun.rowset.CachedRowSetImpl;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import javax.sql.RowSetMetaData;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Oleg
 */
public class CachedRowSetExample {
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
      public static void main(String[] args) throws Exception {
        Connection connection = getOracleDataSource().getConnection();
        System.out.println("Connection Done");
        Statement statement = connection.createStatement();
        CachedRowSetImpl crs = new CachedRowSetImpl();
        RowSetMetaData rsmd = (RowSetMetaData)crs.getMetaData();
        int count = rsmd.getColumnCount();
        int type = rsmd.getColumnType(1);
        crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        String queryString = "SELECT * from KWS.CUSTOMER_MASTER where rownum<11t";
        crs.setCommand(queryString);
        crs.execute();
        System.out.println("Number of records in results set: "+count);
        System.out.println("Type of first column: "+type);
        while (crs.next()) {
            while(crs.next()){
                System.out.println("CUSTOMER=" + crs.getString(1) + " " +
                                    "CUSTOMER_NAME=" + crs.getString(2) + " " +
                                    "CUSTOMER_NAME2=" + crs.getString(3) + " " +
                                    "CUSTOMER_NAME3=" + crs.getString(4) + " " +
                                    "CUSTOMER_NAME4=" + crs.getString(5) + " " +
                                    "CUSTOMER_STATUS=" + crs.getString(6) + " " +
                                    "PREV_STATUS=" + crs.getString(7) + " " +
                                    "CUSTOMER_TYPE=" + crs.getString(8) + " " +
                                    "CHANNEL=" + crs.getString(9) + " " +
                                    "SUBCHANNEL=" + crs.getString(10));
                                    }
        }
        connection.close();
}
}

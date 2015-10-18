/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java.JdbcRowSet;

import com.sun.rowset.JdbcRowSetImpl;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import javax.sql.RowSetListener;
import javax.sql.rowset.JdbcRowSet;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Oleg
 */
public class JdbcRowSetExample {
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
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }
      public static void main(String[] args) throws Exception {
        Connection connection = getOracleDataSource().getConnection();
        System.out.println("Connection Done");
        Statement statement = connection.createStatement();
        JdbcRowSet jdbcRowSet;
        jdbcRowSet = new JdbcRowSetImpl(connection);
        jdbcRowSet.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        String queryString = "SELECT * from KWS.CUSTOMER_MASTER where rownum<11t";
        jdbcRowSet.setCommand(queryString);
        jdbcRowSet.execute();

        while (jdbcRowSet.next()) {
            while(jdbcRowSet.next()){
                System.out.println("CUSTOMER=" + jdbcRowSet.getString(1) + " " +
                                    "CUSTOMER_NAME=" + jdbcRowSet.getString(2) + " " +
                                    "CUSTOMER_NAME2=" + jdbcRowSet.getString(3) + " " +
                                    "CUSTOMER_NAME3=" + jdbcRowSet.getString(4) + " " +
                                    "CUSTOMER_NAME4=" + jdbcRowSet.getString(5) + " " +
                                    "CUSTOMER_STATUS=" + jdbcRowSet.getString(6) + " " +
                                    "PREV_STATUS=" + jdbcRowSet.getString(7) + " " +
                                    "CUSTOMER_TYPE=" + jdbcRowSet.getString(8) + " " +
                                    "CHANNEL=" + jdbcRowSet.getString(9) + " " +
                                    "SUBCHANNEL=" + jdbcRowSet.getString(10));
                                    }
        }
        connection.close();
}
}

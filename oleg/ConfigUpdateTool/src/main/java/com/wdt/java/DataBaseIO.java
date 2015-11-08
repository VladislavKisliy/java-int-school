/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import com.sun.rowset.JdbcRowSetImpl;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Oleg
 */
public class DataBaseIO {
    private DataSource ds =null;

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
    public Properties readPropertiesFromDB(String tableName) throws Exception{
        Properties prop = new Properties();
        Connection connection = getDs().getConnection();
        System.out.println("Connection Done");
        Statement statement = connection.createStatement();
        JdbcRowSet jdbcRowSet;
        jdbcRowSet = new JdbcRowSetImpl(connection);
        jdbcRowSet.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        String queryString = "SELECT * from "+ tableName;
        jdbcRowSet.setCommand(queryString);
        jdbcRowSet.execute();

        while (jdbcRowSet.next()) {
            while(jdbcRowSet.next()){
                prop.put(jdbcRowSet.getString(1), jdbcRowSet.getString(2));
                }
        }
        connection.close();
        return prop;
    }
    public DataSource getDSParameters(String parameterFile){
        Properties props = new Properties();
        FileInputStream fis = null;
        OracleDataSource oracleDS = null;
        try {
            fis = new FileInputStream(parameterFile);
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
    public void writePropertiesToDB(Properties prop, String tableName) throws Exception{
        Connection connection = getDs().getConnection();
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
                System.out.println("CUSTOMER=" + jdbcRowSet.getString(1));
                }
        }
        connection.close();
    }
    
}

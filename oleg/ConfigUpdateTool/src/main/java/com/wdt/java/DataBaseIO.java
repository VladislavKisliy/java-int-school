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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
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
        Statement statement = connection.createStatement();
        JdbcRowSet jdbcRowSet = new JdbcRowSetImpl(connection);
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
    public Properties getDBproperties(String parameterFile){
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(parameterFile);
            props.load(fis);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        return props;
    }
    //props.getProperty("ORACLE_DB_URL")
    //props.getProperty("ORACLE_DB_USERNAME")
    //props.getProperty("ORACLE_DB_PASSWORD")
    public DataSource getDS(String [] parameters){
 
        OracleDataSource oracleDS = null;
        try {
            oracleDS = new OracleDataSource();
            oracleDS.setURL(parameters[0]);
            oracleDS.setUser(parameters[1]);
            oracleDS.setPassword(parameters[2]);
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }
    public void writePropertiesToDB(Properties prop, String tableName) throws Exception{
        Connection connection = getDs().getConnection();
        String sql = "UPDATE "//+connection.getSchema()+"." //commented as user connected could differ from table owner
                +tableName+" SET VALUE=? WHERE PROPERTIE=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        Enumeration<?> e = prop.propertyNames();
	while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            statement.setString(1, value);
            statement.setString(2, key);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated <= 0) {
                System.err.println("Such option doesn't exsist: "+key);
            }
	}
        connection.close();
    }
    
}

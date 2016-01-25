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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Oleg
 */
public class DataBaseIO {
    private static final Logger LOG = Logger.getLogger(ParsParams.class.getName());
    private DataSource ds =null;
    public DataSource getDs() {
        return ds;
    }
    public void setDs(DataSource ds) {
        this.ds = ds;
    }
    public Properties readPropertiesFromDB(String tableOwner, String tableName) {
        //Java exceptions - read
        Properties prop = new Properties();
        try(Connection connection = getDs().getConnection();
            Statement statement = connection.createStatement(); 
            JdbcRowSet jdbcRowSet = new JdbcRowSetImpl(connection);){
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
        }catch (SQLException e){
            LOG.log(Level.SEVERE , e.getMessage());
        }
        return prop;
    }
    public Properties getDBproperties(String parameterFile){
        Properties props = new Properties();
        try (FileInputStream fis =new FileInputStream(parameterFile); ) {
            props.load(fis);
        }catch(IOException e){
            LOG.log(Level.SEVERE , e.getMessage());
        }
        return props;
    }
    public DataSource getDS(Properties props){
 
        OracleDataSource oracleDS = null;
        try  {
            oracleDS = new OracleDataSource();
            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
        } catch ( SQLException e) {
            LOG.log(Level.SEVERE , e.getMessage());
        }
        return oracleDS;
    }
//    public void updatePropertiesInDB(Properties prop, String tableOwner, String tableName) {
//        Connection connection;
//        try {
//        connection = getDs().getConnection();
//        String sql = "UPDATE "+tableOwner+"."//+connection.getSchema()+"." //commented as user connected could differ from table owner
//                +tableName+" SET PROPERTIE_VALUE=? WHERE PROPERTIE=?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        Enumeration<?> e = prop.propertyNames();
//	while (e.hasMoreElements()) {
//            String key = (String) e.nextElement();
//            String value = prop.getProperty(key);
//            statement.setString(1, value);
//            statement.setString(2, key);
//            int rowsUpdated = statement.executeUpdate();
//            if (rowsUpdated <= 0) {
//                System.err.println("Such option doesn't exsist: "+key);
//            }
//	}
//        connection.close(); //!!!!!move after catch
//        } catch (SQLException ex) {
//            Logger.getLogger(DataBaseIO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void insertPropertiesInDB(Properties prop, String tableOwner, String tableName){
        String sql = "INSERT INTO "+tableOwner+"."+tableName+" (PROPERTIE_VALUE,PROPERTIE) VALUES(?,?)";
        try (Connection connection = getDs().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
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
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }        
    }
    public void deletePropertiesFromDB(String tableOwner, String tableName){
        String sql = "DELETE FROM "+tableOwner+"."+tableName;
        try (Connection connection  = getDs().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }        
    }
}

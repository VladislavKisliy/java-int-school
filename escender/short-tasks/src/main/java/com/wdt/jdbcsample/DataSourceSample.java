/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.jdbcsample;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.JdbcRowSet;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Dreamesc
 */
public class DataSourceSample {

    public DataSourceSample() {



        Connection connection = null;
        OracleDataSource ods = null;
        try {

            System.out.println("~~~~~~~~~~~~~~DataSource~~~~~~~~~~~~~~~~~~~");
            ods = new OracleDataSource();
            ods.setUser("kws");
            ods.setPassword("drwkws5t");
            ods.setDriverType("thin");
            ods.setDatabaseName("mom5t");
            ods.setServerName("konzum2.weigandt-consulting.com");
            ods.setPortNumber(1521);
            connection = ods.getConnection();

            Statement statement = connection.createStatement();        
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM kws.customer_master t where rownum < 1000 order by 1");
            
            int i = 0;

            while (resultSet.next() && i <= 5) {
                //System.out.print(resultSet.getLong("RN") + " ");
                System.out.print(resultSet.getLong("CUSTOMER") + " ");
                System.out.print(resultSet.getDate("MOD_DATE") + " ");
                System.out.println(resultSet.getString("CUSTOMER_NAME"));
                i++;
            }
            
            resultSet.close();
            statement.close();
            
            System.out.println("~~~~~~~~~~~~~~JdbcRowSet~~~~~~~~~~~~~~~~~~~");
            
            OraclePreparedStatement oraclePreparedStatement = (OraclePreparedStatement) connection.prepareStatement
                    ("SELECT * FROM kws.customer_master t where rownum < ? order by 1", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            oraclePreparedStatement.setInt(1, 10000);            
            JdbcRowSet jdbcRowSet = new JdbcRowSetImpl(oraclePreparedStatement.executeQuery());
            jdbcRowSet.last();
            i=0; 
           while (jdbcRowSet.previous() && i <= 5) {
                System.out.print(jdbcRowSet.getLong("CUSTOMER") + " ");
                System.out.print(jdbcRowSet.getDate("MOD_DATE") + " ");
                System.out.println(jdbcRowSet.getString("CUSTOMER_NAME"));
                i++;
            }
            
            resultSet.close();
            oraclePreparedStatement.close();

        } catch (SQLException ex) {
            System.out.println("Connection failed");
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataSourceSample dataSourceSample = new DataSourceSample();
    }
}

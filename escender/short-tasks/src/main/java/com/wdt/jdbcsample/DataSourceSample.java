/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.jdbcsample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    "SELECT rownum RN, t.* FROM kws.customer_master t");

            int i = 0;

            while (resultSet.next() && i <= 10) {
                System.out.print(resultSet.getLong("RN") + " ");
                System.out.print(resultSet.getLong("CUSTOMER") + " ");
                System.out.print(resultSet.getDate("MOD_DATE") + " ");
                System.out.println(resultSet.getString("CUSTOMER_NAME"));
                i++;
            }

            resultSet.close();
            statement.close();

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

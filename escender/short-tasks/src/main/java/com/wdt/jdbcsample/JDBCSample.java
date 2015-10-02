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

/**
 *
 * @author Dreamesc
 */
public class JDBCSample {
    public JDBCSample() throws ClassNotFoundException {

        // proporties
        String url = "jdbc:oracle:thin:@konzum2.weigandt-consulting.com:1521:mom5t";
        String name = "kws";
        String password = "drwkws5t";

        Connection connection = null;

        try {
            // Driver connect
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // get connection
            connection = DriverManager.getConnection(url, name, password);
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM kws.customer_master");

            int i = 0;

            while (resultSet.next() && i <= 10) {
                System.out.print(resultSet.getLong("CUSTOMER") + " ");
                System.out.print(resultSet.getDate("MOD_DATE") + " ");
                System.out.println(resultSet.getString("CUSTOMER_NAME"));
                i++;
            }      
            
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(JDBCSample.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCSample.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        JDBCSample sampleJDBCConnect = new JDBCSample();
    }
}

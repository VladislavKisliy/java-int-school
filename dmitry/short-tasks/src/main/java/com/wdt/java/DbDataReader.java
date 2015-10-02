package com.wdt.java;

import java.sql.Connection;
//import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.JdbcRowSet;

import com.sun.rowset.JdbcRowSetImpl;

public class DbDataReader {
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	public static void main(String args[]) throws SQLException {
		String url = "jdbc:oracle:thin:@konzum2.weigandt-consulting.com:1521:mom5t"; 
		
		Properties props = new Properties();
        props.setProperty("user", "kws");
        props.setProperty("password", "drwkws5t");
        
        //Driver myDriver = new oracle.jdbc.driver.OracleDriver();
		//DriverManager.registerDriver( myDriver );
        
        Connection conn = DriverManager.getConnection(url,props);
        System.out.println("connected to database...");
        
        String sql ="select customer, customer_name, cre_date from kws.customer_master where rownum <= 10";
        PreparedStatement preStatement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);        
        ResultSet result = preStatement.executeQuery();

        while(result.next()){
            System.out.println(result.getLong(1)+" | "+padRight(result.getString("customer_name"),35)+" | "+result.getDate(3));
        }
        
        JdbcRowSet jdbcRs = new JdbcRowSetImpl(result);
        jdbcRs.last();
        System.out.println("Row_number for the last row:"+jdbcRs.getRow());
        
        System.out.println("done");
        
        jdbcRs.close();
        conn.close();
	}	

}
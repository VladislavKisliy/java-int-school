package com.wdt.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.RowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.Predicate;

import oracle.jdbc.pool.OracleDataSource;

import com.sun.rowset.FilteredRowSetImpl;
import com.sun.rowset.JdbcRowSetImpl;

public class DbDataReader {
	
	private static class PredicateImpl implements Predicate //only rows with odd values
	{
	  private int columnIndex;
	  
	  public PredicateImpl(int columnIndex) {
	    this.columnIndex = columnIndex;
	  }
	  
	  public boolean evaluate(RowSet rs)
	  {
	    int columnValue = 0;
		try {
			columnValue = rs.getInt(columnIndex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if (columnValue%2 == 0)
          return false;
	    return true;
	  }

		@Override
		public boolean evaluate(Object value, int column) throws SQLException {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean evaluate(Object value, String columnName)
				throws SQLException {
			// TODO Auto-generated method stub
			return true;
		}
	}
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	public static void main(String args[]) throws SQLException {
		System.out.println("starting jdbc test using driver manager...");
		
		String url = "jdbc:oracle:thin:@konzum2.weigandt-consulting.com:1521:mom5t"; 
		
		Properties props = new Properties();
        props.setProperty("user", "kws");
        props.setProperty("password", "drwkws5t");
        
        //Driver myDriver = new oracle.jdbc.driver.OracleDriver();
		//DriverManager.registerDriver( myDriver );
        
        Connection conn = DriverManager.getConnection(url,props);
        System.out.println("connected to database...");
        System.out.println("testing statement...");
        String sql ="select customer, customer_name, cre_date from kws.customer_master where rownum <= 10";
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);        
        ResultSet result = statement.executeQuery(sql);

        while(result.next()){
            System.out.println(result.getLong(1)+" | "+padRight(result.getString("customer_name"),35)+" | "+result.getDate(3));
        }
        
        System.out.println("testing JdbcRowSet...");
        JdbcRowSet jdbcRs = new JdbcRowSetImpl(result);
        jdbcRs.last();
        System.out.println("Rowset test: row_number for the last row:"+jdbcRs.getRow());
        
        System.out.println("done...");
        
        jdbcRs.close();
        conn.close();
        
        System.out.println("starting jdbc test using datasource...");
        OracleDataSource ds = new OracleDataSource();
		ds.setDriverType("thin");
		ds.setServerName("konzum2.weigandt-consulting.com");
		ds.setPortNumber(1521);
		ds.setDatabaseName("mom5t"); // Oracle SID
		ds.setUser("kws");
		ds.setPassword("drwkws5t");		
		conn = ds.getConnection();
		System.out.println("connected to database...");
		
		System.out.println("testing prestatement with parameter...");
		sql ="select * from (select rownum rn, customer, customer_name, cre_date from kws.customer_master)  where rownum <= ?";
		PreparedStatement preStatement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);   
		preStatement.setInt(1, 50);
        result = preStatement.executeQuery();
        /*while(result.next()){
            System.out.println(result.getLong(1)+" | "+padRight(result.getString("customer_name"),35)+" | "+result.getDate(4));
        }*/
        result.next();
        System.out.println(result.getLong(1) + result.getLong(2)+" | "+padRight(result.getString("customer_name"),35)+" | "+result.getDate(4));
        
        System.out.println("testing FilteredRowSet...");
		FilteredRowSet frs = new FilteredRowSetImpl();
		frs.populate(result);
		PredicateImpl predicate = new PredicateImpl (1);
		frs.setFilter(predicate);
        while(frs.next()){
            System.out.println(frs.getLong(1)+" | "+frs.getLong(2)+" | "+padRight(frs.getString("customer_name"),35)+" | "+frs.getDate(4));
        }		
		
        System.out.println("done...");
        frs.close();
		conn.close();
	}	

}

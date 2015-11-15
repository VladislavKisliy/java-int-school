/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Oleg
 */
public class ControlTool {
    public static void main(String[] argv) throws Exception{
        Properties fromDB=new Properties();
        ParsParams pp = new ParsParams();
        DataBaseIO dbIO=new DataBaseIO();

//        String file = "C:/test/db.properties.txt";

        Properties prop = pp.getPropertiesFromFile(argv [0]);
        Enumeration<?> e = prop.propertyNames();
	while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            System.out.println("key: "+key+" value: "+value);
        }
//        pp.writePropertiesToFile(argv [0]+"_out", prop);
        Properties dbSettings=dbIO.getDBproperties("db.properties");
        DataSource ods = dbIO.getDS(dbSettings);
        dbIO.setDs(ods);
        dbIO.insertPropertiesInDB(prop,"OTOPORKOV", "TEST_PROPERTIES");
        fromDB=dbIO.readPropertiesFromDB("OTOPORKOV", "TEST_PROPERTIES");
        pp.writePropertiesToFile(argv [0]+".properties", prop);
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Oleg
 */
public class ControlTool {
    //usage example:
    //java -jar .\target\ConfigUpdateTool-1.0-SNAPSHOT.jar -propertieFile C:\test\db.properties.txt -propTableName TEST_PROPERTIES -propTableOwner OTOPORKOV -db2file -conffile .\db.properties
    public static void main(String[] args) throws Exception {
        Properties dbSettings = new Properties();
        Options options = OptionControlClass.OptionControlClass();
//        String file ;//= "C:/test/db.properties.txt";
        ParsParams pp = new ParsParams();
        Properties prop = new Properties();// = pp.getPropertiesFromFile(file);
        Properties fromDB = new Properties();
        String propFile = new String();
        String tableName = new String();
        String tableOwner = new String();
        DataBaseIO dbIO=new DataBaseIO();
        CommandLine line = null;
	try {          
            CommandLineParser parser = new GnuParser();            
            line = parser.parse( options, args );
            if (line.hasOption("?")) {
                usage(options);
                return;
            }
            //
            //check if direction defined
            if (    ((! line.hasOption("db2file"))&&(! line.hasOption("file2db")))
                    || (! line.hasOption("propTableName"))
                    || (! line.hasOption("propTableOwner"))
                    || (! line.hasOption("propertieFile"))                    
                    ){
                error(options, "propertieFile, propTableName, propTableOwner and direction of propertie load [db2file|file2db] are mandatory to define.");
                return;
            }
            tableName=line.getOptionValue("propTableName");
            tableOwner=line.getOptionValue("propTableOwner");
            propFile=line.getOptionValue("propertieFile");
            prop = pp.getPropertiesFromFile(propFile);
            //get properties from input propertie file
            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                System.out.println("key: "+key+" value: "+value);
            }
            //you can define connection properties separate or as conffile
            if (! line.hasOption("conffile")){
                if( ! line.hasOption("url")|| ! line.hasOption("passwd")|| ! line.hasOption("user")){
                    error(options, "Please define either \"conffile\" or connection properties (url, passwd, user)!");
                    return;
                }else{
                    dbSettings.setProperty("ORACLE_DB_URL", line.getOptionValue("url"));
                    dbSettings.setProperty("ORACLE_DB_USERNAME", line.getOptionValue("user"));
                    dbSettings.setProperty("ORACLE_DB_PASSWORD", line.getOptionValue("passwd"));
                }
            }else{
                dbSettings=dbIO.getDBproperties(line.getOptionValue("conffile"));
            }            
        }catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            usage(options);
        }
        
        DataSource ods = dbIO.getDS(dbSettings);
        dbIO.setDs(ods);
        
        if (line.hasOption("db2file")) {
            fromDB=dbIO.readPropertiesFromDB(tableOwner, tableName);
            pp.writePropertiesToFile(propFile+".properties_new", prop);
        } else if (line.hasOption("file2db")){
            dbIO.insertPropertiesInDB(prop,tableOwner, tableName);
        }        
}
      private static void error(Options options, String msg) {
        System.err.println(msg);
        usage(options);
        System.exit(1);
    }

      private static void usage(Options options) {
       // automatically generate the help statement
       HelpFormatter formatter = new HelpFormatter();
       formatter.printHelp( "ControlTool", options );
    }        
    }

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
    //java -jar .\target\ConfigUpdateTool-1.0-SNAPSHOT.jar -propertieFile C:\test\db.properties.txt -propTableName TEST_PROPERTIES -propTableOwner OTOPORKOV -file2db -conffile .\db.properties
    //java -jar .\target\ConfigUpdateTool-1.0-SNAPSHOT.jar -propertieFile C:\test\db.properties_1.txt -propTableName TEST_PROPERTIES -propTableOwner OTOPORKOV -db2file -conffile .\db.properties -url jdbc:oracle:thin:@konzum2.weigandt-consulting.com:1521:mom5t -passwd password -user OTOPORKOV
    //db.properties example (pass is fake):
    //ORACLE_DB_URL=jdbc:oracle:thin:@konzum2.weigandt-consulting.com:1521:mom5t
    //ORACLE_DB_USERNAME=OTOPORKOV
    //ORACLE_DB_PASSWORD=password
    
    //checked/unchecked exception -hierarhy of exceptions
    //rewrite to process sql exception 
    public static void main(String[] args) {
        Options options = OptionControlClass.OptionControlClass();
        CommandLineParser parser = new GnuParser(); 
        CommandLine line =null;
        try{
            line = parser.parse( options, args );
        }catch(ParseException e){
            
        }
        processArgsLogic(line, options);
 
}
    private static void processArgsLogic(CommandLine line, Options options){
        Properties dbSettings = new Properties();
        ParsParams pp = new ParsParams();
        Properties mainProperties = new Properties();
        String propFile = new String();
        String tableName = new String();
        String tableOwner = new String();
        DataBaseIO dbIO=new DataBaseIO();
	try{          
            if (line.hasOption("?")) {
                usage(options);
                return;
            }
            //check if direction defined
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
            DataSource ods = dbIO.getDS(dbSettings);
            dbIO.setDs(ods);
            if ( (! line.hasOption("propTableName"))
                    || (! line.hasOption("propTableOwner"))
                    || (! line.hasOption("propertieFile"))                    
                    ){
                error(options, "propertieFile, propTableName, propTableOwner are mandatory to define.");
                return;
            } else{
                tableName=line.getOptionValue("propTableName");
                tableOwner=line.getOptionValue("propTableOwner");
                propFile=line.getOptionValue("propertieFile");
            }
            if (line.hasOption("db2file")) {
                mainProperties=dbIO.readPropertiesFromDB(tableOwner, tableName);
                pp.writePropertiesToFile(propFile, mainProperties);
            } else if (line.hasOption("file2db")){
                mainProperties=pp.getPropertiesFromFile(propFile);
                dbIO.deletePropertiesFromDB(tableOwner, tableName);
                dbIO.insertPropertiesInDB(mainProperties,tableOwner, tableName);
            }  else{
                error(options, "You should define db2file or file2db");
                return;
            }              
        }catch(Exception exp ) {
            //something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            usage(options);
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

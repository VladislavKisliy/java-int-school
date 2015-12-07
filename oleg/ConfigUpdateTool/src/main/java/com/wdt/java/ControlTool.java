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
    
    public static void main(String[] args) throws Exception{
//        Properties fromDB=new Properties();
//        ParsParams pp = new ParsParams();
//        DataBaseIO dbIO=new DataBaseIO();

//        String file = "C:/test/db.properties.txt";

//        Properties prop = pp.getPropertiesFromFile(argv [0]);
//        Enumeration<?> e = prop.propertyNames();
//	while (e.hasMoreElements()) {
//            String key = (String) e.nextElement();
//            String value = prop.getProperty(key);
//            System.out.println("key: "+key+" value: "+value);
//        }
//        Properties dbSettings=dbIO.getDBproperties("db.properties");
//        DataSource ods = dbIO.getDS(dbSettings);
//        dbIO.setDs(ods);
//        dbIO.insertPropertiesInDB(prop,"OTOPORKOV", "TEST_PROPERTIES");
//        fromDB=dbIO.readPropertiesFromDB("OTOPORKOV", "TEST_PROPERTIES");
//        pp.writePropertiesToFile(argv [0]+".properties", prop);
        Options options = getOptions();
        
        CommandLineParser parser = new GnuParser();
	try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );
            
	    if (line.hasOption("?")) {
                usage(options);
                return;
            }
        }catch( ParseException exp ) {
            // oops, something went wrong
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
       formatter.printHelp( "ivy", options );
    }        

    private static Options getOptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

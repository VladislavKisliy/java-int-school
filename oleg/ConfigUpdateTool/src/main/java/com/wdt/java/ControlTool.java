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
    
    public static void main(String[] args) throws Exception {
        Properties dbSettings = new Properties();
//        Options options = OptionControlClass.OptionControlClass();
//	try {

            
//            CommandLineParser parser = new GnuParser();
            DataBaseIO dbIO=new DataBaseIO();
//            CommandLine line = parser.parse( options, args );
//            if (line.hasOption("?")) {
//                usage(options);
//                return;
//            }
//            if (! line.hasOption("conffile")){
//                if( ! line.hasOption("url")|| ! line.hasOption("passwd")|| ! line.hasOption("user")){
//                    usage(options);
//                    return;
//                }else{
//                    dbSettings.setProperty("ORACLE_DB_URL", line.getOptionValue("url"));
//                    dbSettings.setProperty("ORACLE_DB_USERNAME", line.getOptionValue("user"));
//                    dbSettings.setProperty("ORACLE_DB_PASSWORD", line.getOptionValue("passwd"));
//                }
//            }else{
//                dbSettings=dbIO.getDBproperties(line.getOptionValue("conffile"));
//            }            
//        }catch( ParseException exp ) {
//            // oops, something went wrong
//            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
//            usage(options);
//        }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
/**
 *
 * @author Oleg
 */
public class OptionControlClass {
   public static Options OptionControlClass() {
    Option conffile = OptionBuilder.withArgName( "conffile" )
        .hasArg()
        .withDescription(  "use given file for DataSource configuration." )
        .create( "conffile" );
    Option url = OptionBuilder.withArgName( "url" )
        .hasArg()
        .withDescription(  "use given url to configure DataSourcce." )
        .create( "url" );
    Option passwd = OptionBuilder.withArgName( "passwd" )
        .hasArg()
        .withDescription(  "use given password to configure DataDource." )
        .create( "passwd" );
    Option user = OptionBuilder.withArgName( "user" )
        .hasArg()
        .withDescription(  "use given user to configure DataSource." )
        .create( "user" );
    Option propertieFile = OptionBuilder.withArgName( "propertieFile" )
        .hasArg()
        .withDescription(  "use given file to get/write properties." )
        .create( "propertieFile" );
    Option propTableOwner = OptionBuilder.withArgName( "propTableOwner" )
        .hasArg()
        .withDescription(  "owner of table containing properties." )
        .create( "propTableOwner" );
    Option propTableName = OptionBuilder.withArgName( "propTableName" )
        .hasArg()
        .withDescription(  "Name of table containing properties." )
        .create( "propTableName" );
    Options options = new Options();
    options.addOption("db2file", false, "direction of properties flow");
    options.addOption("file2db", false, "direction of properties flow");
    options.addOption("?", false, "display this help");
    options.addOption(conffile);
    options.addOption(url);
    options.addOption(passwd);
    options.addOption(user);
    options.addOption(propertieFile);
    options.addOption(propTableOwner);
    options.addOption(propTableName);
    return options;
    }
    public static void processArgsLogic(CommandLine line, Options options) {
        Properties dbSettings = new Properties();
        ParsParams pp = new ParsParams();
        Properties mainProperties = new Properties();
        String propFile = new String();
        String tableName = new String();
        String tableOwner = new String();
        DataBaseIO dbIO = new DataBaseIO();
        if (line.hasOption("?")) {
            usage(options);
            return;
        }
        //check if direction defined
        if (!line.hasOption("conffile")) {
            if (!line.hasOption("url") || !line.hasOption("passwd") || !line.hasOption("user")) {
                error(options, "Please define either \"conffile\" or connection properties (url, passwd, user)!");
                return;
            } else {
                dbSettings.setProperty("ORACLE_DB_URL", line.getOptionValue("url"));
                dbSettings.setProperty("ORACLE_DB_USERNAME", line.getOptionValue("user"));
                dbSettings.setProperty("ORACLE_DB_PASSWORD", line.getOptionValue("passwd"));
            }
        } else {
            dbSettings = dbIO.getDBproperties(line.getOptionValue("conffile"));
        }
        DataSource ods = dbIO.getDS(dbSettings);
        dbIO.setDs(ods);
        if ((!line.hasOption("propTableName"))
                || (!line.hasOption("propTableOwner"))
                || (!line.hasOption("propertieFile"))) {
            error(options, "propertieFile, propTableName, propTableOwner are mandatory to define.");
            return;
        } else {
            tableName = line.getOptionValue("propTableName");
            tableOwner = line.getOptionValue("propTableOwner");
            propFile = line.getOptionValue("propertieFile");
        }
        if (line.hasOption("db2file")) {
            mainProperties = dbIO.readPropertiesFromDB(tableOwner, tableName);
            pp.writePropertiesToFile(propFile, mainProperties);
        } else if (line.hasOption("file2db")) {
            mainProperties = pp.getPropertiesFromFile(propFile);
            dbIO.deletePropertiesFromDB(tableOwner, tableName);
            dbIO.insertPropertiesInDB(mainProperties, tableOwner, tableName);
        } else {
            error(options, "You should define db2file or file2db");
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
        formatter.printHelp("ControlTool", options);
    }
}

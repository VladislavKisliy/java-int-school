/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
/**
 *
 * @author Oleg
 */
public class OptionControlClass {
   public static Options OptionControlClass(String[] argv) throws Exception{
    Option conffile = OptionBuilder.withArgName( "conffile" )
        .hasArg()
        .withDescription(  "use given file for DataSource configuration" )
        .create( "conffile" );
    Option url = OptionBuilder.withArgName( "url" )
        .hasArg()
        .withDescription(  "use given url to configure DataSourcce" )
        .create( "url" );
    Option passwd = OptionBuilder.withArgName( "passwd" )
        .hasArg()
        .withDescription(  "use given password to configure DataDource" )
        .create( "passwd" );
    Option user = OptionBuilder.withArgName( "user" )
        .hasArg()
        .withDescription(  "use given user to configure DataSourcce" )
        .create( "user" );
    Option propertieFile = OptionBuilder.withArgName( "propertieFile" )
        .hasArg()
        .withDescription(  "use given file to get/write properties" )
        .create( "propertieFile" );

    Options options = new Options();

    options.addOption("force", false, "force overwrite properties");
    options.addOption("check", false, "check diff of properties without update");
//    options.addOption("db2file", false, "direction of properties flow");
    options.addOption("file2db", false, "direction of properties flow");
    options.addOption("?", false, "display this help");
    options.addOption(conffile);
    options.addOption(url);
    options.addOption(passwd);
    options.addOption(user);
    options.addOption(propertieFile);
    return options;
    }

}

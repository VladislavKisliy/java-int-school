/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
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
    private static final Logger LOG = Logger.getLogger(ParsParams.class.getName());

    public static void main(String[] args) {

        Options options = OptionControlClass.OptionControlClass();
        CommandLineParser parser = new GnuParser();
        CommandLine line = null;
        try {
            line = parser.parse(options, args);
        } catch (ParseException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        OptionControlClass.processArgsLogic(line, options);
    }


}

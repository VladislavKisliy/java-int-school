/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.UnrecognizedOptionException;
/**
 *
 * @author user
 */
public class OptionControlClass {
   public void main(String[] argv) throws Exception{
       Options opts = createOptions();
try
    {
    CommandLine cmd = new GnuParser().parse(opts, argv);

    String alphabet = "";
    String   format = "";
    String   dbName = "";
    String location = "";
    boolean create = cmd.hasOption('c');

    if (cmd.hasOption('h'))
        exitHelp(opts, 0);

    if (cmd.hasOption('l'))
        location = cmd.getOptionValue('l');
    else
        location = System.getProperty("user.dir");

    if (cmd.hasOption('d'))
        dbName = cmd.getOptionValue('d');
    else
        exitHelp(opts, 2, "No index name was specified");

    String [] fileNames = cmd.getArgs();
    Set seqFiles = new HashSet();
    }

catch (MissingOptionException moe)
    {
    exitHelp(opts, 1, moe.getMessage());
}
catch (MissingArgumentException mae)
    {
    exitHelp(opts, 1, mae.getMessage());
}
catch (UnrecognizedOptionException uoe)
    {
    exitHelp(opts, 1, uoe.getMessage());
}
catch (Exception e)
    {
    exitHelp(opts, 1, e.getMessage());
}
    }
private static Options createOptions()
   {
    Options opts = new Options();
    boolean hasArg = true;

    Option alphabet = new Option("a", "alphabet", hasArg,
                                 "Specifies the source file alphabet "
                                 + "(required if creating an index)");
    alphabet.setRequired(false);

    Option create = new Option("c", "create", ! hasArg,
                               "Create a new index (optional)");
    create.setRequired(false);

    Option dbname = new Option("d", "dbname", hasArg,
                               "Specifies the symbolic database name "
                               + "(used as the index directory name)");
    dbname.setRequired(false);

    Option format = new Option("f", "format", hasArg,
                               "Specifies the source file format "
                               + "(required if creating an index)");
    format.setRequired(false);

    Option help = new Option("h", "help", ! hasArg,
                             "Command line help");
    help.setRequired(false);

    Option index = new Option("i", "index", hasArg,
                              "Specifies the indexing scheme "
                              + "(optional, defaults to 'flat')");
    index.setRequired(false);

    Option location = new Option("l", "location", hasArg,
                                 "Path to the index root directory "
                                 + "(optional, defaults to cwd)");
    location.setRequired(false);

    opts.addOption(alphabet);
    opts.addOption(create);
    opts.addOption(dbname);
    opts.addOption(format);
    opts.addOption(help);
    opts.addOption(index);
    opts.addOption(location);

    return opts;
}

private static void exitHelp(Options opts, int exitValue)
   {
    HelpFormatter help = new HelpFormatter();
    help.printHelp("java org.biojava.app.BioFlatIndex", opts);
    System.exit(exitValue);
}

private static void exitHelp(Options opts, int exitValue, String message)
   {
    if (exitValue == 0)
        System.out.println(message);
    else
        System.err.println(message);

    exitHelp(opts, exitValue);
}    
}

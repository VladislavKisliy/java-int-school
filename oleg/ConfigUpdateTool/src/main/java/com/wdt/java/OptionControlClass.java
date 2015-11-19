/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

import java.io.PrintStream;
import org.apache.commons.cli.*;

/**
 *
 * @author user
 */
public class OptionControlClass {
    private static void printHelp(PrintStream out, Options options) {
        out.println("Usage: java " + OptionControlClass.class.getName() +  " [OPTION]\nwhere OPTION includes:");
        Util.printOptions(out, options);
    }
}

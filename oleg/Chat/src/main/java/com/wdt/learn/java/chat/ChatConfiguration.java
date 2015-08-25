/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.learn.java.chat;

/**
 *
 * @author Oleg
 */
public class ChatConfiguration {
    private static String serverHostname;
    private static int serverPort;
    public static void setServerName(String inServerName)
    { 
            serverHostname=inServerName; 
    }
    public static String getServerName()
    { 
            return serverHostname; 
    }
    public static void setServerPort(int inServerPort)
    { 
            serverPort=inServerPort; 
    }
    public static int getServerPort()
    { 
            return serverPort; 
    }
}

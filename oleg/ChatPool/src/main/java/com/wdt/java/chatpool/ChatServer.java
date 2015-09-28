/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java.chatpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oleg
 */
public class ChatServer {
    private ServerSocket server;
    private ChatServer (int port){
        try {
            this.server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] argc)
    {
        ChatServer localServer = new ChatServer(2043);
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.learn.java.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channels;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oleg 
 */
public class ChatServerM {

//    public Socket getClientSocket(ServerSocket server) throws IOException{
//        return server.accept();
//    }
    public void listenClentTalk(ServerSocket server) throws IOException{
        Socket client  = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
    }
    public static void main(String[] argc) {
        ChatConfiguration.setServerName("localhost");
        ChatConfiguration.setServerPort(2047);

    }
}

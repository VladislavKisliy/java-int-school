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

    public Socket getClientSocket(ServerSocket server) throws IOException{
        return server.accept();
    }
    public String listenClentTalk(Socket client) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        return in.readLine(); 
    }
    public PrintWriter writeToClient(Socket client) throws IOException{
        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
        return out;
    }
    public static void main(String[] argc) {
        ChatConfiguration.setServerName("localhost");
        ChatConfiguration.setServerPort(2047);
        try{
            ServerSocket server = new ServerSocket(ChatConfiguration.getServerPort());
            Socket client = getClientSocket(server);
        }catch(IOException e){
            
        }
    }
}

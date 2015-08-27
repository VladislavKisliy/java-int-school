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
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Oleg 
 */
public class ChatClientM {
    String name;
    public ChatClientM(String inName, String serverName, int serverPort){
        this.name=inName;
        try{
        Socket socket = new Socket(serverName,serverPort);
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(in.readLine()!=null){
                out.print(System.in);
            }
        } catch (UnknownHostException e) {
        System.out.println("Unknown host:"+ChatConfiguration.getServerName());
        System.exit(1);
        } catch  (IOException e) {
        System.out.println("No I/O");
        System.exit(1);
        }
    }
    
}

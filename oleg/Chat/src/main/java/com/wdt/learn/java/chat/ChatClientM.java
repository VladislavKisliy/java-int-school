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

/**
 *
 * @author Oleg 
 */
public class ChatClientM {
    final String name;
    static PrintWriter out;
    static BufferedReader in;
    public ChatClientM(String inName, String serverName, int serverPort) throws IOException{
        this.name=inName;
        Socket socket = new Socket(serverName,serverPort);
        out = new PrintWriter(socket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
    public void listenServerSocket() throws IOException{
        String serverInput;
        String clientMessage=null;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        while ((serverInput = this.in.readLine())!=null || (clientMessage = stdIn.readLine())!=null){
            System.err.println("I'm in client While!");
            System.out.println(serverInput);
            System.out.println("Me: "+clientMessage);
            out.println(this.name+": "+clientMessage);
        }
    }
    
//public static void main (String[] argc) throws IOException{
//    String userInput;
//    ChatConfiguration.setServerName("127.0.0.1");
//    ChatConfiguration.setServerPort(25);
//    ChatClientM client = new ChatClientM("Oleg",ChatConfiguration.getServerName(), ChatConfiguration.getServerPort());
//    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//    while ((userInput = stdIn.readLine())!=null){
//        System.out.println("Me: "+userInput);
//        out.println(client.name+": "+userInput);
//    }
//}
}
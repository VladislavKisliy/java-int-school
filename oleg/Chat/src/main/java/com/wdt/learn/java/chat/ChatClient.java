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
public class ChatClient {
    String name;
    public ChatClient(String inName){
        this.name=inName;
    }
    public void sendOverSocket(String inText){
         try{
           BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
           String line = in.readLine();
           System.out.println("Text received: " + line);
         } catch (IOException e){
           System.out.println("Read failed");
           System.exit(1);
         }    
    }
    
    public void listenSocket(){
    //Create socket connection
    try{
        Socket socket = new Socket("localhost", 2047);
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      } catch (UnknownHostException e) {
        System.out.println("Unknown host: localhost");
        System.exit(1);
      } catch  (IOException e) {
        System.out.println("No I/O");
        System.exit(1);
      }
    }
}

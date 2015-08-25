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
import java.net.*;

/**
 *
 * @author Oleg 
 */
public class ChatServer implements Runnable {
    ServerSocket server;
    Socket client;
    BufferedReader in;
    PrintWriter out;   
    public ChatServer(int serverPort){

        try{
            server = new ServerSocket(serverPort); 
        }catch(IOException e){
            System.out.println("Failed to listen port: "+serverPort);
            System.exit(-1);
        }    
//        
          while(true){
            try{
              String line = in.readLine();
      //Send data back to client
              System.out.println(line);
            } catch (IOException e) {
              System.out.println("Read failed");
              System.exit(-1);
            }
          }}
        public void listenSocket(){
//            Socketserver.acceptSocket
        try{
            client = server.accept();
        } catch (IOException e) {
            System.out.println("Accept failed:");
            System.exit(-1);
        }
        }
//      listenSocketBufferedReaderclientPrintWriter
        public void listenSocketBufferedReaderclientPrintWriter(){
        try{
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }
        }
//      listenSocket

    
    public void run(){

    }
    public static void main(String args[]){
        ChatServer localServer=new ChatServer(2047);
    }
}


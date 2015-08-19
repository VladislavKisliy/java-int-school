/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.learn.java.chat;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Oleg 
 */
public class ChatServer implements Runnable {
    public ChatServer(int serverPort){
        try{
            ServerSocket server = new ServerSocket(serverPort); 
        }catch(IOException e){
            System.out.println("Failed to listen port: "+serverPort);
            System.exit(-1);
        }    
    }
    public void run(){

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.learn.java.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Oleg 
 */
public class ChatServerM {
    
    public void InitializeServer(int serverPort) throws IOException{
        ServerSocket server = new ServerSocket(serverPort);
    }
    public Socket getClientSocket(ServerSocket server) throws IOException{
        return server.accept();
    }
    
}

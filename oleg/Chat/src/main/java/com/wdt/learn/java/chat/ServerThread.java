/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.learn.java.chat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServerThread implements Runnable{
    private ClientForServer clientData;
    public ServerThread(ClientForServer c){
        this.clientData=c;
    }
    @Override
    public void run() {
        String msgFromClient;
        try {
            while((msgFromClient=clientData.getIn().readLine())!=null){
                clientData.getOut().print(msgFromClient);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

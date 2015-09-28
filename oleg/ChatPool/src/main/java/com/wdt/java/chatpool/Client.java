/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java.chatpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oleg
 */
public class Client implements Runnable {
    private String name;
    private Socket socket;
    private PrintWriter out=null; 
    private BufferedReader in=null;
    private Client(String inName, Socket clientSocket){
        this.name=inName;
        this.socket=clientSocket;
        try {
            //get the socket's ouput
            out = new PrintWriter(socket.getOutputStream(), true);
            //get the socket's input
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            out.close();
        }
    }
    @Override
    public void run (){
        String serverMessage=null;
        String clientMessage=null;
        while(true){
        
        try {
            clientMessage=System.console().readLine();
            serverMessage=in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        if( ! serverMessage.isEmpty()){
            System.out.println(serverMessage);
        }
        if( ! clientMessage.isEmpty()){
            if(clientMessage == "quit"){
                out.println(this.name+" has quit...");
                System.out.println("See ya!");
                System.exit(0);
            }else{
            out.println(this.name+" says: "+clientMessage);
            }
        }
        }
    }
}

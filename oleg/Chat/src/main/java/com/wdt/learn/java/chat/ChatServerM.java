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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleg 
 */
public class ChatServerM {
    // Doesn't work at all
    private static List <ClientForServer> listOfObjects = new ArrayList();
    public static List getListOfObjects() {
        return listOfObjects;
    }

    public static void addToListOfObjects(ClientForServer object) {
        listOfObjects.add(object);
    }
    
    public static void notifyAllSockets(String message){
        for (int i=0; i< ChatServerM.getListOfObjects().size();i++){
            ClientForServer tempObject=(ClientForServer) ChatServerM.getListOfObjects().get(i);
            tempObject.getOut().print(message);
        }
    }
       
    public static ClientForServer listenClentTalk(ServerSocket server) throws IOException{
        Socket client  = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
        ChatServerM.addToListOfObjects(new ClientForServer(client,in,out));
        out.print("Welcome to Chat!");
        return new ClientForServer(client,in,out);
    }
    
    

}

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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleg 
 */
public class ChatServerM {
    private static List <ClientForServer> listOfObjects = new ArrayList();
    public static List getListOfObjects() {
        return listOfObjects;
    }

    public static void setListOfObjects(ClientForServer object) {
        listOfObjects.add(object);
    }
       
    public static ClientForServer listenClentTalk(ServerSocket server) throws IOException{
        Socket client  = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
        ChatServerM.setListOfObjects(new ClientForServer(client,in,out));
        return new ClientForServer(client,in,out);
    }
    
    

}

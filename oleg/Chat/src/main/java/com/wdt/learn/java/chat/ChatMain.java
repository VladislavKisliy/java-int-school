/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.learn.java.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

/**
 *
 * @author user
 */
public class ChatMain {
    public static void main(String[] argc) throws IOException {
    ChatConfiguration.setServerPort(2047);
    ChatConfiguration.setServerName("127.0.0.1");
    if(argc[1]=="server"){
        ServerSocket server = new ServerSocket(ChatConfiguration.getServerPort()) ;
        ClientForServer clentObject; 
        clentObject = ChatServerM.listenClentTalk(server);
        String msgFromClient;
        while((msgFromClient=clentObject.getIn().readLine())!=null){
            clentObject.getOut().print(msgFromClient);
        }
    }
    else if (argc[1]=="clent"){
        String userInput;
        ChatClientM client = new ChatClientM("Oleg",ChatConfiguration.getServerName(), ChatConfiguration.getServerPort());
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        while ((userInput = stdIn.readLine())!=null){
            System.out.println("Me: "+userInput);
            ChatClientM.out.println(client.name+": "+userInput);
        }
    }
    else {
        System.exit(-1);
    }
    }
}

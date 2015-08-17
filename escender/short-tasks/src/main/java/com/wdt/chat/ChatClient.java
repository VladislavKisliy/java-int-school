/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Dreamesc
 */
public class ChatClient  {
     public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8081);
        
        DataInputStream dataInStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataoutStream = new DataOutputStream(socket.getOutputStream());
        
        BufferedReader bfrReader = new BufferedReader(new InputStreamReader(System.in));
        
        String line = null;
        while (true) {
           line = bfrReader.readLine();
           dataoutStream.writeUTF(line);
           dataoutStream.flush();
           
           line = dataInStream.readUTF();
           System.out.println("Text from chat: "+line);
        }
}
}

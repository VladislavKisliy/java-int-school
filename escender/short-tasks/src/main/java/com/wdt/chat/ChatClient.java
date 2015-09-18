/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dreamesc
 */
public class ChatClient extends Thread {

    // Client doesn't work properly
    // reading from server doesn't work
    // when server close connection client won't stop
    
    // Static ???
    static Socket socket;
    static boolean endFlag = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Client Point 1");
        socket = new Socket("127.0.0.1", 8081);
        Thread pollServer = new ChatClient();

        DataOutputStream dataoutStream = new DataOutputStream(socket.getOutputStream());

        BufferedReader bfrReader = new BufferedReader(new InputStreamReader(System.in));
        try {

            pollServer.start();

            String line = null;
            while (true) {

                line = bfrReader.readLine();

                dataoutStream.writeUTF(line);
                dataoutStream.flush();

                // NPE unsafed expression
                if (line.equals("buy")) {
                    endFlag = true;
                    pollServer.join();
                    return;
                }
            }
        } finally {
            bfrReader.close();
            dataoutStream.close();
            socket.close();
        }

    }

    @Override
    public void run() {
        
        System.out.println("Client Point 2");
        DataInputStream dataInStream;
        try {
            dataInStream = new DataInputStream(socket.getInputStream());
            String line;
            while (true) {

                line = dataInStream.readUTF();
                if (endFlag) {                    
                    dataInStream.close();
                    return;
                }

                if (line.length() > 0) {
                    System.out.println("Text from chat: " + line);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

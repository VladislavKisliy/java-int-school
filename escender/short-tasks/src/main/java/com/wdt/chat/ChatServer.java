/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dreamesc
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        Socket socket = serverSocket.accept();

        DataInputStream dataInStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataoutStream = new DataOutputStream(socket.getOutputStream());
        
        String line;
        while (true) {
            line = dataInStream.readUTF();
            System.out.println("Client sent: " + line);
            dataoutStream.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
            dataoutStream.flush(); // заставляем поток закончить передачу данных.
        }
    }
}

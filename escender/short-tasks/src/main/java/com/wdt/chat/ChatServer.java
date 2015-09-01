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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dreamesc
 */
public class ChatServer extends Thread {

    private Socket socket;

    public ChatServer(Socket socket) {
        this.socket = socket;
    }

    static ExecutorService pool;
    public void run() {
        DataInputStream dataInStream;
        try {
            dataInStream = new DataInputStream(socket.getInputStream());

            DataOutputStream dataoutStream;

            dataoutStream = new DataOutputStream(socket.getOutputStream());


            String line;
            while (true) {
                line = dataInStream.readUTF();
                System.out.println("Client sent: " + line);
                dataoutStream.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                
                dataoutStream.flush(); // заставляем поток закончить передачу данных.
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {

        pool = Executors.newFixedThreadPool(10);

        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            System.out.println("Server Point 1");
            pool.submit(new ChatServer(serverSocket.accept()));            
            System.out.println("Server Point 2");
        }
    }
}

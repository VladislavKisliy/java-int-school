/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.chat;

import static com.wdt.chat.ChatServer.pool;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dreamesc
 */
public class ChatServer extends Thread {
    
    static List<ChatServer> clientList;
    private Socket socket;
    private DataInputStream dataInStream;
    private DataOutputStream dataoutStream;
    private Integer index;
    
    public ChatServer(Socket socket, Integer index) {
        this.socket = socket;
        this.index = index;
    }
    static ExecutorService pool;

    public void run() {
        System.out.println("Server Point 2");
        try {
            dataInStream = new DataInputStream(socket.getInputStream());
            dataoutStream = new DataOutputStream(socket.getOutputStream());
            
            String line;
            while (true) {
                line = dataInStream.readUTF();
                System.out.println("Client sent: " + line);
                for (ChatServer client : clientList) {
                    client.broadcast(line);
                }
                if (line.equals("buy")) {
                    dataInStream.close();
                    dataoutStream.close();
                    clientList.remove(this);
                    return;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void broadcast(String line) throws IOException {
        dataoutStream.writeUTF(line);        
        dataoutStream.flush();        
    }
    
    public static void main(String[] args) throws IOException {
        pool = Executors.newFixedThreadPool(10);
        
        
        clientList = new LinkedList<ChatServer>();//Collections.synchronizedList();//new ArrayList<ChatServer>();
        ServerSocket serverSocket = new ServerSocket(8081);
        try {
            
        while (true) {
            
            System.out.println("Server Point 1");
            ChatServer client = new ChatServer(serverSocket.accept(), clientList.size());
            clientList.add(client);
            pool.submit(client);
            
        }
        } finally {
            serverSocket.close();
        }
    }
}

package com.weigandtconsulting.javaschool.dmitry.wtc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerSideChat{
	private static final int port = 4444;
	private static ServerSocket listener;
	private static List<PrintWriter> writerList = new ArrayList<PrintWriter>();
	private static Set<String> names = new HashSet<String>();
	
	private static class ClientHandler extends Thread{
		Socket clientSocket;
		private BufferedReader in;
        private PrintWriter out;
        String name;

		public ClientHandler(Socket clientSocket){
			this.clientSocket = clientSocket;
		}
		
		public void run(){
			try {
				in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				// add writer for current thread
				writerList.add(out);
				// submit client's name
				out.println("Submit your name:");
				if ((name = in.readLine()) != null){
					names.add(name);
					System.out.println("New client " + name + " is connected."); 
					out.println("Hello, dude "+ name + "!");
				}					
				// listen for messages
				String input;
				while ((input = in.readLine()) != null) {
                    for (PrintWriter writer : writerList) {
                    	if (writer != out)
                    		writer.println(name + ": " + input);
                    }
                }
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
                    writerList.remove(out);
                    names.remove(name);
                    try {
						clientSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}	

    public static void main(String[] args) throws IOException{
    	if (args[0].equals("server")){
			System.out.println("WhiteThrashChat server has been started!");    	
	    	listener = new ServerSocket(port);
	        try {
	            while (true) {
	            	new ClientHandler(listener.accept()).start();	            	
	            }
	        } finally {
	            listener.close();
	        }
    	} else  
	    	ClientSideChat.dowork("127.0.0.1", port); 
    }
}

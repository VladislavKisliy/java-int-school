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
	private final int PORT = 4444;
	private ServerSocket listener;
	private List<PrintWriter> writerList = new ArrayList<PrintWriter>();
	private Set<String> names = new HashSet<String>();
	
	public void startClient(){
		try {
			new ClientHandler(listener.accept()).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	private class ClientHandler extends Thread{
		private Socket clientSocket;
		private BufferedReader in;
        private PrintWriter out;
        private String name;

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
					if (input.equals("quit")){
						out.println("Go away");
						System.out.println("Client " + name + " is disconnected."); 
						return;
					}						
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
    	ServerSideChat server = new ServerSideChat();
    	if (args.length !=0  && args[0].equals("server")){
			System.out.println("WhiteThrashChat server has been started!");  
	    	server.listener = new ServerSocket(server.PORT);
	        try {
	            while (true) {
	            	server.startClient();	            	
	            }
	        } finally {
	            server.listener.close();
	        }
    	} else {
    		ClientSideChat client = new ClientSideChat();
    		client.dowork("127.0.0.1", server.PORT); 
    	}
	    	
    }
}

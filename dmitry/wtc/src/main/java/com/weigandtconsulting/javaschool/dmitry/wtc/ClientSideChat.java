package com.weigandtconsulting.javaschool.dmitry.wtc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSideChat extends Thread{
	// Static ???
	private static BufferedReader in;
    private static PrintWriter out;
    private static String name;
    private static boolean working = true;
    
	public void run(){
		String fromServer;
    	try {
			while ((fromServer = in.readLine()) != null) {				
				System.out.println(fromServer);
				if (fromServer.equals("Go away")){
					working = false;
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void dowork(String server, int port) throws UnknownHostException, IOException{
        Socket socket = new Socket(server, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        String fromServer;
        String fromUser;
        Scanner userInput = new Scanner(System.in);
    	fromServer = in.readLine();
        if (fromServer.equals("Submit your name:")){
        	System.out.println(fromServer);        	
        	name = userInput.nextLine();
        	out.println(name);
        } else{
        	System.out.println("Strange anonymous server. Exiting.");
        	userInput.close();
        	socket.close();
        	return;
        }	        	
        
        new ClientSideChat().start();
        
        try{
        	while(working && (fromUser = userInput.nextLine()) != null){        		
        		out.println(fromUser);
        	}
        } finally{
        	userInput.close();
        	socket.close();
        }
                
	}

}

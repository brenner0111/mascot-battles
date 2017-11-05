package com.mygdx.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MascotServer {
	protected static ArrayList<ServerThread> threads = new ArrayList<ServerThread>();
	protected static ArrayList<String> data = new ArrayList<String>();
	//protected static String data = "";
	
	public static void main(String[] args) throws IOException {
        ServerSocket listeningSocket = new ServerSocket(6789);
        
        System.out.println("Server Started!");
        Socket connectionSocket = null;
        
        //listen, connect, and create a new Server thread
        while (threads.size() < 1) {
            try {
                connectionSocket = listeningSocket.accept(); 
            } catch (IOException e) {
                System.out.println("I/O error");
            }
            ServerThread st = new ServerThread(connectionSocket);
            threads.add(st);
            data.add("");
            st.start();
        }
        while (threads.size() > 0) {
	        for (int i = 0; i < threads.size(); i++) {
	        		ServerThread thread = threads.get(i);
	        		thread.isDone = false;
	        		data.set(i, thread.data);
	        }
	        	for (int i = 0; i < threads.size(); i++) {
	        		ServerThread thread = threads.get(i);
	        		thread.temp = "Hi Index: " + i;
	        		thread.isDone = true;
	        	}
        }
    }
}

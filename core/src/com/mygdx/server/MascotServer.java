package com.mygdx.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MascotServer {
	public static void main(String[] args) throws IOException {
        ServerSocket listeningSocket = new ServerSocket(6789);
        
        System.out.println("Server Started!");
        Socket connectionSocket = null;
        
        //listen, connect, and create a new Server thread
        while (true) {
            try {
                connectionSocket = listeningSocket.accept(); 
            }catch (IOException e) {
                System.out.println("I/O error");
            }
            new ServerThread(connectionSocket).start();
        }
    
    }
}

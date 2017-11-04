package com.mygdx.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNetworkThread extends Thread {
	private Socket clientSocket;
	private DataOutputStream outToServer;
    private BufferedReader inFromServer;
    private volatile String dataFromServer;
    private boolean connectedToServer = false;
    
	public void run() {
		if (!connectedToServer) {
			connectToServer();
			connectedToServer = true;
		}
		sendAndReceiveFromServer();
	}
	
	private void connectToServer() {
		try {
			clientSocket = new Socket("204.84.9.82", 6789);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("Connected to Server");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private synchronized void sendAndReceiveFromServer() {
		try {
        		//Send to server - KEY PRESSES
			outToServer.writeBytes("this is coming from the network thread test test" + '\n');
			 //receive from server - RECEIVE STRING FROM SERVER
			dataFromServer = dataFromServer + inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	synchronized String getDataFromServer() {
		return dataFromServer;
	}
}

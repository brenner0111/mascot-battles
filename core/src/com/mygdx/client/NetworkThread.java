package com.mygdx.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkThread extends Thread {
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
			clientSocket = new Socket("127.0.0.1", 6789);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("Connected to Server");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void sendAndReceiveFromServer() {
		try {
        		//Send to server
			outToServer.writeBytes("this is coming from the network thread" + '\n');
			 //receive from server
			dataFromServer = inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	synchronized String getDataFromServer() {
		return dataFromServer;
	}
}

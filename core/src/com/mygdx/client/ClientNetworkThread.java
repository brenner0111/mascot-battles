package com.mygdx.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ClientNetworkThread extends Thread {
	private Socket clientSocket;
	private DataOutputStream outToServer;
    private BufferedReader inFromServer;
    public volatile String fromServer = "";
    public volatile boolean isNotRunning = true;
    //private String dataFromServer;
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
		while(true) {
			try {
				//Send to server - KEY PRESSES
				outToServer.writeBytes(getKeyboardInputs() + "\n");
				//receive from server - RECEIVE STRING FROM SERVER
				fromServer = inFromServer.readLine();

				//System.out.println(fromServer);

			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private String getKeyboardInputs() {
		isNotRunning = true;
		String ret = "";
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			ret += "W ";
			isNotRunning = false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			ret += "A ";
			isNotRunning = false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			ret += "S ";
			isNotRunning = false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			ret += "D ";
			isNotRunning = false;
		}
		ret += "| ";
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
			ret += "1";
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
			ret += "2";
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
			ret += "3";
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_4))
			ret += "4";
		else
			ret += "";
		return ret;
	}
}

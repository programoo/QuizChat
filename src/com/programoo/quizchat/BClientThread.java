package com.programoo.quizchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.Message;
import android.util.Log;

public class BClientThread extends Thread {
	private String tag = this.getClass().getSimpleName();
	private Socket clientSocket = null;
	String modifiedSentence;

	boolean run = true;

	public BClientThread(Socket client_socket) {
		this.clientSocket = client_socket;

	}

	public void run() {

		while (run) {

			BufferedReader inFromServer;
			try {
				inFromServer = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));

				modifiedSentence = inFromServer.readLine();
				Info.hostMsg.add(modifiedSentence);

				
				SecondActivity.updateUIThread();
				
				Log.d(tag, "FROM SERVER: " + modifiedSentence);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	
	public void updateMainUIThread(){
		
	}
	
}
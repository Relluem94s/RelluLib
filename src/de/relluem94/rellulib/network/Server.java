package de.relluem94.rellulib.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port); 
		socket = serverSocket.accept();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		
	}
	
	
	
}

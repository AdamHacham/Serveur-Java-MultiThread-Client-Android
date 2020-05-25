package com.hachamadam;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EasyServeur extends Thread{
	int port = 7090;
	ServerSocket srv_socket = null;
	protected int nbClients = 0;
	@Override
	public void run() {
		try {
			srv_socket = new ServerSocket(port);
			System.out.println("Waiting for clients ...");
		while(true) {	
			
			Socket clt_socket = srv_socket.accept();
			++nbClients;
			ServiceRunnable sr = new ServiceRunnable(clt_socket,nbClients);
			new Thread(sr).start();	
		}
			
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	
		
	}
	
}

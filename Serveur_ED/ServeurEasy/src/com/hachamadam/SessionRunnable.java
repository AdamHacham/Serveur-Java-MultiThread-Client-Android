package com.hachamadam;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import database.Client_Data;

public class SessionRunnable implements Runnable {

	protected String aId ;
	protected Socket clt_socket = null;
	protected BufferedReader in = null;
	protected Client_Data cd ;
	protected PrintWriter pw;
	protected String arg1;
	protected String ip_clt;
	protected int nbr = 0 ;
	protected ArrayList<String> ht;
	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;
	protected ByteArrayInputStream bis ;
	
	public SessionRunnable(String aId, Socket clt_socket, Client_Data cd) {
		super();
		this.aId = aId;
		this.clt_socket = clt_socket;
		this.cd = cd;
	}

	@Override
	public void run() {
		try {	
			oos = new ObjectOutputStream(clt_socket.getOutputStream());
			in  = new BufferedReader(new InputStreamReader(clt_socket.getInputStream()));
			pw = new PrintWriter(clt_socket.getOutputStream(),true);
			ip_clt = clt_socket.getRemoteSocketAddress().toString();
			ht = new ArrayList<String>();
			ht=cd.getDataById(aId);
			while(true) {
			String req = in.readLine();
			System.out.println(req);
			if(req.equals("Session_ok")) {
				pw.println("env_data_client");
				System.out.println("env_d");
				pw.flush();
				oos.writeObject(ht);
			}
			
			}
		// TODO Auto-generated method stub
		
	}catch(IOException e) {
		e.printStackTrace();
	}

}

	public String getaId(String string, String string2) {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}
}

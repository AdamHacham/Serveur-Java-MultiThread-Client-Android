package com.hachamadam.serveur;


//import java.rmi.activation.ActivationID;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;






public class Server {
	
	public static void main(String [] args) throws Exception{
	try {
		Registry registre = LocateRegistry.createRegistry(1099);
		registre.rebind("insertclient", new InsClient());
	}catch (Exception e) {
		System.out.println("Exception "+ e);
	}
	
	}
		
}

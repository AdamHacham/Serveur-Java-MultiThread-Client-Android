package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.hachamadam.serveur.Client;

public interface InsClientDistant extends Remote {
	
	int MailExists(String mail) throws RemoteException ;
	int TelExists(String tel) throws RemoteException ;
	int MailPassExists(String mail,String pass) throws RemoteException ;
	int TelPassExists(String tel,String pass) throws RemoteException ;
	int InsertClient(String pNom ,String pPrenom,String pPassword,String pMail,String pDate,String pIndicatif,String pTel) throws RemoteException;
	Client newClient() throws RemoteException; 

}

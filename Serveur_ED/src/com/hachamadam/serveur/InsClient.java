package com.hachamadam.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

import com.hacham.database.Sha;

import service.InsClientDistant;

public class InsClient extends UnicastRemoteObject implements InsClientDistant{
	

	protected String aNom;
    protected String aPrenom;
    protected String aPassword;
    protected String aMail;
    protected String aDate;
    protected String aIndicatif;
    protected String aTel ;
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InsClient() throws RemoteException {
		super();
	} 
    public InsClient(String pNom ,String pPrenom,String pPassword,String pMail,String pDate,String pIndicatif,String pTel) throws RemoteException {
    		super();
    		this.aNom = pNom ;
    		this.aPrenom = pPrenom;
    		this.aPassword = pPassword;
    		this.aMail = pMail;
    		this.aDate = pDate ;
    		this.aIndicatif = pIndicatif;
    		this.aTel = pTel;
    }
    @Override
    public Client newClient() {
    	Client c = new Client();
    	c.setNom(aNom);
    	c.setPrenom(aPrenom);
    	c.setPassword(aPassword);
    	c.setMail(aMail);
    	c.setDate(aDate);
    	c.setIndicatif(aIndicatif);
    	c.setTel(aTel);
    	return c;
    }
    
  @Override 
  public int InsertClient(String pNom ,String pPrenom,String pPassword,String pMail,String pDate,String pIndicatif,String pTel) throws RemoteException{
    	Client c = newClient();	
   // 	String sql1 = "INSERT INTO client VALUES ( NULL,'ada','hac','azerefsdcx','mr_ha@hotmail.com','1993-08-08','33','609699888 ',NOW() , FALSE,FALSE)";
    	
    	try{
	String sql = "INSERT INTO client VALUES ( NULL,'"+pNom+"','"+pPrenom+"','"+new Sha(pPassword).HachageMdp()+"','"+pMail+"','"+pDate+"','"+pIndicatif+"','"+pTel+"', NOW() , FALSE,FALSE)";
	
    		if(c.DatabaseConnexion().executeUpdate(sql) != 0) return 1;
	} catch(SQLException e){
	e.printStackTrace();
	} finally {
	try {
	c.getCn().close();
	c.getSt().close();
	 
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
    	return 0;
}
  @Override
    public int MailExists(String mail) {
    	int rowcount = 0;
    	Client c = newClient();	
    	try {
    		String query = "SELECT * FROM client WHERE mail ='"+mail+"';";
    		ResultSet rs = c.DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return 1;
	return 0;
    }
  @Override
    public int MailPassExists(String mail,String pass) {
    	Client c = newClient();	
    	int rowcount = 0;
    	try {
    		String query = "SELECT * FROM client WHERE mail ='"+mail+"' AND '"+new Sha(pass).HachageMdp()+"'";
    		ResultSet rs = c.DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return 1;
	return 0;
    }
    @Override
	public int TelPassExists(String tel,String pass) {
    	Client c = newClient();	
    	int rowcount = 0;
    	try {
    		String query = "SELECT * FROM client WHERE tel ='"+tel+"' AND '"+new Sha(pass).HachageMdp()+"'";
    		ResultSet rs = c.DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return 1;
	return 0;
    }

	@Override
	public int TelExists(String tel) {
    	int rowcount = 0;
Client c = newClient();
    	try {
    		String query = "SELECT * FROM client WHERE tel ='"+tel+"';";
    		ResultSet rs = c.DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return 1;
	return 0;
    }


}
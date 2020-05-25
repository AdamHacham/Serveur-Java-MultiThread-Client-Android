package com.hachamadam.serveur;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hacham.database.Sha;

public class Client implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String url = "jdbc:mysql://localhost:3306/easydelivery";
    private String login = "root";
    private String passwd= "tahadam.2106" ;
    protected String aNom;
    protected String aPrenom;
    protected String aPassword;
    protected String aMail;
    protected String aDate;
    protected String aIndicatif;
    protected String aTel ;
    protected Connection cn =null;
    protected Statement st =null;
    
    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNom() {
		return aNom;
	}
	public void setNom(String aNom) {
		this.aNom = aNom;
	}
	public String getPrenom() {
		return aPrenom;
	}
	public void setPrenom(String aPrenom) {
		this.aPrenom = aPrenom;
	}
	public String getPassword() {
		return aPassword;
	}
	public void setPassword(String aPassword) {
		this.aPassword = aPassword;
	}
	public String getMail() {
		return aMail;
	}
	public void setMail(String aMail) {
		this.aMail = aMail;
	}
	public String getDate() {
		return aDate;
	}
	public void setDate(String aDate) {
		this.aDate = aDate;
	}
	public String getIndicatif() {
		return aIndicatif;
	}
	public void setIndicatif(String aIndicatif) {
		this.aIndicatif = aIndicatif;
	}
	public String getTel() {
		return aTel;
	}
	public void setTel(String aTel) {
		this.aTel = aTel;
	}
	public Connection getCn() {
		return cn;
	}
	public void setCn(Connection cn) {
		this.cn = cn;
	}
	public Statement getSt() {
		return st;
	}
	public void setSt(Statement st) {
		this.st = st;
	}
	public Statement DatabaseConnexion () {
    	try {
    	this.cn = DriverManager.getConnection(getUrl(),getLogin(),getPasswd());
    	this.st = getCn().createStatement();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return getSt();
    }
    protected int TelExists() {
    	int rowcount = 0;
    	try {
    		String query = "SELECT * FROM client WHERE tel ='"+getTel()+"';";
    		ResultSet rs = DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return 1;
	return 0;
    }

    public int MailExists() {
    	int rowcount = 0;
    	try {
    		String query = "SELECT * FROM client WHERE mail ='"+getMail()+"';";
    		ResultSet rs = DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return 1;
	return 0;
    }
    protected int MailPassExists() {
    	int rowcount = 0;
    	try {
    		String query = "SELECT * FROM client WHERE mail ='"+getMail()+"' AND '"+new Sha(getPassword()).HachageMdp()+"'";
    		ResultSet rs = DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return 1;
	return 0;
    }
    protected int TelPassExists() {
    	int rowcount = 0;
    	try {
    		String query = "SELECT * FROM client WHERE tel ='"+getTel()+"' AND '"+new Sha(getPassword()).HachageMdp()+"'";
    		ResultSet rs = DatabaseConnexion().executeQuery(query);
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

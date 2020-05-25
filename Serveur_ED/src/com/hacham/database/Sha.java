package com.hacham.database;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha implements HachageMdp{

	private String aMdp ;
	
	
	public Sha() {
		super();
	}
	public Sha(String pMdp) {
		this.aMdp = pMdp;
	}
	
	public String getMdp() {
		return this.aMdp;
	}
	public void setMdp(String mdp) {
		this.aMdp = mdp;
	}
	

	public StringBuffer HachageMdp() {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(getMdp().getBytes());
			byte byteData[] = md.digest();
			for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		    }
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb;
	}
}

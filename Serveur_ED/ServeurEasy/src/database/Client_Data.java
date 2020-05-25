package database;




import java.sql.*;
import java.util.ArrayList;




public class Client_Data{
	
	private String url = "jdbc:mysql://localhost:3306/easydelivery";
    private String login = "root";
    private String passwd= "tahadam.2106" ;
    protected String aId;
    protected String aNom;
    protected String aPrenom;
    protected String aPassword;
    protected String aMail;
    protected String aDate;
    protected String aIndicatif;
    protected String aTel ;
    protected String aAdresse;
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
	
	public Client_Data() {
		super();
	} 
    public Client_Data(String aId, String aNom, String aPrenom, String aPassword, String aMail, String aDate,
			String aIndicatif, String aTel, String aAdresse, Connection cn, Statement st) {
		super();
		this.aId = aId;
		this.aNom = aNom;
		this.aPrenom = aPrenom;
		this.aPassword = aPassword;
		this.aMail = aMail;
		this.aDate = aDate;
		this.aIndicatif = aIndicatif;
		this.aTel = aTel;
		this.aAdresse = aAdresse;
		this.cn = cn;
		this.st = st;
	}
	public Client_Data(String pNom ,String pPrenom,String pPassword,String pMail,String pDate,String pIndicatif,String pTel){
    		super();
    		this.aNom = pNom ;
    		this.aPrenom = pPrenom;
    		this.aPassword = pPassword;
    		this.aMail = pMail;
    		this.aDate = pDate ;
    		this.aIndicatif = pIndicatif;
    		this.aTel = pTel;
    }
    
  public String getIdClient(String mail , String pass) {
    	try {
    		String query = "SELECT * FROM client WHERE mail ='"+mail+"' AND password = '"+new Sha(pass).HachageMdp()+"'";
    		ResultSet rs = DatabaseConnexion().executeQuery(query);
    		while(rs.next())
    		this.aId = rs.getString("id");
    		
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
		return this.aId;
 
  }
  public ArrayList<String> getDataById(String id) {
	  ArrayList<String> ht ;
	  ht = new ArrayList<String>();
		try {
    		String query = "SELECT * FROM client WHERE id ='"+id+"'";
    		ResultSet rs = DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    		ht.add(rs.getString("nom"));
    		ht.add(rs.getString("prenom"));
    		ht.add(rs.getString("mail"));
    		ht.add(rs.getString("date"));
    		ht.add(rs.getString("tel"));
    		}//ht.put("adresse", rs.getString(""));
    		
    		
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}	  
	return ht;
  }
  public int InsertClient(String pNom ,String pPrenom,String pPassword,String pMail,String pDate,String pIndicatif,String pTel) {
   
   // 	String sql1 = "INSERT INTO client VALUES ( NULL,'ada','hac','azerefsdcx','mr_ha@hotmail.com','1993-08-08','33','609699888 ',NOW() , FALSE,FALSE)";
    	
    	try{
	String sql = "INSERT INTO client VALUES ( NULL,'"+pNom+"','"+pPrenom+"','"+new Sha(pPassword).HachageMdp()+"','"+pMail+"','"+pDate+"','"+pIndicatif+"','"+pTel+"', NOW() , FALSE,FALSE)";
	
    	if(DatabaseConnexion().executeUpdate(sql) != 0) return 1;
	} catch(SQLException e){
	e.printStackTrace();
	} finally {
	try {
	getCn().close();
	getSt().close();
	 
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
    	return 0;
}

    public boolean MailExists(String mail) {
    	int rowcount = 0;
   	try {
    		String query = "SELECT * FROM client WHERE mail ='"+mail+"';";
    		ResultSet rs = DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return true;
	return false;
    }
    public boolean MailPassExists(String mail,String pass) {
    	int rowcount = 0;
    	try {
    		String query = "SELECT * FROM client WHERE mail ='"+mail+"' AND password = '"+new Sha(pass).HachageMdp()+"'";
    		ResultSet rs = DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return true;
	return false;
    }
	public int TelPassExists(String tel,String pass) {	
    	int rowcount = 0;
    	try {
    		String query = "SELECT * FROM client WHERE tel ='"+tel+"' AND password = '"+new Sha(pass).HachageMdp()+"'";
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
	public boolean TelExists(String tel) {
    	int rowcount = 0;
    	try {
    		String query = "SELECT * FROM client WHERE tel ='"+tel+"';";
    		ResultSet rs = DatabaseConnexion().executeQuery(query);
    		while(rs.next()) {
    			rowcount++;
    		}
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	if(rowcount > 0) return true;
	return false;
    }


}
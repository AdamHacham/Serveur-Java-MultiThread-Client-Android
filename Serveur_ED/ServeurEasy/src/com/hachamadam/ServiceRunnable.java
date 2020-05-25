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
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import database.Client_Data;

public class ServiceRunnable  implements Runnable {
	
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
	protected Session session;
	protected Properties properties;
	
	
	public ServiceRunnable(Socket clt_socket,int nbr) {
		this.clt_socket = clt_socket;
		this.nbr = nbr ;
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		ht = new ArrayList<String>();
		
		try {
			
			ois  = new ObjectInputStream(clt_socket.getInputStream());
			oos = new ObjectOutputStream(clt_socket.getOutputStream());
			in  = new BufferedReader(new InputStreamReader(clt_socket.getInputStream()));
			pw = new PrintWriter(clt_socket.getOutputStream(),true);
			ip_clt = clt_socket.getRemoteSocketAddress().toString();
			System.out.println("Connexion du client numéro : "+nbr+" IP = "+ip_clt);
			ht = (ArrayList<String>) ois.readObject();
			System.out.println(ht);
			
			if(ht.size() != 2 && ht.size() != 7 ) {
				System.out.println("Authentification Failed !");
				System.out.println(ht.size());
			}
			else if(ht.size() == 2) {
			 cd = new Client_Data();
				if(cd.MailPassExists(ht.get(0).toString(),ht.get(1).toString())) {
					System.out.println(cd.MailPassExists(ht.get(0).toString(),ht.get(1).toString()));
					pw.println(cd.MailPassExists(ht.get(0).toString(),ht.get(1).toString()));
					pw.flush();
					SessionRunnable sr = new SessionRunnable(cd.getIdClient(ht.get(0).toString(),ht.get(1).toString()),clt_socket,cd);
					new Thread(sr).start();
				}else {
					System.out.println(cd.MailPassExists(ht.get(0).toString(),ht.get(1).toString()));
					pw.println(cd.MailPassExists(ht.get(0).toString(),ht.get(1).toString()));
					pw.flush();
				}
			}
			
			else if(ht.size() == 7) {
				cd = new Client_Data();
				if(cd.MailExists(ht.get(0).toString())){
					System.out.println("Cet e-mail existe déjà");
					pw.println(cd.MailExists(ht.get(0).toString())+" 1");
					pw.flush();
				} else if (cd.TelExists(ht.get(1).toString())) {
					System.out.println("Ce téléphone existe déjà");
					pw.println(cd.TelExists(ht.get(1).toString())+" 2");
					pw.flush();
				} else {
					//cd.InsertClient(ht.get("nom"),ht.get("prenom"), ht.get("password"),ht.get("email"),ht.get("date"),"33", ht.get("phone"));
					sendEmail(ht.get("email"));
					System.out.println("Nouvel Utilisateur inscrit dans la db !!!");
					pw.println(false);
					pw.flush();
				}
			}
			}catch(IOException e) {
				e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	private void sendEmail(String email)
    {
      
     
       System.out.println("ok");
       Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.free.fr");

        System.out.println("ok");
       Session session = Session.getInstance(props, null);
 
       try
       {
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress("easydelivery@support.com"));
           message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
           message.setSentDate(new Date());
           message.setSubject("Inscription Easy Delivery");
           
           message.setContent("<h2>Validation d'E-mail</h2>" + 
        	          "<h3>Veuillez Cliquer pour valider votre E-mail\n </h3> "+
        	          "<button onmouseover=\"this.style.background-color:red\" style=\"padding: 16px 32px; background-color: white ;color: black;  border: 2px solid #f44336\" >Validez votre E-mail</button>","text/html; charset=utf-8");
           System.out.println("Sending e-mail...");
           Transport.send(message);
           System.out.println("e-mail sent.");
       }
        catch(MessagingException me)
       {
           System.out.println("e-mail send failed.");
           me.getMessage();
       }
    }

}

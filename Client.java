import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.InputStreamReader;
import java .io.BufferedReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.ArrayList;;
import java.util.Enumeration;
import java.io.PrintWriter;

public class Client {
    public static void main(String[] args){

	ArrayList<String> ht_login;
	ArrayList<String> ht;
	//        Hashtable<String, String> ht_signup;
	Enumeration en ;
	int i = 0;
	try{
	    Socket s = new Socket("localhost",7090);
	    System.out.println("Connexion au Serveur");
       	    ht_login = new ArrayList<String>();
	    InputStream is = s.getInputStream();
	    OutputStream os = s.getOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(os);
	    ObjectInputStream oii = new ObjectInputStream(is);
	    InputStreamReader ois  = new InputStreamReader(is);	
	    BufferedReader bf = new BufferedReader(ois);
	    PrintWriter pw = new PrintWriter(os,true);
	    Scanner sc = new Scanner(System.in);	    //Scaner sc1 = new Scanner(System.in);
	    System.out.print(bf.readLine());
	    /*      	    System.out.print(" Login : ");
	    ht_login.add(sc.nextLine());
	    System.out.print("Password : ");
	    ht_login.add(sc.nextLine());
	    System.out.println(ht_login.size());
	    oos.writeObject(ht_login);
	    //	    en = ht_login.elements();
	    while(i<ht_login.size()){
		System.out.println(ht_login.get(i));
		i++;
	    }
	    while(true){
		String req = bf.readLine();
		System.out.println(req);
	    if(req.contains("true")){
	    pw.println("Session_ok");
	    }
	    if (req.contains("env_data_client")){
       	    ArrayList<String> al = (ArrayList<String>) oii.readObject();
       		    for (int ij = 0; ij < al.size(); ij++) {
       			System.out.println(al.get(ij));
       		    }
	    }
	    }
	    */
       	}catch (Exception e){
	    e.printStackTrace();
	}

    }
}
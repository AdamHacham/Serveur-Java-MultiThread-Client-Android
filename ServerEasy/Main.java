
public class Main {

    public static void main(String[] args) {
    
	String host = "127.0.0.1";
	int port = 2345;
      
	ServerEasy ts = new ServerEasy(host, port);
	ts.open();
      
	System.out.println("Serveur initialisé.");
      
	for(int i = 0; i < 5; i++){
	    Thread t = new Thread(new ClientConnexion(host, port));
	    t.start();
	}
    }
}
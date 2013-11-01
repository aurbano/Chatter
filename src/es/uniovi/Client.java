package es.uniovi;

import java.net.*;
import java.util.Random;

/**
 * Simulates a new Client connection
 * floods the server with messages.
 * Each Client starts its own sender
 */
public class Client extends Thread{
	Socket s;
	Sender sender;
	String salaBase;
	Random r;
	
	public Client(String host, int port){
		s = new Socket();
		try{
			sender = new Sender(s, host, port);
			r = new Random();
			salaBase = "s"+Long.toString(r.nextLong());
			this.start();
		}catch(Exception e){
			System.out.println("Host inalcanzable! Cliente no arrancado");
		}
	}
	
	public void run(){
		Command c;
		long offset = r.nextInt();
		c = new Command("/NICK "+"chevi"+Long.toString(offset));
		
		sender.send(new Command("/LIST"));
		
		sender.send(c);
		// Start giving the sender new commands
		while(true){
			c = new Command("/JOIN "+salaBase+Long.toString(offset));
			sender.send(c);
			offset++;
			if(!sender.isAlive()){
				System.out.println("Sender cerrado, cierro.");
				break;
			}
		}
		System.out.println("Cliente cerrado");
	}
}
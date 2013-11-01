package es.uniovi;

import java.io.*;
import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Sender extends Thread{
	
	private ArrayBlockingQueue<Command> outQueue;
	
	Socket s;
	
	public Sender(Socket s, String host, int port) throws UnknownHostException, IOException{
		this.s = new Socket(host, port);
		outQueue = new ArrayBlockingQueue<Command>(100);
		this.start();
	}
	
	public void send(Command cmd){
		try{
			outQueue.put(cmd);
		}catch(Exception e){
			System.out.println("Ignorando, cola llena");
		}
	}
	
	public void run(){
		int fails = 0;
		Command c;
		try{
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			while(true){
				try{
					c = outQueue.take();
					out.write(c.get());
				}catch(Exception e){
					fails++;
					if(fails > 10){
						System.out.println("Tumbado, cierro Sender");
						break;
					}
				}
			}
		}catch(IOException e){
			System.err.println("No Socket - Flooded");
		}
	}
}

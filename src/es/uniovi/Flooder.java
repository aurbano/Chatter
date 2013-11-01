package es.uniovi;

/**
 * Chat flooder
 */
public class Flooder {
	
	public static void main(String[] args){
		String host;
		int port;
		int clients;
		int iter = 0;
		try{
			host = args[0];
			port = new Integer(args[1]);
			clients = new Integer(args[2]);
			
			Client[] client = new Client[clients];
			
			System.out.println("Lanzando mensajes a "+host+":"+port);
			System.out.println("Utilizando "+clients+" clientes.");
			
			while(true){	
				System.out.println("Intento "+iter);
				if(iter>0){
					for(int i=0; i<clients; i++){
						client[i] = new Client(host, port);
					}
				}
				
				for(int i=0; i<clients; i++){
					if(iter>0){
						client[i].join();
						// Si ha acabado es que el server cayo, notifica a todos los clientes
						for(int a=i; a<clients; a++){
							client[a].interrupt();
						}
					}
					client[i] = new Client(host, port);
				}
				iter++;
			}
			
		}catch(Exception e){
			System.out.println("Define host y puerto");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}

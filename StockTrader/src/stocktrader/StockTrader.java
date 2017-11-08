package stocktrader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class StockTrader {
	
	protected PrintWriter out = null;
	protected BufferedReader in = null;
	protected Socket serverSocket;
	
	public StockTrader()
    {
		try {
			serverSocket = new Socket("192.168.1.103", 5000);
			out = new PrintWriter(serverSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StockTrader serverConnect = new StockTrader();
		System.out.println("Connection to server starting....");
        System.out.println("Server ip is: " + serverConnect.serverSocket.getRemoteSocketAddress() + "\n\n");
        serverConnect.out.println("HELO");
        
        try {
			System.out.println(serverConnect.in.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

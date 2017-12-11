package stocktrader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class StockTrader {
	
	protected PrintWriter out = null;
	protected BufferedReader stdIn = null;
	protected Socket toServerSocket;
	
	ClientRead myRead;
	 
	public StockTrader()
    {
		
		try
        {
            toServerSocket = new Socket("192.168.1.73", 5000);
            out = new PrintWriter(toServerSocket.getOutputStream(), true);

            stdIn = new BufferedReader(new InputStreamReader(System.in));

        }
        catch(IOException e)
        {
            System.out.println("Some Error: " + e);
        }

        myRead = new ClientRead(toServerSocket);
        
    }
	
	public void writeToServer()
    {
        String aString;
        try
        {
            aString = stdIn.readLine();
            out.println(aString);
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void StartThreads()
    {
        Thread t1 = new Thread(myRead);
        t1.start();
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StockTrader serverConnect = new StockTrader();
        serverConnect.StartThreads();
        
        while(true)
        {
            serverConnect.writeToServer();
        }

	
		
	}

}

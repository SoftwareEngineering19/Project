import java.net.*;
import java.io.*;

public class STSServer 
{

    protected ServerSocket STSSocket = null;

    public StockMarket mySM;

    private static STSServer ref = null;
    private String instName = "";


    private STSServer()
    {
    }

    public static STSServer getObject()
    {
        if (ref == null)
        {
            ref = new STSServer();
        }
        return ref;
    }

    public void initSTS()
    {
        System.out.println("StockMarket thread started.");
        Thread t1 = new Thread(mySM.getStockMarket());
        t1.start();
    }


    public void listenForClients()
    {
        try
        {
            STSSocket = new ServerSocket(5000);
            
            while(true)
            {
                System.out.println("Listening for connections from Client.\n");
                new ClientConnect(STSSocket.accept(), mySM.getStockMarket());
            }
        }
        catch(IOException e)
        {
            System.out.println("Error in setting up socket " + e);
            System.exit(1);
        }
    }

    public String getName()
    {
        return instName;
    }

    public void setName(String aName)
    {
        instName = aName;
    }

    public static void main(String [] args)
    {

        //STSServer mySTS = new STSServer();
        //Give a number and name(s)
        if (args.length > 0){
            if (args[0] != "0"){
                for (int i=0; i < Integer.parseInt(args[0]); i++){
                    //New command window
                    //start STSServer.java 0 i;
                    Runtime rt = Runtime.getRuntime();
                    try{
                        System.out.println("Trying");
                        Process pr = rt.exec("cmd.exe");
                    }
                    catch(IOException e){
                        System.out.println("Bad");
                        e.printStackTrace();
                    }
                
                }
            
            }
        }
        else{
            STSServer mySTS = STSServer.getObject();
            mySTS.initSTS();
            mySTS.listenForClients();
        }
    }
}
package sss5jsw;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import coinbase.coinbase;
import coinbase.fileIO;

public class socketServer implements Runnable
{
	   Socket csocket;
	   String ipString;
	   char threadType;

	   static Vector<String> vec = new Vector<String>(5);
	   
	   public static Hashtable<String, coinbase> clients = 
			     new Hashtable<String, coinbase>();
	   
	   static final String newline = "\n";
	   static int first_time = 1;
	   
	   static int port_num = 3333;
	   
	   static int numOfConnections = 0;
	   static int numOfMessages = 0;
	   static int max_connections = 5;
	   static int numOfTransactions = 0;
	   
	   static boolean found = false;
	   static String ipAddrOfSocketServer = null;

	   socketServer(Socket csocket, String ip)
	   {
	      this.csocket  = csocket;
	      this.ipString = ip;
	   } 
	   
	   static void displayInterfaceInformation(NetworkInterface netint) throws SocketException 
	    {	
	    	String dname = netint.getName();
	    	if (dname.startsWith("en") == true && found == false)
	    	{
	           Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
	           
	           int counter= 0;
	           for (InetAddress inetAddress : Collections.list(inetAddresses))
	           {   
	        	   String myAddr = inetAddress.toString();
	        	   myAddr = myAddr.replaceFirst("/", "");
	        	   
	        	   if (counter != 0)
	        	   {
	                    ipAddrOfSocketServer = myAddr;
	                    found = true;
	        	   }
	        	   
	        	   counter++;
	           }
	    	}
	   }
	   
	   
	   public static void runSockServer()   // throws Exception
	   {
	     boolean sessionDone = false;	  
	     ServerSocket ssock = null;
 
	     Enumeration<NetworkInterface> nets = null;
		 try 
		 {
			nets = NetworkInterface.getNetworkInterfaces();
		 }
		 catch (SocketException e3)
		 {
			// TODO Auto-generated catch block
			e3.printStackTrace(); 
		 }
		 
	     for (NetworkInterface netint : Collections.list(nets))
			try {
				displayInterfaceInformation(netint);
			} catch (SocketException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

	   
	     try
	     {
	       InetAddress addr = InetAddress.getByName(ipAddrOfSocketServer);
	       
		   ssock = new ServerSocket(port_num, 50, addr);
	     }
	     catch (BindException e)
	     {
		    e.printStackTrace();
	     }
	     catch (IOException e)
	     {
		    e.printStackTrace();
	     }
	 
	     // update the status text area to show progress of program
		 sss5jsw.wrToCenterTA("IP Address : " + ipAddrOfSocketServer + newline);
		     
		 sss5jsw.left.setText(ipAddrOfSocketServer + newline);
		 sss5jsw.left.append(Integer.toString(port_num));
	 
	     sss5jsw.wrToCenterTA("Listening on port " + port_num + newline);
	 
	     //
	     // initialize the hash table to the following keys or if file hash table data exists, then use it
	     //
	     try
         {
     	      File f = new File("hashTableData.txt");
     	      if (f.exists())
     	      {
                 FileReader reader = new FileReader("hashTableData.txt");
                 BufferedReader br = new BufferedReader(reader);
               
                 String line = br.readLine();
                 while (line != null)
                 {
                	String args[]   = line.split("\\,");
                	
                	String key     =                  args[0];
                	int transCount = Integer.parseInt(args[1]);
                	int items      = Integer.parseInt(args[2]);
                	double dollars = Double.parseDouble(args[3]);
                	int numApples  = Integer.parseInt(args[4]);
                	int numGrapes  = Integer.parseInt(args[5]);
                	int numOranges = Integer.parseInt(args[6]);
                	int numPears   = Integer.parseInt(args[7]);
                	
 					clients.put(key, new coinbase(
 				                           ));
 					
                 	line = br.readLine();
                 }
                 
                 br.close();
              
                 int currentSize     = clients.size();
         	     sss5jsw.right.setText("Total# : " + currentSize + newline);
         	    
         	     clients.put("totalKiosk", new coinbase());
     	      }
     	      else
     	      {
     	    	 //
     	    	 // add these default kiosk names to start out with
     	    	 //
     		     //clients.put("kiosk#001", new coinbase("kiosk#001", 0, 0, 0.0, 0, 0, 0, 0));
     		     //clients.put("kiosk#002", new coinbase("kiosk#002", 0, 0, 0.0, 0, 0, 0, 0));
     		     //clients.put("kiosk#003", new kiosk("kiosk#003", 0, 0, 0.0, 0, 0, 0, 0));
     		     
     		     
     		     //
                 // add homemade key, "totalKiosk", to HASHTABLE data structure
                 //
                 int    transCount=0, ticketCount=0, numApples=0, numGrapes=0, numOranges=0, numPears=0;
                 double dollarCount=0.0;
                 
                //  List<String> v = new ArrayList<String>(clients.keySet());
         	    //  for (String key : v)
        	    //  {
        	    //     transCount  = transCount  + clients.get(key).getTransactions();
        	    //     ticketCount = ticketCount + clients.get(key).getItems();
        	    //     dollarCount = dollarCount + clients.get(key).getDollars();
        	    //     numApples   = numApples   + clients.get(key).getApples();
        	    //     numGrapes   = numGrapes   + clients.get(key).getGrapes();
        	    //     numOranges  = numOranges  + clients.get(key).getOranges();
        	    //     numPears    = numPears    + clients.get(key).getPears();
        	    //  }        
         	     
         	     
         	     int currentSize     = clients.size();
        	     sss5jsw.right.setText("Total# : " + currentSize + newline);
        	     
         	     clients.put("totalKiosk", new coinbase());   
     	      }
     	 }
         catch(Exception e2)
         {   
     	    e2.printStackTrace(); 
         }		

	     
	     
	     sessionDone = false;
	     while (sessionDone == false)
	     {
	        Socket sock = null;
		    try
		    {
		       //	
  	     	   // blocking system call
		       //	
			   sock = ssock.accept();
		    }
		    catch (IOException e)
		    {
			   e.printStackTrace();
		    }
		 
		    // update the status text area to show progress of program
	        sss5jsw.wrToCenterTA("Client Connected : " + sock.getInetAddress() + newline);
	        
	        //
	        // start a NEW THREAD process
	        //
	        new Thread(new socketServer(sock, sock.getInetAddress().toString())).start();
	     }
	 
	     try 
	     {
		    ssock.close();
	     }
	     catch (IOException e) 
	     {
		    e.printStackTrace();
	     }
	}	  

	   
	static synchronized void hashOperation(char type, String key, String items, String d, String nApples, String nGrapes, String nOranges, String nPears)
	{
		switch (type)
		{
			case 'T':
				long start = System.nanoTime();
				
				if (clients.containsKey(key) == true)
		        {
					// clients.get(key).incrementTrans();
					// clients.get(key).addItems(Integer.parseInt(items));
					// clients.get(key).addDollars(Double.parseDouble(d));
					// clients.get(key).addApples(Integer.parseInt(nApples));
					// clients.get(key).addGrapes(Integer.parseInt(nGrapes));
					// clients.get(key).addOranges(Integer.parseInt(nOranges));
					// clients.get(key).addPears(Integer.parseInt(nPears));
					
					long finish = System.nanoTime();
					
					long timeElapsed = finish - start;
					sss5jsw.right.append(" Time Nano-Seconds : " + timeElapsed + newline);
					sss5jsw.right.append("Time Milli-Seconds : " + timeElapsed / 1000000 + newline);
					
					if (clients.containsKey("totalKiosk") == true)
					{
						// clients.get("totalKiosk").incrementTrans();
						// clients.get("totalKiosk").addItems(Integer.parseInt(items));
						// clients.get("totalKiosk").addDollars(Double.parseDouble(d));
						// clients.get("totalKiosk").addApples(Integer.parseInt(nApples));
						// clients.get("totalKiosk").addGrapes(Integer.parseInt(nGrapes));
						// clients.get("totalKiosk").addOranges(Integer.parseInt(nOranges));
						// clients.get("totalKiosk").addPears(Integer.parseInt(nPears));
					}
		        }
				else
				{
					clients.put(key, new coinbase());      // create a new key right now and add it to the hash table!
					
					// clients.get(key).incrementTrans();
					// clients.get(key).addItems(Integer.parseInt(items));
					// clients.get(key).addDollars(Double.parseDouble(d));
					// clients.get(key).addApples(Integer.parseInt(nApples));
					// clients.get(key).addGrapes(Integer.parseInt(nGrapes));
					// clients.get(key).addOranges(Integer.parseInt(nOranges));
					// clients.get(key).addPears(Integer.parseInt(nPears));
					
					long finish = System.nanoTime();
					
					long timeElapsed = finish - start;
					sss5jsw.right.append(" Time Nano-Seconds : " + timeElapsed + newline);
					sss5jsw.right.append("Time Milli-Seconds : " + timeElapsed / 1000000 + newline);
					
					if (clients.containsKey("totalKiosk") == true)
					{
						// clients.get("totalKiosk").incrementTrans();
						// clients.get("totalKiosk").addItems(Integer.parseInt(items));
						// clients.get("totalKiosk").addDollars(Double.parseDouble(d));
						// clients.get("totalKiosk").addApples(Integer.parseInt(nApples));
						// clients.get("totalKiosk").addGrapes(Integer.parseInt(nGrapes));
						// clients.get("totalKiosk").addOranges(Integer.parseInt(nOranges));
						// clients.get("totalKiosk").addPears(Integer.parseInt(nPears));
					}
				}
			break;
			
			
			case 'S':
				
				
				
		    break;
		}
	}

	//
	// add a new KIOSK entry and number to the hash table
	//
	public static void createNewKiosk()
	{
		int nextKioskNumber, currentSize = 0;
		String kioskString;
		
		currentSize     = clients.size();
		nextKioskNumber = currentSize + 1;
		
		nextKioskNumber = nextKioskNumber - 1;
		kioskString     = "kiosk#" + String.format("%03d", nextKioskNumber);
				
		clients.put(kioskString, new coinbase());
	}

	//
	// method to write out hash table data
	//
	public static void writeHashTableData()
	{
		FileWriter fwg = null;
        try 
        {
        	// open the file in append write mode
        	fwg = new FileWriter("hashTableData.txt");
        }
        catch (IOException e)
        {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
   	    
        List<String> v = new ArrayList<String>(clients.keySet());
		//in	    Collections.sort(v);
	    
        BufferedWriter bwg = new BufferedWriter(fwg);
        PrintWriter outg   = new PrintWriter(bwg);
		
	    for (String key : v)
	    {
	    	if (key.equals("totalKiosk") == true)
	    		continue;
	    	
	        //outg.println(clients.get(key).forFileOutput());
	    }
	        
        outg.close();
	}
	
	
	//
	// get all transaction data from the hash table keys
	//
	public static String getAllTransactions()
	{
		String rs="";
		
		List<String> v = new ArrayList<String>(clients.keySet());
	    Collections.sort(v);
		
	    for (String str : v)
	    {
	    	//
	    	// add a line feed before the total KIOSK data
	    	//
	    	if (str == "totalKiosk")
	    		rs = rs + "\r\n";
	    
	        rs = rs + clients.get(str.toString()) + "\r\n";
	    }
				
		return rs;
	}

	
	//
	// CLIENT THREAD CODE - This is the thread code that ALL Internet clients will run()
	//
	public void run()
	{
	   long threadId = 0;
	   
	   try
	   {
		  int watchDog = 0;
		  boolean session_done = false;
	      String clientString;
	      String keyString = "";
	    
	      threadId = Thread.currentThread().getId();
	      
	      numOfConnections++;
	      
	      sss5jsw.wrToCenterTA("Num of Connections = " + numOfConnections + newline);
	      
	      keyString = ipString + ":" + threadId;
	      
	      //
	      // write IP address of the client who just connected to the main screen
	      //
	      if (vec.contains(keyString) == false)
	      {
	    	    int counter = 0;
	        	vec.addElement(keyString);
	        	
	        	sss5jsw.bottom.setText("");
	        	Enumeration<String> en = vec.elements();
	        	while (en.hasMoreElements())
	        	{
	        		sss5jsw.bottom.append(en.nextElement() + " || ");
	        		
	        		if (++counter >= 6)
	        		{
	        			sss5jsw.bottom.append("\r\n");
	        			counter = 0;
	        		}
	        	}
	      }
	       
	      PrintStream pstream = new PrintStream (csocket.getOutputStream());
	      BufferedReader rstream = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
	       
	      while (session_done == false)
	      {
	          if (rstream.ready())   // check for any data messages
	          {
	              clientString = rstream.readLine();

	              watchDog = 0;             // reset the watch counter back to zero
	              
	              //
	              // write to transaction log
	              //
	              fileIO transLog = new fileIO();
	              transLog.wrTransactionData("SERVER : " + clientString);
	              
	              	              
	              // update the status text area to show progress of program
	   	           sss5jsw.wrToCenterTA("RECV : " + clientString + newline);
	     	       
	     	       // update the status text area to show progress of program
	     	      // sss5jsw.wrToCenterTA("RLEN : " + clientString.length() + newline);
	              
	              if (clientString.length() > 128)
	              {
	           	   session_done = true;
	           	   continue;
	              }

	              if (clientString.contains("quit"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("QUIT"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("Quit"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("Query>"))
	              {
	            	  String tokens[] = clientString.split("\\>");
	            	  
	            	  if (clients.containsKey(tokens[1]) == true)
	            	  {
	            		  pstream.println(clients.get(tokens[1]).toString());  
	            	  }
	            	  else
	            	  {
	            		  pstream.println("NACK : ERROR : No such kiosk number!");
	            	  }
	              }
	              else if (clientString.contains("Transaction>"))
	              {
	            	  String tokens[] = clientString.split("\\>");
	            	  String args[]   = tokens[1].split("\\,");
	            	  
	            	  if (clients.containsKey(args[0]) == true)
	            	  {
	            		  hashOperation('T', args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
	            		  pstream.println("ACK");
	            	  }
	            	  else
	            	  {
                          hashOperation('T', args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
	            		  pstream.println("ACK");
	            		  
	            		  transLog.wrTransactionData(" ***** ADDING New Key to the HASH TABLE! : " + args[0]);
	            	  }
	              }
	              else if (clientString.contains("Configure>"))
	              {
	            	  String tokens[] = clientString.split("\\>");
	            	  
	            	  if (tokens.length == 2)
	            	  {
	            	     //clients.put(tokens[1], new coinbase(tokens[1], 0, 0, 0.0, 0, 0, 0, 0));
	            	     
	            	     pstream.println("ACK");
	            	  }
	            	  else
	            	  {
	            		  pstream.println("NACK : ERROR : Invalid number of parameters!");
	            	  }
	              }
				  else if (clientString.contains("LOGIN>"))
	              {
	            	  String emailData = ""; 
	            	  sss5jsw.wrToCenterTA("IDKs = " + emailData + newline);
	              }
	              else if (clientString.contains("Date>"))
	              {
	            	numOfMessages++;
	            	  
	            	// Create an instance of SimpleDateFormat used for formatting 
	            	// the string representation of date (month/day/year)
	            	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	            	// Get the date today using Calendar object.
	            	Date today = Calendar.getInstance().getTime();
	            	
	            	// Using DateFormat format method we can create a string 
	            	// representation of a date with the defined format.
	            	String reportDate = df.format(today);

	            	//
	            	// Print what date is today! Send to the individual THREAD
	            	//
	            	pstream.println("Num Of Messages : " + numOfMessages + "   Simple Date: " + reportDate);
	              }
	              else if (clientString.contains("Stock>"))
	              {
	            	  String tokens[] = clientString.split("\\>");
	            	  
	            	//   if (tokens.length == 2)
	            	//   {
	            	// 	 //String iexString = urlIEXData.getIEXRawData(tokens[1].trim());
	            	//      pstream.println(iexString);
	            	//   }
	            	//   else
	            	//   {
	            	// 	  pstream.println("NACK : ERROR : Invalid stock command!");
	            	//   }
	              }
	              else
	              {
	            	  pstream.println("NACK : ERROR : No such command!");
	              }
	       	   }
	         			    		        	
	           Thread.sleep(500);

	           if (++watchDog >= 40)     // if not messages by this client in 40 seconds, then close connection
	           {
	        	   watchDog = 0;
	        	   session_done = true;
	           }
	        }    // end WHILE LOOP - session_done
	      
	      
	      
	
            keyString = ipString + ":" + threadId;
	      
	        if (vec.contains(keyString) == true)
	        {
	        	int counter = 0;
	        	vec.removeElement(keyString);
	        	
	        	sss5jsw.bottom.setText("");
	        	Enumeration<String> en = vec.elements();
	        	while (en.hasMoreElements())
	        	{        		     		
                    sss5jsw.bottom.append(en.nextElement() + " || ");
	        		
	        		if (++counter >= 6)
	        		{
	        			sss5jsw.bottom.append("\r\n");
	        			counter = 0;
	        		}
	        	}

  	            sss5jsw.bottom.repaint();
	        }
	      
	        numOfConnections--;

	        // close client socket
	        csocket.close();
	       
	        // update the status text area to show progress of program
		     sss5jsw.wrToCenterTA("Child Thread : " + threadId + " : is Exiting!!!" + newline);
		     sss5jsw.wrToCenterTA("Num of Connections = " + numOfConnections + newline);
		     
		     return;
	     } // end try  
	 
	     catch (SocketException e)
	     {
		  // update the status text area to show progress of program
	      sss5jsw.wrToCenterTA("ERROR : Socket Exception!" + newline);
	     }
	     catch (InterruptedException e)
	     {
		  // update the status text area to show progress of program
	      sss5jsw.wrToCenterTA("ERROR : Interrupted Exception!" + newline);
	     }
	     catch (UnknownHostException e)
	     {
		  // update the status text area to show progress of program
	      sss5jsw.wrToCenterTA("ERROR : Unkonw Host Exception" + newline);
	     }
	     catch (IOException e) 
	     {
	     // update the status text area to show progress of program
	      sss5jsw.wrToCenterTA("ERROR : IO Exception!" + newline);
	     }     
	     catch (Exception e)
	     { 
		  numOfConnections--;
		  
		  // update the status text area to show progress of program
	      sss5jsw.wrToCenterTA("ERROR : Generic Exception!" + newline);
	     }
	   
         String keyString = ipString + ":" + threadId;
	      
         if (vec.contains(keyString) == true)
         {
        	int counter = 0;
        	vec.removeElement(keyString);
        	
        	sss5jsw.bottom.setText("");
        	Enumeration<String> en = vec.elements();
        	while (en.hasMoreElements())
        	{        		     		
                sss5jsw.bottom.append(en.nextElement() + " || ");
        		
        		if (++counter >= 6)
        		{
        			sss5jsw.bottom.append("\r\n");
        			counter = 0;
        		}
        	}
	            sss5jsw.bottom.repaint();
         }
      
         if (numOfConnections > 0)
            numOfConnections--;

         // close client socket
         try 
         { 
			csocket.close();
		 } 
         catch (IOException e)
         {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
       
         // update the status text area to show progress of program
	     sss5jsw.wrToCenterTA("Num of Connections = " + numOfConnections + newline);
	     
	  }  // end run() thread method
}

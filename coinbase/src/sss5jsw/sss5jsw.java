package sss5jsw;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class sss5jsw extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// global variables
	public static JTextArea top;
	public static JTextArea left;
	public static JTextArea center;
	public static JTextArea right;
	public static JTextArea bottom;

	public static int numOfCenterLines = 0;
	//
	// main method starts here
	//
	public static void main(String[] args)
	{
		sss5jsw frame = new sss5jsw();
		frame.setVisible(true);
	}

	
	public static void wrToCenterTA(String s)
	{
		if (numOfCenterLines == 0)
		{
			sss5jsw.center.setText("");
			sss5jsw.center.setText(s);
		}
		else
		{
			sss5jsw.center.append(s);;
		}
		
		numOfCenterLines++;
		if (numOfCenterLines >= 27)
			numOfCenterLines=0;
	}
	
	//
	// constructor
	//
	public sss5jsw()
	{	
		String titleString = "Simple Socket Server JAVA SWING Rev 5.0   :   Rel Date  :  Mar. 30, 2022   11:00 AM     ";
		setTitle(titleString);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//
		// set the Frame size
		//
		setSize(1020, 700);
		
		// 
		// panel title
		//
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new TitledBorder(new EtchedBorder(), "Simple Socket Server Rev.5 JAVA Swing"));
		
		
		setContentPane(contentPane);
		
		
		//
		// TOP - available text area - has the real-time clock display for now
		//
		top = new JTextArea();
		top.setEditable(false);
		top.setBounds(15, 20, 975, 90);
		top.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		top.setBackground(Color.WHITE);
		contentPane.add(top);
		
		//
		// LEFT - available text area
		//
		left = new JTextArea();
		left.setEditable(false);
		left.setBounds(15, 120, 200, 450);
		left.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		left.setBackground(Color.WHITE);
		contentPane.add(left);
		
		
		//
		// main area for socket server to display messages
		//
		center = new JTextArea();
		center.setEditable(false);
		center.setBounds(225, 120, 600, 450);
		center.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		center.setBackground(Color.WHITE);
		contentPane.add(center);
	
		
		//
		// RIGHT - available text
		//
		right = new JTextArea();
		right.setEditable(false);
		right.setBounds(840, 120, 145, 450);
		right.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		right.setBackground(Color.WHITE);
		contentPane.add(right);
		
		
		//
		// BOTTOM - available text area
		//
		bottom = new JTextArea();
		bottom.setEditable(false);
		bottom.setBounds(15, 580, 975, 35);
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		bottom.setBackground(Color.WHITE);
		contentPane.add(bottom);
		
		
		//
		// define all BUTTONS
		//
		JButton exitButton = new JButton("EXIT");
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		exitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int result = JOptionPane.showConfirmDialog(null,
						     "Do you really want to exit the Socket Server?",
						     "Socket Server Backend",
						     JOptionPane.INFORMATION_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION)
				{
					socketServer.writeHashTableData();
					
					dispose();
					System.exit(0);
				}		
			}
		});
		exitButton.setBounds(4, 620, 133, 30);;
		contentPane.add(exitButton);
		
		
		
		// 
		// List Kiosk Button
		//
		JButton listKiosks = new JButton("LIST KIOSKS");
		listKiosks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		listKiosks.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(null, 
		                   socketServer.getAllTransactions(),
		                   "Food Truck",
		                   JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		listKiosks.setBounds(150, 620, 133, 30);;
		contentPane.add(listKiosks);
		
		
		
		
		
		//JButton clients = new JButton("Clients");
		//clients.setOnAction(new EventHandler<ActionEvent>()
		//{
		//	@Override
		// 	public void handle(ActionEvent e)
		// 	{
		//
		//	}
		//});
		
		
		
		//JButton showLog = new JButton("Show Log");
		//showLog.setOnAction(new EventHandler<ActionEvent>()
		//{
		//	@Override
		// 	public void handle(ActionEvent e)
		// 	{
		//		Platform.runLater(new Runnable() 
		//		 {
		//			    String logString = "";
		//			    
		//		        public void run() 
		//		        {
		//		        	try
		//		            {
		//		        	      File f = new File("transactionLog.txt");
		//		        	      if (f.exists())
		//		        	      {
		//		                    FileReader reader = new FileReader("transactionLog.txt");
		//		                    BufferedReader br = new BufferedReader(reader);
		//		                  
		//		                    String line = br.readLine();
		//		                    while (line != null)
		//		                    {
		//		                    	logString = logString + line + "\r\n";
		//		                    	line = br.readLine();
		//		                    }
		//		                    
		//		                    br.close();
		//		        	      }
		//		        	      else
		//		        	      {
		//		        	    	  logString = "No log File Found!";
		//		        	      }
		//		        	 }
		//		             catch(Exception e2)
		//		             {   
		//		        	    e2.printStackTrace(); 
		//		             }		
		//		        	
		//		             Alert alert = new Alert(Alert.AlertType.INFORMATION);
		//		             alert.setTitle("--- Ticket Kiosk ---");
		//		             alert.setHeaderText("Transaction Log File");
		//		          
		//		             alert.setContentText(logString);
		//		             alert.setWidth(300);
		//		             alert.setHeight(600);
		//		             alert.showAndWait();
		//		        }
		//		    });	
		//	}
		//});
		
		
		
		
		//JButton summary = new JButton("Summary");
		//summary.setOnAction(new EventHandler<ActionEvent>()
		//{
		//	@Override
		//	public void handle(ActionEvent e)
		// 	{
		//
		//	}
		//});
		
		
		
		
		
		//JButton newKiosk = new JButton("New Kiosk");
		//newKiosk.setOnAction(new EventHandler<ActionEvent>()
		//{
		//	@Override
		//	public void handle(ActionEvent e)
		// 	{
		//		Platform.runLater(new Runnable() 
		//		 {
		//		        public void run() 
		//		        {
		//		          sockServer.createNewKiosk();
		//		          
		//		          Alert alert = new Alert(Alert.AlertType.INFORMATION);
		//		          alert.setTitle("--- Ticket Kiosk ---");
		//		          alert.setHeaderText("Total Number of Transactions");
		//		          
		//		          alert.setContentText(sockServer.getAllTransactions());
		//		          
		//		          alert.showAndWait();
		//		        }
		//		    });	
		//	}
		//});
		
		
		
		//JButton query1 = new JButton("Query #1");
		//query1.setOnAction(new EventHandler<ActionEvent>()
		//{
		//	@Override
		// 	public void handle(ActionEvent e)
		// 	{
		//
		//	}
		//});
		
		
		
		
		//JButton query2 = new JButton("Query #2");
		//query2.setOnAction(new EventHandler<ActionEvent>()
		//{
		//	@Override
		// 	public void handle(ActionEvent e)
		// 	{
		//
		//	}
		//});
		
		
		
		

	
		
		
		//JButton helpButton = new JButton("HELP");
		//helpButton.setOnAction(new EventHandler<ActionEvent>()
		//{
		//	@Override
		// 	public void handle(ActionEvent e)
		// 	{
		//		 Platform.runLater(new Runnable() 
		//		 {
		//		        public void run() 
		//		        {
		//		          Alert alert = new Alert(Alert.AlertType.INFORMATION);
		//		          alert.setTitle("--- Ticket Kiosk Help Window ---");
		//		          alert.setHeaderText("Help Screen");
		//		          
		//		          String hStr="- Click on   EXIT   button to quit the socket server.\r\n" + 
		//		        		      "- Click on   Show Log   to display current transaction log file.\r\n" +
		//		        		      "- Click on   New Kiosk   to create the next ticket kiosk station.\r\n" +
		//		                      "- Click on   LIST KIOSKS to display current status of kiosks.\r\n";
		//		          
		//		          alert.setContentText(hStr);
		//		          alert.showAndWait();
		//		        }
		//		    });
		//	}
		//});		
		
		
		
		// start all threads  for the GUI screen here
		startRealTimeClock();
		
		
		
		
		
		// start the socket server thread - will start to accept client connections
		startSockServer();
		
		
		
		
		
		//
		// lights, camera, action
		//
		contentPane.setLayout(null);
		
		this.setLocationRelativeTo(null);
	}

	
  /*
   * Thread to update weather info for NYC and Boston    
   */     
  private void startSockServer()
  {	
	 Thread refreshWeatherThread = new Thread()
	 {
	    public void run()
		  { 	
			  socketServer.runSockServer();
	      }
	 };

    refreshWeatherThread.start();
  }
	
  
  /*
   * Thread to update real-time clock
   */     
  private void startRealTimeClock()
  {	
	   Thread refreshClock = new Thread()
	   {
		  public void run()
		  {  
			 while (true)
			 {	 			      
				   Date   date = new Date();
				   String str = String.format("\n    %tc", date);
					 
				   top.setText("");
				   top.setText(str);
				   
			    	try
				    {
					   sleep(5000L);
				    }
				    catch (InterruptedException e)
				   {
					   // TODO Auto-generated catch block
					  e.printStackTrace();
				   }
             } // end while true 
	     }
	  };

    refreshClock.start();
  }
}
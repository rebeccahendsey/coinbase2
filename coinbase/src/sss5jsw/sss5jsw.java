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
import javax.swing.JLabel;

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
		setBackground(Color.WHITE);	
		String titleString = "COINBASE     ";
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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new TitledBorder(new EtchedBorder(), "TRANSACTIONS"));
		
		
		setContentPane(contentPane);
		
		
		//
		// TOP - available text area - has the real-time clock display for now
		//
		top = new JTextArea();
		top.setEditable(false);
		top.setBounds(712, 16, 279, 34);
		top.setBorder(null);
		top.setBackground(Color.WHITE);
		contentPane.add(top);
		
		//
		// LEFT - available text area
		//
		left = new JTextArea();
		left.setEditable(false);
		left.setBounds(15, 62, 200, 90);
		left.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		left.setBackground(Color.BLUE);
		contentPane.add(left);
		
		
		//
		// main area for socket server to display messages
		//
		center = new JTextArea();
		center.setEditable(false);
		center.setBounds(225, 62, 765, 90);
		center.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		center.setBackground(Color.BLUE);
		contentPane.add(center);
		
		
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
		exitButton.setBounds(6, 642, 133, 30);;
		contentPane.add(exitButton);;
		
		
		
		
		
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
		
		JTextArea center_1 = new JTextArea();
		center_1.setEditable(false);
		center_1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		center_1.setBackground(Color.BLUE);
		center_1.setBounds(13, 195, 975, 312);
		contentPane.add(center_1);
		
		JTextArea left_1 = new JTextArea();
		left_1.setEditable(false);
		left_1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		left_1.setBackground(Color.BLUE);
		left_1.setBounds(15, 540, 200, 90);
		contentPane.add(left_1);
		
		JTextArea left_1_1 = new JTextArea();
		left_1_1.setEditable(false);
		left_1_1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		left_1_1.setBackground(Color.BLUE);
		left_1_1.setBounds(269, 540, 200, 90);
		contentPane.add(left_1_1);
		
		JTextArea left_1_2 = new JTextArea();
		left_1_2.setEditable(false);
		left_1_2.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		left_1_2.setBackground(Color.BLUE);
		left_1_2.setBounds(531, 540, 200, 90);
		contentPane.add(left_1_2);
		
		JTextArea left_1_3 = new JTextArea();
		left_1_3.setEditable(false);
		left_1_3.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		left_1_3.setBackground(Color.BLUE);
		left_1_3.setBounds(791, 540, 200, 90);
		contentPane.add(left_1_3);
		
		JLabel lastNameLabel = new JLabel("LAST NAME");
		lastNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lastNameLabel.setBounds(25, 164, 90, 26);
		contentPane.add(lastNameLabel);
		
		JLabel lblFirstName = new JLabel("FIRST NAME");
		lblFirstName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblFirstName.setBounds(202, 164, 101, 26);
		contentPane.add(lblFirstName);
		
		JLabel lblType = new JLabel("TYPE");
		lblType.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblType.setBounds(494, 164, 60, 26);
		contentPane.add(lblType);
		
		JLabel lblAction = new JLabel("ACTION");
		lblAction.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAction.setBounds(636, 164, 79, 26);
		contentPane.add(lblAction);
		
		JLabel lblAmount = new JLabel("AMOUNT");
		lblAmount.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAmount.setBounds(766, 164, 79, 26);
		contentPane.add(lblAmount);
		
		JLabel lblNewLabel = new JLabel("BITCOIN");
		lblNewLabel.setBounds(76, 519, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblEthereum = new JLabel("ETHEREUM");
		lblEthereum.setBounds(327, 519, 90, 16);
		contentPane.add(lblEthereum);
		
		JLabel lblDogecoin = new JLabel("DOGECOIN");
		lblDogecoin.setBounds(584, 519, 90, 16);
		contentPane.add(lblDogecoin);
		
		JLabel lblLitecoin = new JLabel("LITECOIN");
		lblLitecoin.setBounds(859, 519, 90, 16);
		contentPane.add(lblLitecoin);
		
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
package coinbase;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import coinbase.BuyAndSell; 


public class coinbase extends JFrame
{
        // Launch the application
        public static void main(String[] args)
        {
            
        	coinbase frame = new coinbase();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setBackground(new Color(255, 255, 255));
            frame.setVisible(true);
            //Ping the server
            socketUtils sock = new socketUtils();
            sock.socketConnect();
            sock.sendMessage("Hello from coinbase");
            sock.closeSocket();
             
        }

        // Create the frame
        public coinbase()
        {
        	
            // Frame title display current time
            Date  date = new Date();
            String str = String.format("%tc", date);
        
            String titleString = "--- Coinbase --- " + str;

            setTitle(titleString);
            
            NumberFormat formatter = new DecimalFormat("#0.00");
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Size of the frame
            setSize(1000,1000);
            
            // Panel title
            JPanel contentPane = new JPanel();
            contentPane.setBackground(UIManager.getColor("Button.highlight"));
            contentPane.setForeground(Color.BLACK);
            contentPane.setBorder(new TitledBorder(new EtchedBorder(),
                                  "coinbase"));;
            setContentPane(contentPane);

            // Border around textFields for log in and create an account
            Border border = BorderFactory.createLineBorder(new Color(188, 188, 188));

            // Border for textArea backgrounds
            Border border2 = BorderFactory.createLineBorder(new Color(188, 188, 188),2);
            contentPane.setLayout(null);

           
            // Log in section
            
            // Email text field
            JTextField EmailTA = new JTextField("");
            EmailTA.setBounds(144, 225, 200, 30);
            contentPane.add(EmailTA);
            EmailTA.setBorder(border);
            EmailTA.setVisible(true);
            EmailTA.setForeground(new Color(188, 188, 188));
            EmailTA.setHorizontalAlignment(JTextField.CENTER);
        
            // Password text field
            JPasswordField passTA = new JPasswordField(""); 
            //JTextField passTA = new JTextField("");
            passTA.setBounds(144, 288, 200, 30);
            contentPane.add(passTA);
            passTA.setBorder(border);
            passTA.setForeground(new Color(188, 188, 188));
            passTA.setEchoChar('*');
            passTA.setHorizontalAlignment(JTextField.CENTER);

            // Email label
            JLabel emailL = new JLabel("Email");
            emailL.setBounds(144, 201, 200, 30);
            contentPane.add(emailL);
            emailL.setFont(new Font("Tahoma", Font.PLAIN, 10));

            // Password label
            JLabel passL = new JLabel("Password");
            passL.setBounds(144, 267, 200, 30);
            contentPane.add(passL);
            passL.setFont(new Font("Tahoma", Font.PLAIN, 10));
                
    

            // Extra information text area
            JTextArea ExtraInfo1 = new JTextArea ("Join 68+ million people on Coinbase\nBuy, sell, and manage your crypto on the world’s most trusted crypto exchange\n   * 68+ million customers   \n   * 100+ supported countries   ");
            ExtraInfo1.setBounds(46, 532, 481, 146);
            contentPane.add(ExtraInfo1);
            ExtraInfo1.setBackground(new Color(0, 0, 0));
            ExtraInfo1.setEditable(false);
            ExtraInfo1.setFont(new Font("Tahoma", Font.PLAIN, 20));
            ExtraInfo1.setLineWrap(true);
            ExtraInfo1.setWrapStyleWord(true);
            ExtraInfo1.setBorder(null);
            ExtraInfo1.setOpaque(false);
            ExtraInfo1.setForeground(UIManager.getColor("Button.darkShadow"));

            // Log in button
            JButton btnNewButton = new JButton("Log in");
            btnNewButton.setBackground(Color.BLUE);
            btnNewButton.setBounds(144, 332, 200, 30);
            contentPane.add(btnNewButton);
            btnNewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0)
            {
                String emailFieldStr = EmailTA.getText();
                emailFieldStr        = emailFieldStr.trim();
                if (emailFieldStr == "" || emailFieldStr == null || emailFieldStr.length() == 0)
                {
                    JOptionPane.showMessageDialog(null,
                                    "Please enter a valid email address.");
                    
                    return;
                }
                char[] passwordFieldChar = passTA.getPassword();

                if (passwordFieldChar == null || passwordFieldChar.length == 0)
                {
                    JOptionPane.showMessageDialog(null,
                                    "Please enter your password.");
                    
                    return;
                }
                String dataStr = null;
                dataStr="User : " + emailFieldStr;
     
                fileIO fio = new fileIO("users.txt");
                fio.wrTransactionData(dataStr);
                
                contentPane.setVisible(false); 
                
                homeScreen bs; // Reference to the class BuyAndSell
                bs = new homeScreen(); // Creates the object of class BuyAndSell
                bs.setVisible(true);
                
                //Ping the server
                socketUtils sock = new socketUtils();
                sock.socketConnect();
                sock.sendMessage(emailFieldStr);
                sock.closeSocket();
                
            }
        });
        contentPane.add(btnNewButton);
        

            
            // Sign up section
        
            // First name label
            JLabel FnameL = new JLabel("First name");
            FnameL.setBounds(650, 225, 90, 30);
            contentPane.add(FnameL);
            FnameL.setBorder(null);
            FnameL.setFont(new Font("Tahoma", Font.PLAIN, 10));
            
            // Last name label
            JLabel LnameL = new JLabel("Last name");
            LnameL.setBounds(760, 225, 90, 30);
            contentPane.add(LnameL);
            LnameL.setBorder(null);
            LnameL.setFont(new Font("Tahoma", Font.PLAIN, 10));

            // First name test field
            JTextField FnameTA = new JTextField("");
            FnameTA.setBounds(650, 250, 90, 30);
            contentPane.add(FnameTA);
            FnameTA.setBorder(border);
            FnameTA.setForeground(new Color(188, 188, 188));
            FnameTA.setHorizontalAlignment(JTextField.CENTER);

            // Last name text field
            JTextField LnameTA = new JTextField("");
            LnameTA.setBounds(760, 250, 90, 30);
            contentPane.add(LnameTA);
            LnameTA.setBorder(border);
            LnameTA.setForeground(new Color(188, 188, 188));
            LnameTA.setHorizontalAlignment(JTextField.CENTER);
            
            // Email text field for create account
            JTextField EmailTA2 = new JTextField("");
            EmailTA2.setBounds(650, 310, 200, 30);
            contentPane.add(EmailTA2);
            EmailTA2.setBorder(border);
            EmailTA2.setForeground(new Color(188, 188, 188));
            EmailTA2.setHorizontalAlignment(JTextField.CENTER);

            // Email label for create account
            JLabel EmailL = new JLabel("Email");
            EmailL.setBounds(650, 285, 90, 30);
            contentPane.add(EmailL);
            EmailL.setBorder(null);
            EmailL.setFont(new Font("Tahoma", Font.PLAIN, 10));

            // Password text field for create account
            JPasswordField passTA2 = new JPasswordField("");
           // JTextField passTA2 = new JTextField("Choose password");
            passTA2.setBounds(650, 370, 200, 30);
            contentPane.add(passTA2);
            passTA2.setBorder(border);
            passTA2.setEchoChar('*');
            passTA2.setForeground(new Color(188, 188, 188));
            passTA2.setHorizontalAlignment(JTextField.CENTER);

            // Password label for create account
            JLabel PassL = new JLabel("Password");
            PassL.setBounds(650, 345, 90, 30);
            contentPane.add(PassL);
            PassL.setBorder(null);
            PassL.setFont(new Font("Tahoma", Font.PLAIN, 10));
            
         // State text field

            String state[] = {"Select State", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN",
                                "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
                                "VA", "WA", "WV", "WI", "WY"};
            @SuppressWarnings({ "unchecked", "rawtypes" })
            JComboBox stateF = new JComboBox(state);
            stateF.setBounds(650, 430, 200, 30);
            contentPane.add(stateF);

            // State label
            JLabel stateL = new JLabel("State");
            stateL.setBounds(650, 405, 200, 30);
            contentPane.add(stateL);
            stateL.setBorder(null);
            stateL.setFont(new Font("Tahoma", Font.PLAIN, 10));

            // Social security number text field
            JPasswordField ssn = new JPasswordField("");
            //JTextField ssn = new JTextField("Social security number");
            ssn.setBounds(650, 490, 200, 30);
            contentPane.add(ssn);
            ssn.setBorder(border);
            ssn.setEchoChar('*');
            ssn.setForeground(new Color(188, 188, 188));
            ssn.setHorizontalAlignment(JTextField.CENTER);

            // Create account button
            JButton btnNewButton_2 = new JButton("Create account");
            btnNewButton_2.setBackground(Color.BLUE);
            btnNewButton_2.setBounds(650, 550, 200, 30);
            contentPane.add(btnNewButton_2);
            btnNewButton_2.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent arg0)
            {
            	String FnameStr = FnameTA.getText();
            	if (FnameStr == "" || FnameStr == null || FnameStr.length() == 0)
                {
                    JOptionPane.showMessageDialog(null,
                                    "Please enter your first name.");
                    
                    return;
                }
            	String LnameStr = LnameTA.getText();
            	if (LnameStr == "" || LnameStr == null || LnameStr.length() == 0)
                {
                    JOptionPane.showMessageDialog(null,
                                    "Please enter your last name.");
                    
                    return;
                }
                String emailFieldStr2 = EmailTA2.getText();
                emailFieldStr2        = emailFieldStr2.trim();
                if (emailFieldStr2 == "" || emailFieldStr2 == null || emailFieldStr2.length() == 0)
                {
                    JOptionPane.showMessageDialog(null,
                                    "Please enter a valid email address.");
                    
                    return;
                }
                char[] passwordFieldChar = passTA2.getPassword();

                if (passwordFieldChar == null || passwordFieldChar.length == 0)
                {
                    JOptionPane.showMessageDialog(null,
                                    "Please enter your password.");
                    
                    return;
                }
                String stateStr = (String) stateF.getSelectedItem();

                if (stateStr == null || stateStr == "Select State")
                {
                    JOptionPane.showMessageDialog(null,
                                    "Please select a state.");
                    
                    return;
                }
                char[] ssnFieldChar = ssn.getPassword();

                if (ssnFieldChar == null || ssnFieldChar.length == 0)
                {
                    JOptionPane.showMessageDialog(null,
                                    "Please enter your ssn.");
                    
                    return;
                }
               
                String dataStr = null;
                dataStr="User : " + emailFieldStr2;
     
                fileIO fio = new fileIO("users.txt");
                fio.wrTransactionData(dataStr);
                
                contentPane.setVisible(false); 
                
                homeScreen bs; // Reference to the class BuyAndSell
                bs = new homeScreen(); // Creates the object of class BuyAndSell
                bs.setVisible(true);
                
                //Ping the server
                socketUtils sock = new socketUtils();
                sock.socketConnect();
                sock.sendMessage(FnameStr);
                sock.sendMessage(LnameStr);
                sock.closeSocket();
                
            }
            });
            contentPane.add(btnNewButton_2);
      

            // Social security label
            JLabel ssnL = new JLabel("Social Security Number");
            ssnL.setBounds(650, 465, 200, 30);
            contentPane.add(ssnL);
            ssnL.setBorder(null);
            ssnL.setFont(new Font("Tahoma", Font.PLAIN, 10));

            // EXIT Button
            JButton btnNewButton_3 = new JButton("EXIT");
            btnNewButton_3.setBounds(6, 757, 133, 34);
            btnNewButton_3.setBackground(Color.LIGHT_GRAY);
            btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
            btnNewButton_3.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    int result = JOptionPane.showConfirmDialog(null,
                                 "Do you really want to exit Coinbase?",
                                 "Coinbase",
                                 JOptionPane.INFORMATION_MESSAGE);

                    
                    if (result == JOptionPane.OK_OPTION)
                    {
                        dispose();
                        System.exit(0);
                     }
                }
                
            });
            contentPane.add(btnNewButton_3);
            
            // HELP Button
            JButton help = new JButton("HELP");
            help.setBounds(152, 757, 133, 34);
            help.setBackground(Color.LIGHT_GRAY);
            help.setFont(new Font("Tahoma", Font.PLAIN, 16));
            help.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {
                        
            }
            });
            contentPane.add(help);
            
            // Call thread to update date and time
            refreshTitleBar();
            
            JPanel panel = new JPanel();
            panel.setBorder(new LineBorder(new Color(238, 238, 238), 5, true));
            panel.setBackground(Color.WHITE);
            panel.setBounds(101, 136, 290, 331);
            contentPane.add(panel);
            
                // Sign in text field
                JTextField header_SI = new JTextField("Sign in to Coinbase");
                panel.add(header_SI);
                header_SI.setBackground(new Color(16, 89, 251));
                header_SI.setEditable(false);
                header_SI.setFont(new Font("Tahoma", Font.PLAIN, 22));
                header_SI.setBorder(null);
                header_SI.setOpaque(false);
                
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(new LineBorder(new Color(238, 238, 238), 5, true));
                panel_1.setBackground(Color.WHITE);
                panel_1.setBounds(589, 136, 322, 482);
                contentPane.add(panel_1);
                
              // Create account label
              JTextField header_SU = new JTextField("Create your account today");
              panel_1.add(header_SU);
              header_SU.setOpaque(false);
              header_SU.setEditable(false);
              header_SU.setFont(new Font("Tahoma", Font.PLAIN, 22));
              header_SU.setBorder(null);
                            
                            JLabel logoLabel = new JLabel("");
                            Image img = new ImageIcon(this.getClass().getResource("/Coinbase.png")).getImage();
                            logoLabel.setIcon(new ImageIcon(img));
                            logoLabel.setBounds(6, 16, 174, 102);
                            contentPane.add(logoLabel);
            
            // Position frame in the middle of the screen
            this.setLocationRelativeTo(null);
        }

        // Thread to update TITLE BAR, date, and time
        private void refreshTitleBar()
        {
            Thread refreshAllTitleBar = new Thread()
            {
                public void run()
                {
                    while (true){
                        try{
                            // Display current time
                            Date  date = new Date();
                            String str = String.format("%tc", date);
                         
                            String titleString = "Coinbase " + str;
                       
                            setTitle(titleString);
                         
                            sleep(5000L);  // Sleep for 5 seconds or 5,000 milliseconds
                       
                        } // End try block
                  
                        catch (InterruptedException e){
                            JOptionPane.showMessageDialog(null,
                                    "ERROR. Interrupt Exception! Check Internet Connection!",
                                    "Title Top Bar",
                                    JOptionPane.WARNING_MESSAGE);
                            
                            continue;
                        }
                        finally{
                    
                        }
                    }   // End while true
                }
            };

          refreshAllTitleBar.start();
        }

}
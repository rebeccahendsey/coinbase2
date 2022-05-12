package coinbase; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.net.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.io.*;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

public class homeScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	
    JTextField amount = new JTextField("0");
    JTextField currBal = new JTextField();
    Integer currBal2;
    
    String crypto[] = {"Bitcoin", "Ethereum", "Dogecoin", "Litecoin"};
    JComboBox comboBox = new JComboBox(crypto);
    private JTextField textField_3;
    
    JTextField bitcoinProfileValue;
    
    String newBal2;
    
    int amountText;
    int valueBit = 0;
    int valueEth = 0;
    int valueDog = 0;
    int valueLite = 0;

    private JTextField liteProfileValue;
    private JTextField dogProfileValue;
    private JTextField ethProfileValue;
    private JTextField textField_4;

    JTextField bitQuan;
    JTextField liteQuan;
    JTextField dogQuan;
    JTextField ethQuan;

    
    long cp2; 
    double current_priceETH2;
    double current_priceDOG2;
    double current_priceLITE2;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField bitLab;
    private JTextField ethLab;
    private JTextField dogLab;
    private JTextField liteLab;
    
    JTextField textField;
    private JTextField top;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homeScreen f2 = new homeScreen();
					f2.setSize(1000,1000);
					f2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public homeScreen() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
        setSize(1000,1000);

		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		// Watchlist label 
        
        JLabel watchlistLab = new JLabel("Profile");
        watchlistLab.setBounds(722, 356, 130, 49);
        watchlistLab.setFont(new Font("Tahoma", Font.PLAIN, 29));
        contentPane.add(watchlistLab);       
     
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(null);
        panel_3.setBackground(Color.WHITE);
        panel_3.setBounds(32, 408, 664, 385);
        contentPane.add(panel_3);
        panel_3.setLayout(null);
        
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(6, 6, 652, 49);
        panel_3.add(panel_2);
        panel_2.setBorder(null);
        panel_2.setBackground(Color.LIGHT_GRAY);
        panel_2.setLayout(null);
        
        top = new JTextField();
        top.setBackground(SystemColor.window);
        top.setBounds(679, 17, 293, 27);
        contentPane.add(top);
        top.setColumns(10);
        top.setEditable(false);
        top.setBorder(null);
        
		startRealTimeClock();

		
		try{
			
			// Api usage 
			
            URL url = new URL("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=200&&order=nsort&page=1&sparkline=false");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int response = connection.getResponseCode();

            if(response != 200){
            	
                throw new RuntimeException("HttpResponseCode: " + response);
                
            }
            else{
            	
                StringBuilder data = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext()){
                    data.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parse = new JSONParser();
                JSONArray obj = (JSONArray) parse.parse(String.valueOf(data));
                
                /**
            	 * BITCOIN
            	 */
                
                // Get id from API 

                JSONObject id = (JSONObject) obj.get(0);
                //System.out.println(id.get("id"));
                String id2 = (String) id.get("id");
                
                // Put id into GUI TextField
                
                JTextField BitcoinNameTF = new JTextField(id2);
                BitcoinNameTF.setBounds(10, 70, 70, 50);
                BitcoinNameTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinNameTF);
                //System.out.println(id2);
                BitcoinNameTF.setEditable(false);
                BitcoinNameTF.setBorder(null);

                // Get current_price from API 

                JSONObject current_price = (JSONObject) obj.get(0);
                //System.out.println(current_price.get("current_price"));
                cp2 = (long) current_price.get("current_price");
                String cp3 = String.valueOf(cp2);
                
                // Put current_price into GUI TextField
                		
                JTextField BitcoinCPTF = new JTextField(cp3);
                BitcoinCPTF.setBounds(90, 70, 70, 50);
                BitcoinCPTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinCPTF);
                //System.out.println(cp2);
                BitcoinCPTF.setEditable(false);
                BitcoinCPTF.setBorder(null);

                
                // Get price_change_24h from API 

                JSONObject price_change_24h = (JSONObject) obj.get(0);
                //System.out.println(price_change_24h.get("price_change_24h"));
                double price_change_24h2 = (double) price_change_24h.get("price_change_24h");
                String pc3 = String.valueOf(price_change_24h2);
                
                // Put price_change_24h into GUI TextField

                JTextField BitcoinPriceTF = new JTextField(pc3);
                BitcoinPriceTF.setBounds(180, 70, 170, 50);
                BitcoinPriceTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinPriceTF);
                //System.out.println(price_change_24h2);
                BitcoinPriceTF.setEditable(false);
                BitcoinPriceTF.setBorder(null);


                // Get total_volume from API 

                JSONObject total_volume = (JSONObject) obj.get(0);
                //System.out.println(total_volume.get("total_volume"));
                long total_volume2 = (long) total_volume.get("total_volume");
                String tv3 = String.valueOf(total_volume2);
        
                // Put total_volume into GUI TextField

                JTextField BitcoinVolTF = new JTextField(tv3);
                BitcoinVolTF.setBounds(360, 70, 100, 50);
                BitcoinVolTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinVolTF);
                //System.out.println(total_volume2);
                BitcoinVolTF.setEditable(false);
                BitcoinVolTF.setBorder(null);


                // Get market_cap from API 
                
                JSONObject market_cap = (JSONObject) obj.get(0);
                //System.out.println(market_cap.get("market_cap"));
                long market_cap2 = (long) market_cap.get("market_cap");
                String mc3 = String.valueOf(market_cap2);

                // Put market_cap into GUI TextField

                JTextField BitcoinMCTF = new JTextField(mc3);
                BitcoinMCTF.setBounds(480, 70, 100, 50);
                BitcoinMCTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinMCTF);
                //System.out.println(market_cap2);
                BitcoinMCTF.setEditable(false);
                BitcoinMCTF.setBorder(null);
                
                
                /**
            	 * ETHEREUM
            	 * 
            	 */
                
                // Get id from API 

                JSONObject idEthereum = (JSONObject) obj.get(1);
                //System.out.println(idEthereum.get("id"));
                String idEthereum2 = (String) idEthereum.get("id");
                
                // Put id into GUI TextField
                
                JTextField ethereumNameTF = new JTextField(idEthereum2);
                ethereumNameTF.setBounds(10, 120, 70, 50);
                ethereumNameTF.setBackground(Color.WHITE);
                panel_3.add(ethereumNameTF);
                //System.out.println(id2);
                ethereumNameTF.setEditable(false);
                ethereumNameTF.setBorder(null);
                
                // Get current_price from API 

                JSONObject current_priceETH = (JSONObject) obj.get(1);
                //System.out.println(current_priceETH.get("current_price"));
                current_priceETH2 = (double) current_priceETH.get("current_price");
                String current_priceETH3 = String.valueOf(current_priceETH2);
                
                // Put current_price into GUI TextField
                		
                JTextField EthCPTF = new JTextField(current_priceETH3);
                EthCPTF.setBounds(90, 120, 70, 50);
                EthCPTF.setBackground(Color.WHITE);
                panel_3.add(EthCPTF);
                //System.out.println(current_priceETH2);
                EthCPTF.setEditable(false);
                EthCPTF.setBorder(null);

                
                // Get price_change_24h from API 

                JSONObject price_change_24hETH = (JSONObject) obj.get(1);
                //System.out.println(price_change_24hETH.get("price_change_24h"));
                double price_change_24hETH2 = (double) price_change_24hETH.get("price_change_24h");
                String price_change_24hETH2_3 = String.valueOf(price_change_24hETH2);
                
                // Put price_change_24h into GUI TextField

                JTextField ETHPriceTF = new JTextField(price_change_24hETH2_3);
                ETHPriceTF.setBounds(180, 120, 170, 50);
                ETHPriceTF.setBackground(Color.WHITE);
                panel_3.add(ETHPriceTF);
                //System.out.println(price_change_24hETH2);
                ETHPriceTF.setEditable(false);
                ETHPriceTF.setBorder(null);


                // Get total_volume from API 

                JSONObject total_volumeETH = (JSONObject) obj.get(1);
                //System.out.println(total_volumeETH.get("total_volume"));
                long total_volumeETH2 = (long) total_volumeETH.get("total_volume");
                String total_volumeETH2_3 = String.valueOf(total_volumeETH2);
        
                // Put total_volume into GUI TextField

                JTextField ethVolTF = new JTextField(total_volumeETH2_3);
                ethVolTF.setBounds(360, 120, 100, 50);
                ethVolTF.setBackground(Color.WHITE);
                panel_3.add(ethVolTF);
                //System.out.println(total_volumeETH2);
                ethVolTF.setEditable(false);
                ethVolTF.setBorder(null);


                // Get market_cap from API 
                
                JSONObject market_capETH = (JSONObject) obj.get(1);
                //System.out.println(market_capETH.get("market_cap"));
                long market_capETH2 = (long) market_capETH.get("market_cap");
                String market_capETH2_3 = String.valueOf(market_capETH2);

                // Put market_cap into GUI TextField

                JTextField ETHMCTF = new JTextField(market_capETH2_3);
                ETHMCTF.setBounds(480, 120, 100, 50);
                ETHMCTF.setBackground(Color.WHITE);
                panel_3.add(ETHMCTF);
                //System.out.println(market_capETH2);
                ETHMCTF.setEditable(false);
                ETHMCTF.setBorder(null);

                
                /**
            	 * DOGECOIN
            	 * 
            	 */
                
                // Get id from API 

                JSONObject idDogecoin = (JSONObject) obj.get(7);
                //System.out.println(idDogecoin.get("id"));
                String idDogecoin2 = (String) idDogecoin.get("id");
                
                // Put id into GUI TextField
                
                JTextField dogecoinNameTF = new JTextField(idDogecoin2);
                dogecoinNameTF.setBounds(10, 170, 70, 50);
                dogecoinNameTF.setBackground(Color.WHITE);
                panel_3.add(dogecoinNameTF);
                //System.out.println(idDogecoin2);
                dogecoinNameTF.setEditable(false);
                dogecoinNameTF.setBorder(null);
                
                // Get current_price from API 

                JSONObject current_priceDOG = (JSONObject) obj.get(7);
                //System.out.println(current_priceDOG.get("current_price"));
                current_priceDOG2 = (double) current_priceDOG.get("current_price");
                String current_priceDOG3 = String.valueOf(current_priceDOG2);
                
                // Put current_price into GUI TextField
                		
                JTextField dogCPTF = new JTextField(current_priceDOG3);
                dogCPTF.setBounds(90, 170, 70, 50);
                dogCPTF.setBackground(Color.WHITE);
                panel_3.add(dogCPTF);
                //System.out.println(current_priceDOG2);
                dogCPTF.setEditable(false);
                dogCPTF.setBorder(null);

                
                // Get price_change_24h from API 

                JSONObject price_change_24hDOG = (JSONObject) obj.get(7);
                //System.out.println(price_change_24hDOG.get("price_change_24h"));
                double price_change_24hDOG2 = (double) price_change_24hDOG.get("price_change_24h");
                String price_change_24hDOG2_3 = String.valueOf(price_change_24hDOG2);

                  
                // Put price_change_24h into GUI TextField

                JTextField DOGPriceTF = new JTextField(price_change_24hDOG2_3);
                DOGPriceTF.setBounds(180, 170, 170, 50);
                DOGPriceTF.setBackground(Color.WHITE);
                panel_3.add(DOGPriceTF);
                System.out.println(price_change_24hDOG2);
                DOGPriceTF.setEditable(false);
                DOGPriceTF.setBorder(null);

                // Get total_volume from API 

                JSONObject total_volumeDOG = (JSONObject) obj.get(7);
                //System.out.println(total_volumeDOG.get("total_volume"));
                long total_volumeDOG2 = (long) total_volumeDOG.get("total_volume");
                String total_volumeDOG2_3 = String.valueOf(total_volumeDOG2);
        
                // Put total_volume into GUI TextField

                JTextField dogVolTF = new JTextField(total_volumeDOG2_3);
                dogVolTF.setBounds(360, 170, 100, 50);
                dogVolTF.setBackground(Color.WHITE);
                panel_3.add(dogVolTF);
                //System.out.println(total_volumeDOG2);
                dogVolTF.setEditable(false);
                dogVolTF.setBorder(null);


                // Get market_cap from API 
                
                JSONObject market_capDOG = (JSONObject) obj.get(7);
                //System.out.println(market_capDOG.get("market_cap"));
                long market_capDOG2 = (long) market_capDOG.get("market_cap");
                String market_capDOG_3 = String.valueOf(market_capDOG2);

                // Put market_cap into GUI TextField

                JTextField DOGMCTF = new JTextField(market_capDOG_3);
                DOGMCTF.setBounds(480, 170, 100, 50);
                DOGMCTF.setBackground(Color.WHITE);
                panel_3.add(DOGMCTF);
                //System.out.println(market_capDOG2);
                DOGMCTF.setEditable(false);
                DOGMCTF.setBorder(null);
                
                
                /**
            	 * LITECOIN
            	 * 
            	 */
                
                // Get id from API

                JSONObject idLitecoin = (JSONObject) obj.get(22);
                System.out.println(idLitecoin.get("id"));
                String idLitecoin2 = (String) idLitecoin.get("id");
                
                // Put id into GUI TextField
                
                JTextField litecoinNameTF = new JTextField(idLitecoin2);
                litecoinNameTF.setBounds(10, 220, 70, 50);
                litecoinNameTF.setBackground(Color.WHITE);
                panel_3.add(litecoinNameTF);
                //System.out.println(id2);
                litecoinNameTF.setEditable(false);
                litecoinNameTF.setBorder(null);
                
                // Get current_price from API 

                JSONObject current_priceLITE = (JSONObject) obj.get(22);
                //System.out.println(current_priceETH.get("current_price"));
                current_priceLITE2 = (double) current_priceLITE.get("current_price");
                String current_priceLITE3 = String.valueOf(current_priceLITE2);
                
                // Put current_price into GUI TextField
                		
                JTextField liteCPTF = new JTextField(current_priceLITE3);
                liteCPTF.setBounds(90, 220, 70, 50);
                liteCPTF.setBackground(Color.WHITE);
                panel_3.add(liteCPTF);
                //System.out.println(current_priceETH2);
                liteCPTF.setEditable(false);
                liteCPTF.setBorder(null);

                
                // Get price_change_24h from API 

                JSONObject price_change_24hLITE = (JSONObject) obj.get(22);
                //System.out.println(price_change_24hETH.get("price_change_24h"));
                double price_change_24hLITE2 = (double) price_change_24hLITE.get("price_change_24h");
                String price_change_24hLITE2_3 = String.valueOf(price_change_24hLITE2);
                
                // Put price_change_24h into GUI TextField

                JTextField LITEPriceTF = new JTextField(price_change_24hLITE2_3);
                LITEPriceTF.setBounds(180, 220, 170, 50);
                LITEPriceTF.setBackground(Color.WHITE);
                panel_3.add(LITEPriceTF);
                //System.out.println(price_change_24hETH2);
                LITEPriceTF.setEditable(false);
                LITEPriceTF.setBorder(null);


                // Get total_volume from API 

                JSONObject total_volumeLITE = (JSONObject) obj.get(22);
                //System.out.println(total_volumeETH.get("total_volume"));
                long total_volumeLITE2 = (long) total_volumeLITE.get("total_volume");
                String total_volumeLITE2_3 = String.valueOf(total_volumeLITE2);
        
                // Put total_volume into GUI TextField

                JTextField liteVolTF = new JTextField(total_volumeLITE2_3);
                liteVolTF.setBounds(360, 220, 100, 50);
                liteVolTF.setBackground(Color.WHITE);
                panel_3.add(liteVolTF);
                //System.out.println(total_volumeETH2);
                liteVolTF.setEditable(false);
                liteVolTF.setBorder(null);


                // Get market_cap from API 
                
                JSONObject market_capLITE = (JSONObject) obj.get(22);
                //System.out.println(market_capETH.get("market_cap"));
                long market_capLITE2 = (long) market_capLITE.get("market_cap");
                String market_capLITE2_3 = String.valueOf(market_capLITE2);

                // Put market_cap into GUI TextField

                JTextField liteMCTF = new JTextField(market_capLITE2_3);
                liteMCTF.setBounds(480, 220, 100, 50);
                liteMCTF.setBackground(Color.WHITE);
                panel_3.add(liteMCTF);
                //System.out.println(market_capETH2);
                liteMCTF.setEditable(false);
                liteMCTF.setBorder(null);

            }
        } catch(Exception e){
            e.printStackTrace();
        }
		

		// Coinbase logo 
		
        Image img = new ImageIcon(this.getClass().getResource("/Coinbase.png")).getImage();
        contentPane.setLayout(null);
        contentPane.setLayout(null);
        
        // Panel for logos, quantity owned, and total value of each owned 
   
        JPanel panel = new JPanel();
        panel.setBounds(722, 408, 250, 385);
        panel.setBorder(null);
        //panel.setBackground(UIManager.getColor("CheckBoxMenuItem.background"));
        panel.setBackground(new Color(247,247,247,255));
        contentPane.add(panel);
        panel.setLayout(null);
        Image bitcoinimg = new ImageIcon(this.getClass().getResource("/bitcoin_PNG48_ccexpress.png")).getImage();
        Image ethimg = new ImageIcon(this.getClass().getResource("/ethereum.png")).getImage();
        Image dogecoinimg = new ImageIcon(this.getClass().getResource("/Dogecoin_Logo_ccexpress.png")).getImage();
        Image litecoinimg = new ImageIcon(this.getClass().getResource("/litecoin-ltc-logo_ccexpress.png")).getImage();
        
        JLabel lblNewLabel_1 = new JLabel("Value (USD)");
        lblNewLabel_1.setBounds(172, 6, 72, 24);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Quantity");
        lblNewLabel_1_1.setBounds(88, 10, 61, 16);
        panel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("Type");
        lblNewLabel_1_2.setBounds(6, 6, 39, 24);
        panel.add(lblNewLabel_1_2);
        
        bitcoinProfileValue = new JTextField("0");
        bitcoinProfileValue.setBounds(172, 50, 72, 26);
        bitcoinProfileValue.setEditable(false);
        panel.add(bitcoinProfileValue);
        bitcoinProfileValue.setColumns(10);
        
        ethProfileValue = new JTextField("0");
        ethProfileValue.setColumns(10);
        ethProfileValue.setBounds(172, 127, 72, 26);
        ethProfileValue.setEditable(false);
        panel.add(ethProfileValue);
        
        dogProfileValue = new JTextField("0");
        dogProfileValue.setColumns(10);
        dogProfileValue.setBounds(172, 214, 72, 26);
        dogProfileValue.setEditable(false);
        panel.add(dogProfileValue);
        
        liteProfileValue = new JTextField("0");
        liteProfileValue.setColumns(10);
        liteProfileValue.setBounds(172, 308, 72, 26);
        liteProfileValue.setEditable(false);
        panel.add(liteProfileValue);
        
        bitQuan = new JTextField();
        bitQuan.setColumns(10);
        bitQuan.setBounds(68, 50, 92, 26);
        bitQuan.setEditable(false);
        panel.add(bitQuan);
        
        ethQuan = new JTextField();
        ethQuan.setColumns(10);
        ethQuan.setBounds(68, 127, 92, 26);
        ethQuan.setEditable(false);
        panel.add(ethQuan);
        
        dogQuan = new JTextField();
        dogQuan.setColumns(10);
        dogQuan.setBounds(68, 214, 92, 26);
        dogQuan.setEditable(false);
        panel.add(dogQuan);
        
        liteQuan = new JTextField();
        liteQuan.setColumns(10);
        liteQuan.setEditable(false);
        liteQuan.setBounds(68, 308, 92, 26);
        panel.add(liteQuan);
        
        bitLab = new JTextField("btc");
        bitLab.setBackground(new Color(247, 247, 247));
        bitLab.setColumns(10);
        bitLab.setBounds(6, 50, 57, 26);
        bitLab.setBorder(null);
        panel.add(bitLab);
        
        ethLab = new JTextField("eth");
        ethLab.setColumns(10);
        ethLab.setBounds(6, 127, 57, 26);
        ethLab.setBackground(new Color(247, 247, 247));
        ethLab.setBorder(null);
        panel.add(ethLab);
        
        dogLab = new JTextField("doge");
        dogLab.setColumns(10);
        dogLab.setBounds(6, 214, 57, 26);
        dogLab.setBackground(new Color(247, 247, 247));
        dogLab.setBorder(null);
        panel.add(dogLab);
        
        liteLab = new JTextField("ltc");
        liteLab.setColumns(10);
        liteLab.setBounds(6, 308, 57, 26);
        liteLab.setBackground(new Color(247, 247, 247));
        liteLab.setBorder(null);
        panel.add(liteLab);
        
        // Some more labels 
        
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setBounds(406, 17, 147, 63);
        welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        contentPane.add(welcomeLabel);
        
        // Coinbase logo 
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(6, 6, 128, 73);
        Image imgLOGO = new ImageIcon(this.getClass().getResource("/Coinbase.png")).getImage();
        logoLabel.setIcon(new ImageIcon(imgLOGO));
        contentPane.add(logoLabel);
        
        // Labels for Watchlist 
        
        JLabel lblNewLabel = new JLabel("NAME");
        lblNewLabel.setBounds(13, 6, 68, 27);
        panel_2.add(lblNewLabel);
        
        JLabel lblPrice = new JLabel("PRICE");
        lblPrice.setBounds(93, 6, 68, 27);
        panel_2.add(lblPrice);
        
        JLabel lblHourChange = new JLabel("24 HR CHANGE");
        lblHourChange.setBounds(173, 6, 96, 27);
        panel_2.add(lblHourChange);
        
        JLabel lblHrVolume = new JLabel("24 HR VOLUME");
        lblHrVolume.setBounds(326, 6, 103, 27);
        panel_2.add(lblHrVolume);       
              
        JLabel lblMarketCap = new JLabel("MARKET CAP");
        lblMarketCap.setBounds(480, 6, 103, 27);
        panel_2.add(lblMarketCap);
        
        // Panel for available balance
        
        JPanel panelTotalBal = new JPanel();
        panelTotalBal.setBounds(42, 142, 301, 96);
        contentPane.add(panelTotalBal);
        panelTotalBal.setBorder(new LineBorder(new Color(255, 255, 255), 8, true));
        panelTotalBal.setBackground(Color.WHITE);
        panelTotalBal.setLayout(null);
        
        // Place holder for value in available balance
        
        textField = new JTextField();
        textField.setFont(new Font("Lucida Grande", Font.PLAIN, 44));
        textField.setBounds(32, 6, 263, 80);
        textField.setText("$452,891");
        textField.setEditable(false);
        panelTotalBal.add(textField);
        textField.setText(newBal2);
        textField.setColumns(10);
        
        // Label for available balance
        JLabel totalBalLabel = new JLabel("Today's Balance (USD):");
        totalBalLabel.setBounds(42, 103, 330, 27);
        contentPane.add(totalBalLabel);
        totalBalLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        
        // Label for watchlist
        JLabel watchlistLab_1 = new JLabel("Watchlist");
        watchlistLab_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
        watchlistLab_1.setBounds(65, 354, 199, 49);
        contentPane.add(watchlistLab_1);
        
        // Label for total balance 
        JPanel panelTotalBal_1 = new JPanel();
        panelTotalBal_1.setLayout(null);
        panelTotalBal_1.setBorder(new LineBorder(new Color(255, 255, 255), 8, true));
        panelTotalBal_1.setBackground(Color.WHITE);
        panelTotalBal_1.setBounds(536, 92, 436, 258);
        contentPane.add(panelTotalBal_1);
        
        // TextField that holds the current balance in USD of that particular crypto
        
        currBal.setText("200");
        currBal.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
        currBal.setEditable(false);
        currBal.setColumns(10);
        currBal.setBounds(138, 34, 174, 53);
        panelTotalBal_1.add(currBal);
        
        // Gray background behind the current balance section 
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.LIGHT_GRAY);
        panel_1.setForeground(Color.LIGHT_GRAY);
        panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 7, true));
        panel_1.setBounds(116, 6, 220, 86);
        panelTotalBal_1.add(panel_1);
        

		//System.out.println(currBal2);


         // Label for current balance 
         JLabel lblCurrentBalance = new JLabel("Current Balance (USD):");
         panel_1.add(lblCurrentBalance);
         lblCurrentBalance.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        // Text field where user will enter the amount of USD they'd like to buy or sell 
        
        //JTextField amount = new JTextField("0");
        amount.setBounds(158, 149, 144, 39);
        panelTotalBal_1.add(amount);
        amount.setColumns(10);
        
        
        // Combo box for selecting the type of cryptocurrency to buy or sell  
        comboBox.setToolTipText("Select cryptocurrency");
        comboBox.setBounds(148, 110, 164, 27);
        panelTotalBal_1.add(comboBox);
       
        
        // Sell button 
        JButton sellButton = new JButton("SELL");
        sellButton.setBounds(242, 198, 117, 54);
        panelTotalBal_1.add(sellButton);
        
        sellButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
        		updateCBSell();  
        		todaysBal();
        	 }
        });
        
        // Buy button
        JButton buyButton = new JButton("BUY");
        buyButton.setBounds(80, 198, 117, 54);
        panelTotalBal_1.add(buyButton);
         
        buyButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
        		updateCBBuy();  
        		todaysBal();	
        	 }
        });
        
        
	}
	public void updateCBBuy() {
		
		amountText = Integer.parseInt(amount.getText());
		String amountStr = Integer.toString(amountText);
        //System.out.println(amountText);
        String currBalText = currBal.getText();
        Integer currInt = Integer.parseInt(currBal.getText());
        
        if(amountText > currInt) {
    		JOptionPane.showMessageDialog(null, "Sorry, insufficient funds!");

        }
        else {
        	Integer currBal2 = Integer.parseInt(currBalText); 
        	Integer newBal = currBal2 - amountText;
        	String newBal2 = String.valueOf(newBal); 
        	System.out.println(newBal2);
        	JOptionPane.showMessageDialog(null, "Thank you for your purchase!");
        	currBal.setText(newBal2); 
        	amount.setText("0");
        	updateValueBuy();
        	updateQuan();
        	
        	
        	// Ping the server with the buying type and amount
            socketUtils sock = new socketUtils();
            sock.socketConnect();
            sock.sendMessage("Purchased");
            sock.sendMessage(amountStr);
            
            Object typeObj = comboBox.getSelectedItem(); 
            String typeStr = typeObj.toString();
            sock.sendMessage(typeStr);

            
            sock.closeSocket();
            
            
        }

	}
	
	public void updateCBSell() {
	    String cryptoChoice = comboBox.getSelectedItem().toString(); 
		amountText = Integer.parseInt(amount.getText());

		String amountStr = Integer.toString(amountText);

		String bitcoinProfileValueStr = bitcoinProfileValue.getText();
	    Integer bitcoinProfileValueINT = Integer.parseInt(bitcoinProfileValueStr);
	    
	    String ethProfileValueStr = ethProfileValue.getText();
	    Integer ethProfileValueINT = Integer.parseInt(ethProfileValueStr);
	    
	    String dogProfileValueStr = dogProfileValue.getText();
	    Integer dogProfileValueINT = Integer.parseInt(dogProfileValueStr);
	    
	    String liteProfileValueStr = liteProfileValue.getText();
	    Integer liteProfileValueINT = Integer.parseInt(liteProfileValueStr);
	    
	    if(cryptoChoice == "Bitcoin" && (amountText > bitcoinProfileValueINT)) {
    		JOptionPane.showMessageDialog(null, "Sorry, insufficient funds!");
        }
	    else if(cryptoChoice == "Ethereum" && (amountText > ethProfileValueINT)) {
    		JOptionPane.showMessageDialog(null, "Sorry, insufficient funds!");
        }
		else if(cryptoChoice == "Dogecoin" && (amountText > dogProfileValueINT)) {
    		JOptionPane.showMessageDialog(null, "Sorry, insufficient funds!");
        }
		else if(cryptoChoice == "Litecoin" && (amountText > liteProfileValueINT)) {
    		JOptionPane.showMessageDialog(null, "Sorry, insufficient funds!");
        }
		else {
			amountText = Integer.parseInt(amount.getText());
			String currBalText = currBal.getText();

			Integer currBal2 = Integer.parseInt(currBalText); 
			Integer newBal = currBal2 + amountText;
			newBal2 = String.valueOf(newBal); 
			System.out.println(newBal2);
			JOptionPane.showMessageDialog(null, "Sale complete!");
			currBal.setText(newBal2); 
			amount.setText("0");
			updateValueSell();
			
			// Ping the server with the selling type and amount
			
			socketUtils sock = new socketUtils();
            sock.socketConnect();
            sock.sendMessage("Amount sold");
            sock.sendMessage(amountStr);
            sock.sendMessage(cryptoChoice);
            sock.closeSocket();
		}
	}
	
	public void updateValueBuy() {
		 //String getCryptoChoice = (String)comboBox.getSelectedItem();
	     String cryptoChoice = comboBox.getSelectedItem().toString(); 
          
         
          
	     // System.out.println(cryptoChoice);
	     
	     if(cryptoChoice == "Bitcoin") {
	    	 Integer newValbit = valueBit + amountText;
	         //String.valueOf(amountText); 
	         String newVal2 = String.valueOf(newValbit);
	    	 bitcoinProfileValue.setText(newVal2);
	    	 valueBit = newValbit;
	     }
	     if(cryptoChoice == "Ethereum") {
	    	 Integer newValEth = valueEth + amountText;
	         //String.valueOf(amountText); 
	         String newVal2 = String.valueOf(newValEth);
	    	 ethProfileValue.setText(newVal2);
	    	 valueEth = newValEth;
	     }
	     if(cryptoChoice == "Dogecoin") {
	    	 Integer newVal = valueDog + amountText;
	         //String.valueOf(amountText); 
	         String newVal2 = String.valueOf(newVal);
	    	 dogProfileValue.setText(newVal2);
	    	 valueDog = newVal;
	     }
	     if(cryptoChoice == "Litecoin") {
	    	 Integer newVal = valueLite + amountText;
	         //String.valueOf(amountText); 
	         String newVal2 = String.valueOf(newVal);
	    	 liteProfileValue.setText(newVal2);
	    	 valueLite = newVal;
	     }
	     
	     
	}
	public void updateValueSell() {
		
		//String getCryptoChoice = (String)comboBox.getSelectedItem();
	    String cryptoChoice = comboBox.getSelectedItem().toString(); 
  
	     // System.out.println(cryptoChoice);
	     
	     if(cryptoChoice == "Bitcoin") {
	    	 Integer newVal = valueBit - amountText;
	         //String.valueOf(amountText); 
	         String newVal2 = String.valueOf(newVal);
	    	 bitcoinProfileValue.setText(newVal2);
	    	 valueBit = newVal;
	     }
	     if(cryptoChoice == "Ethereum") {
	    	 Integer newVal = valueEth - amountText;
	         //String.valueOf(amountText); 
	         String newVal2 = String.valueOf(newVal);
	         ethProfileValue.setText(newVal2);
	    	 valueEth = newVal;
	     }
	     if(cryptoChoice == "Dogecoin") {
	    	 Integer newVal = valueDog - amountText;
	         //String.valueOf(amountText); 
	         String newVal2 = String.valueOf(newVal);
	    	 dogProfileValue.setText(newVal2);
	    	 valueDog = newVal;
	     }
	     if(cryptoChoice == "Litecoin") {
	    	 Integer newVal = valueLite - amountText;
	         //String.valueOf(amountText); 
	         String newVal2 = String.valueOf(newVal);
	    	 liteProfileValue.setText(newVal2);
	    	 valueLite = newVal;
	     }
	     

	     todaysBal();
	}
	
	public void updateQuan() {
		String cryptoChoice = comboBox.getSelectedItem().toString(); 
		
	     if(cryptoChoice == "Bitcoin") {
	    	 float bitQuantity = (float) (valueBit / cp2); 
	    	 String bitQuantityString = String.valueOf(bitQuantity);
	    	 bitQuan.setText(bitQuantityString);
	     }
	     if(cryptoChoice == "Ethereum") {
	    	 float ethQuantity = (float) (valueEth/ current_priceETH2); 
	    	 String ethQuantityString = String.valueOf(ethQuantity);
	    	 ethQuan.setText(ethQuantityString);
	     }
	     if(cryptoChoice == "Dogecoin") {
	    	 float dogQuantity = (float) (valueDog / current_priceDOG2); 
	    	 String dogQuantityString = String.valueOf(dogQuantity);
	    	 dogQuan.setText(dogQuantityString);
	     }
	     if(cryptoChoice == "Litecoin") {
	    	 float liteQuantity = (float) (valueLite / current_priceLITE2); 
	    	 String liteQuantityString = String.valueOf(liteQuantity);
	    	 liteQuan.setText(liteQuantityString);
	     }
	}
	
	public void todaysBal() {
		//String bitcoinValue = String.valueOf(bitcoinProfileValue.getText());
		//String ethValue = String.valueOf(bitcoinProfileValue.getText());
		//String dogValue = String.valueOf(bitcoinProfileValue.getText());
		//String liteValue = String.valueOf(bitcoinProfileValue.getText());
		
		int bitcoinValue = Integer.parseInt(bitcoinProfileValue.getText());
		int ethValue = Integer.parseInt(ethProfileValue.getText());
		int dogValue = Integer.parseInt(dogProfileValue.getText());
		int liteValue = Integer.parseInt(liteProfileValue.getText());

		int todaysbal = bitcoinValue + ethValue + dogValue + liteValue; 
		System.out.printf("bitcoingval: ", bitcoinValue);
		String todaysBalStr = String.valueOf(todaysbal);
		textField.setText(todaysBalStr);

	}
	
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
package coinbase; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.net.*;
import java.text.ParseException;
import java.util.Scanner;
import java.io.*;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class homeScreen extends JFrame {

	private JPanel contentPane;

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
        
        JLabel watchlistLab = new JLabel("Watchlist");
        watchlistLab.setBounds(42, 354, 199, 49);
        watchlistLab.setFont(new Font("Tahoma", Font.PLAIN, 35));
        contentPane.add(watchlistLab);
        
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.YELLOW);
        panel_1.setBorder(new LineBorder(new Color(238, 238, 238), 20, true));
        panel_1.setBounds(38, 67, 574, 39);
        panel_1.setLayout(null);
        
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
		
		try{
            URL url = new URL("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false");
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

                JSONObject id = (JSONObject) obj.get(0);
                System.out.println(id.get("id"));
                String id2 = (String) id.get("id");
                
                JTextField BitcoinNameTF = new JTextField(id2);
                BitcoinNameTF.setBounds(10, 70, 70, 50);
                BitcoinNameTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinNameTF);
                System.out.println(id2);
                BitcoinNameTF.setEditable(false);
                BitcoinNameTF.setBorder(null);

                
                JSONObject current_price = (JSONObject) obj.get(0);
                System.out.println(current_price.get("current_price"));
                long cp2 = (long) current_price.get("current_price");
                String cp3 = String.valueOf(cp2);
                		
                JTextField BitcoinCPTF = new JTextField(cp3);
                BitcoinCPTF.setBounds(90, 70, 70, 50);
                BitcoinCPTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinCPTF);
                System.out.println(cp2);
                BitcoinCPTF.setEditable(false);
                BitcoinCPTF.setBorder(null);

                
                
                JSONObject price_change_24h = (JSONObject) obj.get(0);
                System.out.println(price_change_24h.get("price_change_24h"));
                double price_change_24h2 = (double) price_change_24h.get("price_change_24h");
                String pc3 = String.valueOf(price_change_24h2);
                
                JTextField BitcoinPriceTF = new JTextField(pc3);
                BitcoinPriceTF.setBounds(180, 70, 70, 50);
                BitcoinPriceTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinPriceTF);
                System.out.println(price_change_24h2);
                BitcoinPriceTF.setEditable(false);
                BitcoinPriceTF.setBorder(null);



                JSONObject total_volume = (JSONObject) obj.get(0);
                System.out.println(total_volume.get("total_volume"));
                long total_volume2 = (long) total_volume.get("total_volume");
                String tv3 = String.valueOf(total_volume2);

                
                JTextField BitcoinVolTF = new JTextField(tv3);
                BitcoinVolTF.setBounds(320, 70, 100, 50);
                BitcoinVolTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinVolTF);
                System.out.println(total_volume2);
                BitcoinVolTF.setEditable(false);
                BitcoinVolTF.setBorder(null);


                
                JSONObject market_cap = (JSONObject) obj.get(0);
                System.out.println(market_cap.get("market_cap"));
                long market_cap2 = (long) market_cap.get("market_cap");
                String mc3 = String.valueOf(market_cap2);

                
                JTextField BitcoinMCTF = new JTextField(mc3);
                BitcoinMCTF.setBounds(480, 70, 100, 50);
                BitcoinMCTF.setBackground(Color.WHITE);
                panel_3.add(BitcoinMCTF);
                System.out.println(market_cap2);
                BitcoinMCTF.setEditable(false);
                BitcoinMCTF.setBorder(null);



     
            }
        } catch(Exception e){
            e.printStackTrace();
        }
		

        Image img = new ImageIcon(this.getClass().getResource("/Coinbase.png")).getImage();
        contentPane.setLayout(null);
        contentPane.setLayout(null);
        
        JPanel panelTotalBal = new JPanel();
        panelTotalBal.setBounds(537, 91, 263, 97);
        panelTotalBal.setBorder(new LineBorder(SystemColor.window, 5, true));
        panelTotalBal.setBackground(Color.WHITE);
        contentPane.add(panelTotalBal);
        panelTotalBal.setLayout(null);
        
        JLabel totalBalLabel = new JLabel("Available Balance");
        totalBalLabel.setBounds(46, 10, 170, 27);
        panelTotalBal.add(totalBalLabel);
        totalBalLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        
        JTextField textField = new JTextField();
        textField.setBounds(68, 49, 130, 26);
        textField.setText("$0");
        textField.setEditable(false);
        panelTotalBal.add(textField);
        textField.setColumns(10);

        
        JPanel buySellPan = new JPanel();
        buySellPan.setBounds(537, 229, 263, 97);
        buySellPan.setBackground(Color.WHITE);
        buySellPan.setBorder(new LineBorder(SystemColor.window, 5, true));
        contentPane.add(buySellPan);
        buySellPan.setLayout(null);
        
       
        
        JPanel panel = new JPanel();
        panel.setBounds(32, 91, 404, 262);
        panel.setBorder(new LineBorder(SystemColor.window, 6, true));
        //panel.setBackground(UIManager.getColor("CheckBoxMenuItem.background"));
        panel.setBackground(new Color(247,247,247,255));
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel bc = new JLabel();
        bc.setBounds(6, 6, 62, 56);
        panel.add(bc);
        Image bitcoinimg = new ImageIcon(this.getClass().getResource("/bitcoin_PNG48_ccexpress.png")).getImage();
        bc.setIcon(new ImageIcon(bitcoinimg));

        
        JLabel eth = new JLabel();
        eth.setBounds(6, 74, 62, 56);
        panel.add(eth);
        Image ethimg = new ImageIcon(this.getClass().getResource("/ethereum.png")).getImage();
        eth.setIcon(new ImageIcon(ethimg));
        
        JLabel dogecoin = new JLabel();
        dogecoin.setBounds(6, 133, 62, 56);
        panel.add(dogecoin);
        Image dogecoinimg = new ImageIcon(this.getClass().getResource("/Dogecoin_Logo_ccexpress.png")).getImage();
        dogecoin.setIcon(new ImageIcon(dogecoinimg));
        
        JLabel litecoin = new JLabel();
        litecoin.setBounds(6, 189, 62, 73);
        panel.add(litecoin);
        Image litecoinimg = new ImageIcon(this.getClass().getResource("/litecoin-ltc-logo_ccexpress.png")).getImage();
        litecoin.setIcon(new ImageIcon(litecoinimg));
        
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setBounds(406, 17, 147, 63);
        welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        contentPane.add(welcomeLabel);
        
        JLabel homeLabel = new JLabel("Home");
        homeLabel.setBounds(908, 6, 86, 63);
        homeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(homeLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(6, 6, 128, 73);
        Image imgLOGO = new ImageIcon(this.getClass().getResource("/Coinbase.png")).getImage();
        logoLabel.setIcon(new ImageIcon(imgLOGO));
        contentPane.add(logoLabel);
        
   
     
        
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
        
	}
}

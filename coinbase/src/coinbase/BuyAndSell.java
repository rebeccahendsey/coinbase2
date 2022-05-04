package coinbase;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.util.Scanner;
import javax.tools.JavaFileObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import netscape.javascript.JSObject;


public class BuyAndSell extends coinbase {
	public BuyAndSell() {
	}
	JTextField textField;

	public void BuyAndSell() {
		JFrame f2 = new JFrame("Buy and Sell Cryptocurrencies ");

		f2.getContentPane().setBackground(Color.WHITE);
	    
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setSize(1000,1000);
        Image img = new ImageIcon(this.getClass().getResource("/Coinbase.png")).getImage();
        f2.getContentPane().setLayout(null);
        
        JPanel panelTotalBal = new JPanel();
        panelTotalBal.setBounds(537, 91, 263, 97);
        panelTotalBal.setBorder(new LineBorder(SystemColor.window, 5, true));
        panelTotalBal.setBackground(Color.WHITE);
        f2.getContentPane().add(panelTotalBal);
        panelTotalBal.setLayout(null);
        
        JLabel totalBalLabel = new JLabel("Available Balance");
        totalBalLabel.setBounds(46, 10, 170, 27);
        panelTotalBal.add(totalBalLabel);
        totalBalLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        
        textField = new JTextField();
        textField.setBounds(68, 49, 130, 26);
        textField.setText("$0");
        textField.setEditable(false);
        panelTotalBal.add(textField);
        textField.setColumns(10);

        
        JPanel buySellPan = new JPanel();
        buySellPan.setBounds(537, 229, 263, 97);
        buySellPan.setBackground(Color.WHITE);
        buySellPan.setBorder(new LineBorder(SystemColor.window, 5, true));
        f2.getContentPane().add(buySellPan);
        buySellPan.setLayout(null);
        
        JButton buySellButton = new JButton("Buy / Sell");
        buySellButton.setBackground(Color.BLUE);
        buySellButton.setBounds(73, 22, 126, 50);
        buySellPan.add(buySellButton);
        
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(32, 399, 647, 387);
        panel_2.setBorder(new LineBorder(new Color(238, 238, 238), 7, true));
        panel_2.setBackground(Color.WHITE);
        f2.getContentPane().add(panel_2);
        panel_2.setLayout(null);
        
        JLabel watchlistLab = new JLabel("Watchlist");
        watchlistLab.setBounds(17, 6, 199, 49);
        watchlistLab.setFont(new Font("Tahoma", Font.PLAIN, 35));
        panel_2.add(watchlistLab);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBorder(new LineBorder(new Color(238, 238, 238), 20, true));
        panel_1.setBounds(38, 67, 574, 39);
        panel_2.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("NAME");
        lblNewLabel.setBounds(6, 6, 68, 27);
        panel_1.add(lblNewLabel);
        
        JLabel lblPrice = new JLabel("PRICE");
        lblPrice.setBounds(93, 6, 68, 27);
        panel_1.add(lblPrice);
        
        JLabel lblHourChange = new JLabel("24 HR CHANGE");
        lblHourChange.setBounds(173, 6, 96, 27);
        panel_1.add(lblHourChange);
        
        JLabel lblHrVolume = new JLabel("24 HR VOLUME");
        lblHrVolume.setBounds(313, 6, 103, 27);
        panel_1.add(lblHrVolume);
        
        JLabel lblMarketCap = new JLabel("MARKET CAP");
        lblMarketCap.setBounds(465, 6, 103, 27);
        panel_1.add(lblMarketCap);
        
        
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(SystemColor.window, 6, true));
        //panel.setBackground(UIManager.getColor("CheckBoxMenuItem.background"));
        panel.setBackground(new Color(247,247,247,255));
        panel.setBounds(32, 91, 404, 262);
        f2.getContentPane().add(panel);
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
        welcomeLabel.setBounds(426, 16, 147, 63);
        welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        f2.getContentPane().add(welcomeLabel);
        
        JLabel logoLabel = new JLabel("");
        Image imgLOGO = new ImageIcon(this.getClass().getResource("/Coinbase.png")).getImage();
        logoLabel.setIcon(new ImageIcon(imgLOGO));
        logoLabel.setBounds(6, 6, 128, 73);
        f2.getContentPane().add(logoLabel);
        
	}

}
        
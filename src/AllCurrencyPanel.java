import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class AllCurrencyPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093569272128770912L;
	Operator operator;
	public JTextArea id[] = new JTextArea[10];
	public JTextArea PLNRate[] = new JTextArea[10];
	public JTextArea amountRate[] = new JTextArea[10];
	private String keys[] = {"PLN","EUR","GBP","USD","AUD","HUF","RUB","INR","CHF","BTC"};
	
	public AllCurrencyPanel(Operator o) {
		super();
		
		
		operator=o;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(1200, 125));
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		
		for(int i=0;i<10;i++)
		{
			gbc.insets = new Insets(0, 20, 0, 0);
			addSingleBrick(this, keys[i], gbc,i);
			gbc.gridx++;
		}
		
		
		
		operator.setLowerTextArea(PLNRate, amountRate, keys);
	}
	
	
	void addSingleBrick(JPanel mainPanel, String code, GridBagConstraints gbc, int index)
	{
		
		id[index]=addTextArea(mainPanel, gbc, code);
		
		gbc.gridy++;
		
		PLNRate[index]=addTextArea(mainPanel, gbc, "(345)");
		
		gbc.gridy++;
		amountRate[index]=addTextArea(mainPanel, gbc, "(33.50)");
		gbc.gridy--;
		gbc.gridy--;
		
		
				
		
		
		
		gbc.insets = new Insets(0, 0, 0, 20);
		gbc.gridx++;
		BufferedImage Picture=null;
		try {
			Picture = ImageIO.read(new File("includy/flagi/png/"+code+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(Picture));
		add(picLabel);
		
		
	}
	
	JTextArea addTextArea(JPanel mainPanel, GridBagConstraints gbc, String code)
	{
		JTextArea t= new JTextArea(code);
		t.setEditable(false);
		t.setBackground(Color.DARK_GRAY);
		t.setForeground(Color.white);
		mainPanel.add(t, gbc);
		return t;
	}

}

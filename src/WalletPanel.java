import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WalletPanel extends JPanel{

	/**
	 * 
	 */
	Operator operator;
	GridBagConstraints gbc;
	public JTextArea walletTextItems[] = new JTextArea[10];
	private static final long serialVersionUID = -3772007892250393694L;
	
	public WalletPanel(Operator o) {
		super();
		
		operator=o;
		operator.setWalletTextItems(walletTextItems); //ustawienie referencji na wyswietlanie portfela
		
		
		setLayout(new GridBagLayout());
		gbc= new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.insets = new Insets(0, 20, 0, 20);
		setPreferredSize(new Dimension(1200,50));
		setBackground(Color.DARK_GRAY);
		showWallet();
	}
	
	
	void showWallet()
	{
		Wallet wallet = operator.getWallet();
		JTextArea c,a;
		Iterator it = wallet.getWallet().entrySet().iterator();
		int counter=0;
		while(it.hasNext())
		{
			Map.Entry values = (Map.Entry)it.next();
			
			walletTextItems[counter]= new JTextArea(values.getKey()+" "+values.getValue());
			walletTextItems[counter].setEditable(false);
			walletTextItems[counter].setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 15));
			walletTextItems[counter].setBackground(Color.DARK_GRAY);
			walletTextItems[counter].setForeground(Color.WHITE);
			add(walletTextItems[counter],gbc);
			gbc.gridx++;
			counter++;
		}
		
		
	}
	
}

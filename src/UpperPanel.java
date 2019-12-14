
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UpperPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	JButton refresh;
	JButton loadWallet;
	JTextArea title;
	Operator operator;
	GridBagConstraints gbc;
	private static final long serialVersionUID = 986944670471491100L;
	public UpperPanel(Operator o)
	{
		super();
		operator=o;
		setLayout(new GridBagLayout());
		gbc= new GridBagConstraints();
		
		setPreferredSize(new Dimension(1200,150));
		setBackground(Color.DARK_GRAY);
		gbc.gridwidth=5;
		gbc.gridheight=2;
		
		gbc.weightx=0.5;
		gbc.weighty=0.5;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.anchor=GridBagConstraints.FIRST_LINE_START;
		
		showTitle();
		
		gbc.anchor=GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(0, 10, 60, 0);
	
	
		
		refresh = new JButton(new ImageIcon("includy/buttons/refresh.png"));
		refresh.setBackground(Color.GRAY);
		refresh.setName("refresh");
		refresh.setBorderPainted(false);
		refresh.addActionListener(this);
		refresh.setPreferredSize(new Dimension(30,30));
		add(refresh, gbc);
		
		gbc.anchor=GridBagConstraints.LINE_END;
		
		loadWallet = new JButton(new ImageIcon("includy/buttons/wallet.png"));
		loadWallet.setName("loadWallet");
		loadWallet.setBorderPainted(false);
		loadWallet.addActionListener(this);
		loadWallet.setPreferredSize(new Dimension(30,30));
		add(loadWallet, gbc);
		
		
		
		showWallet();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		if(source.getName()=="refresh")
		{
			operator.refresh();
		}else if(source.getName()=="loadWallet")
		{
			showWallet();
		}
	
		
		
	}
	
	void showTitle()
	{
		gbc.insets = new Insets(0, 10, 40, 10);
		
		title= new JTextArea("Kalkulator Walutowy\n przelicznik walut");
		title.setEditable(false);
		title.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 20));
		title.setBackground(Color.DARK_GRAY);
		title.setForeground(Color.WHITE);
		
		add(title,gbc);
	}
	
	
	void showWallet()
	{
		gbc.anchor=GridBagConstraints.PAGE_END;
		
		JTextArea w= new JTextArea("wallet");
		w.setEditable(false);
		w.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 10));
		w.setBackground(Color.WHITE);
		w.setForeground(Color.WHITE);
		add(w,gbc);
		
		System.out.println("witam");
		
	}
	
	
}

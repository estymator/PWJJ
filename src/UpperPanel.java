
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UpperPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	JButton refresh;
	JTextArea title;
	private static final long serialVersionUID = 986944670471491100L;
	public UpperPanel()
	{
		super();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		setBackground(Color.DARK_GRAY);
		gbc.gridwidth=3;
		gbc.gridheight=1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(40, 10, 40, 10);
		setPreferredSize(new Dimension(800,150));
		
		title= new JTextArea("Kalkulator Walutowy\n przelicznik walut");
		title.setEditable(false);
		title.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 20));
		title.setBackground(Color.DARK_GRAY);
		title.setForeground(Color.WHITE);
		add(title,gbc);
		
		//gbc.insets = new Insets(40, 10, 40, 0);
		gbc.gridx++;
		gbc.anchor=GridBagConstraints.LINE_END;
		
		refresh = new JButton();
		refresh.addActionListener(this);
		refresh.setPreferredSize(new Dimension(30,30));
		add(refresh, gbc);
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("knock");
		
	}
	
	
	
}

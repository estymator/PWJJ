import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FlagPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9037918305227602216L;
	//String to distinguish upper and lower flag panel
	public final String name;
	public JButton flagButtons[] = new JButton[10];
	Operator operator;

	public FlagPanel(Operator o, String n)
	{
		super();
		
		operator=o;
		name=n;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1200, 75));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(40, 10, 40, 10);
		setListOfFlags(this, gbc);
	}
	
	
	/**
	 * Dodaje do ramki widok flag jako przycisk�w
	 * @param panel ramka
	 * @param gbc Layout manager
	 */
	void setListOfFlags(JPanel panel, GridBagConstraints gbc)
	{
		
		flagButtons[0]=getButtonWithFlag("PLN");
		flagButtons[1]=getButtonWithFlag("EUR");
		flagButtons[2]=getButtonWithFlag("GBP");
		flagButtons[3]=getButtonWithFlag("USD");
		flagButtons[4]=getButtonWithFlag("AUD");
		flagButtons[5]=getButtonWithFlag("HUF");
		flagButtons[6]=getButtonWithFlag("RUB");
		flagButtons[7]=getButtonWithFlag("INR");
		flagButtons[8]=getButtonWithFlag("CHF");
		flagButtons[9]=getButtonWithFlag("BTC");
		
		for(int i=0;i<10;i++)
		{
			panel.add(flagButtons[i],gbc);
			gbc.gridx++;
		}
		
		
	}
	
	/**
	 * Zwraca przycisk z okre�lon� flag�
	 * @param code kod pa�stwa
	 * @return
	 */
	JButton getButtonWithFlag(String code)
	{
		JButton button = new JButton(code, new ImageIcon("includy/flagi/png/"+code+".png"));
		button.setBackground(Color.GRAY);
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.addActionListener(this);
		return button;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		System.out.println("FlagPanel zmienia sie na "+source.getText()+name);
		source.setBackground(Color.DARK_GRAY);
		operator.setCurrency(source.getText(), name);
		
	}
}

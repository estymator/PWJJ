import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MaklersListPanel extends JPanel{
	Operator operator;
	MaklersListFrame container;
	GridBagConstraints gbc;
	ArrayList<Makler> maklers;
	public MaklersListPanel(Operator o, MaklersListFrame mk)
	{
		
			super();
			operator=o;
			container=mk;
			
			
			maklers=operator.getMaklers();
			
			setLayout(new GridBagLayout());
			gbc= new GridBagConstraints();
			
			setPreferredSize(new Dimension(700,400));
			setBackground(Color.DARK_GRAY);
			gbc.insets = new Insets(10, 5, 10, 5);
			gbc.gridx=0;
			gbc.gridy=0;
			for(int i=0;i<maklers.size();i++)
			{
				String typ;
				Makler m = maklers.get(i);
				if(m.typ==0) {
					typ="wzrośne powyżej";
				}else
				{
					typ="spadnie poniżej";
				}
				String result=i+" Jeżeli "+m.currCompare1+" "+typ+" "+m.boundaryRate+" względem "+m.currCompare2+" to sprzedaj "+m.amount+" "+m.currForSale+" i kup "+m.currForBuy;
				JTextArea t = new JTextArea(result);
				t.setEditable(false);
				t.setBackground(Color.DARK_GRAY);
				t.setForeground(Color.WHITE);
				this.add(t,gbc);
				gbc.gridy++;
			}
	}

}

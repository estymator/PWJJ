import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MaklersListFrame extends JFrame{
	GridBagConstraints mainGbc;
	Operator operator;
	private static final long serialVersionUID = 801498567097853104L;
	
	public MaklersListFrame(Operator o) {
		
		super("Lista Maklerow");
		operator=o;
		
		setDefaultCloseOperation(MaklerFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.GRAY);
		
		setLocation(100,100);
		setLayout(new GridBagLayout());
		mainGbc = new GridBagConstraints();
		
		
		JPanel maklersListPanel = new MaklersListPanel(operator, this);
		add(maklersListPanel,mainGbc);
		mainGbc.gridy++;
		
		pack();
		setVisible(true);
	}


}

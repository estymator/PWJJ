import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MaklerFrame extends JFrame {

	/**
	 * 
	 */
	GridBagConstraints mainGbc;
	Operator operator;
	private static final long serialVersionUID = 801498567097853104L;
	
	public MaklerFrame(Operator o) {
		
		super("Ustaw próg");
		operator=o;
		
		setDefaultCloseOperation(MaklerFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.GRAY);
		
		setLocation(0,0);
		setLayout(new GridBagLayout());
		mainGbc = new GridBagConstraints();
		
		
		JPanel maklerPanel = new MaklerPanel(operator, this);
		add(maklerPanel,mainGbc);
		mainGbc.gridy++;
		
		pack();
		setVisible(true);
	}

}

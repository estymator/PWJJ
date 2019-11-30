import java.awt.*;
import javax.swing.*;
/**
 * Klasa generuj¹ca okno g³ówne	
 * @author Pawel
 *
 */
public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2269971701250845501L;
	public static final int MAIN_WIDTH=800;
	static final float MAIN_HEIGHT=600;
	Operator operator;
	
	public MainFrame(Operator o)
	{
		super("Kalkulator Walutowy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.GRAY);
		
		operator=o;
		
		
		setLocation(0,0);
		setLayout(new GridBagLayout());
		GridBagConstraints mainGbc = new GridBagConstraints();
		mainGbc.gridx=0;
		mainGbc.gridy=0;
		mainGbc.fill=GridBagConstraints.HORIZONTAL;
		
		
		JPanel upperPanel = new UpperPanel();
		add(upperPanel,mainGbc);
		mainGbc.gridy++;
		
		JPanel upperFlags = new FlagPanel(operator,"upper");
		add(upperFlags,mainGbc);
		mainGbc.gridy++;
		
		JPanel amount = new AmountPanel(operator);
		add(amount, mainGbc);
		mainGbc.gridy++;
		
		JPanel lowerFlags = new FlagPanel(operator,"lower");
		add(lowerFlags,mainGbc);
		mainGbc.gridy++;
		
		JPanel allJPanel = new AllCurrencyPanel(operator);
		add(allJPanel,mainGbc);
		pack();
		setVisible(true);
	}
	
	
}

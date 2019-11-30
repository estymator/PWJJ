import java.awt.*;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AmountPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8467774980603373277L;
	public JTextField amount;
	JTextArea result;
	Operator operator;
	
	
	AmountPanel(Operator o)
	{
		super();
		
		operator=o;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(800, 125));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(40, 10, 40, 10);
		
		result= new JTextArea("1");
		result.setEditable(false);
		result.setBackground(Color.DARK_GRAY);
		result.setForeground(Color.WHITE);
		
		operator.setResultDest(result);
		
		amount = new JTextField("1");
		
		/**
		 * zmiana wartoœci przeliczanej po ka¿dej zmianie wartoœci textBox
		 */
		amount.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    sendValue(e);
				  }
				  public void removeUpdate(DocumentEvent e) {
					sendValue(e);
				  }
				  public void insertUpdate(DocumentEvent e) {
					sendValue(e);
				  }

				  public void sendValue(DocumentEvent e) {
				     
				     Double d;
				     try {
				    	 d=Double.parseDouble(amount.getText());
				    	 operator.setAmount(d);
				     }catch(Exception exc)
				     {
				    	 result.setText("input Numbers Only");
				     }
				  }
				
					
				
				});
		//amount.setPreferredSize(new Dimension(15,10));
		add(amount, gbc);
		gbc.gridx++;
		
		
		
		
		
		add(result,gbc);
		
		this.repaint();
		
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, 25);
		g2.setFont(font);
		g2.setColor(Color.WHITE);

		g2.drawString("Zamieñ <ilosc> <waluta> \n\r tak", 10, 30);
	}


	

}

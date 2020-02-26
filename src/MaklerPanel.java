import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MaklerPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	GridBagConstraints gbc;
	MaklerFrame container;
	Operator operator;
	JComboBox<String> firstList, secondList, thirdList, fourthList, option;
	JTextArea amount, rate;
	JButton submit;
	String[] currencies;
	String[] options= {"wzrośnie powyżej", "spadnie poniżej"};
	private static final long serialVersionUID = 293183404694763276L;
	
	public MaklerPanel(Operator o, MaklerFrame mk){
		super();
		operator=o;
		container=mk;
		setCurrencies();
		
		
		setLayout(new GridBagLayout());
		gbc= new GridBagConstraints();
		
		setPreferredSize(new Dimension(700,400));
		setBackground(Color.DARK_GRAY);
		gbc.insets = new Insets(10, 5, 10, 5);
		gbc.gridx=0;
		gbc.gridy=0;
		
		
		addFirstRaw();
		
		
		gbc.gridy++;
		gbc.gridx=0;
		
		addSecondRaw();
		
		gbc.gridy++;
		gbc.gridx=2;
		
		submit = new JButton("Zapisz");
		submit.setBackground(Color.BLACK);
		submit.setBorderPainted(false);
		submit.addActionListener(this);
		add(submit,gbc);
		
		
	}
	
	/**
	 * Funckja zapisuje do tablicy currencies wszystkie dostępne nazwy walut
	 */
	void setCurrencies()
	{
		List<String> curr = new LinkedList<String>();
		Wallet wallet = operator.getWallet();
		Iterator it = wallet.getWallet().entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry values = (Map.Entry)it.next();
			
			curr.add((String)values.getKey());
			currencies=new String[curr.size()];
			currencies=curr.toArray(currencies);
		}
	}
	
	
	/**
	 * wiersz z wyborem kursu graniczengo i waluty
	 */
	void addFirstRaw()
	{
		JTextArea t1 = new JTextArea("Gdy waluta");
		t1.setEditable(false);
		t1.setBackground(Color.DARK_GRAY);
		t1.setForeground(Color.WHITE);
		this.add(t1,gbc);
		
		gbc.gridx++;
		
		firstList= new JComboBox(currencies);
		firstList.setBackground(Color.GRAY);
		firstList.setForeground(Color.WHITE);
		this.add(firstList,gbc);
		gbc.gridx++;
		
		option= new JComboBox(options);
		option.setBackground(Color.GRAY);
		option.setForeground(Color.WHITE);
		this.add(option,gbc);
		gbc.gridx++;
		
		
		rate = new JTextArea("kurs");
		rate.setBackground(Color.GRAY);
		rate.setForeground(Color.WHITE);
		this.add(rate,gbc);
		gbc.gridx++;
		
		JTextArea t2 = new JTextArea("względem");
		t2.setEditable(false);
		t2.setBackground(Color.DARK_GRAY);
		t2.setForeground(Color.WHITE);
		this.add(t2,gbc);
		gbc.gridx++;
		
		secondList= new JComboBox(currencies);
		secondList.setBackground(Color.GRAY);
		secondList.setForeground(Color.WHITE);
		this.add(secondList,gbc);
		gbc.gridx++;
		}
	
	/**
	 * wiersz z wyborem waluty do kupna
	 */
	void addSecondRaw()
	{
		
		JTextArea t3 = new JTextArea("Sprzedaj");
		t3.setEditable(false);
		t3.setBackground(Color.DARK_GRAY);
		t3.setForeground(Color.WHITE);
		this.add(t3,gbc);
		gbc.gridx++;
		
		
		amount = new JTextArea("ilość");
		amount.setBackground(Color.GRAY);
		amount.setForeground(Color.WHITE);
		this.add(amount,gbc);
		gbc.gridx++;
		
		thirdList= new JComboBox(currencies);
		thirdList.setBackground(Color.GRAY);
		thirdList.setForeground(Color.WHITE);
		this.add(thirdList,gbc);
		gbc.gridx++;
		
		JTextArea t4 = new JTextArea("i kup");
		t4.setEditable(false);
		t4.setBackground(Color.DARK_GRAY);
		t4.setForeground(Color.WHITE);
		this.add(t4,gbc);
		gbc.gridx++;
		
		
		fourthList = new JComboBox(currencies);
		fourthList.setBackground(Color.GRAY);
		fourthList.setForeground(Color.WHITE);
		this.add(fourthList,gbc);
		gbc.gridx++;
		
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton source = (JButton)e.getSource();
		Double a, b;
		try {
			a=Double.valueOf(amount.getText());
			b=Double.valueOf(rate.getText());
		}catch(Exception exc)
		{
			System.out.println("Podaj poprawne wartości");
			return;
		}
		int type=0;
		if(option.getSelectedIndex()==0)
		{
			type=0;
		}else if(option.getSelectedIndex()==1)
		{
			type=1;
		}
		
		operator.addMakler(thirdList.getSelectedItem().toString(),fourthList.getSelectedItem().toString(),firstList.getSelectedItem().toString(),secondList.getSelectedItem().toString(), a, b, type);
		container.dispose();
	}

}


import java.util.ArrayList;
import java.util.concurrent.*;

import javax.swing.JTextArea;

/**
 * Klasa zarządza aktualnie wybranymi wartościami i zleca zadania wątkom
 * @author Pawel
 *  
 */

//TODO blokowac cr za pomoc� lock
public class Operator {
	ExecutorService exec;
	ArrayList<Future<Double>> results;
	ArrayList<ConversionRate> convThreads;
	Wallet wallet = new Wallet();
	
	
	String baseCurrency, destCurrency;
	Double amount;
	CurrencyRates cr;
	ConversionRate conRate;
	JTextArea resultDest;
	JTextArea PLN_Rate[]= new JTextArea[10];
	JTextArea lowerRates[]= new JTextArea[10];  // dolne wartości określonej ilości dla każdej waluty
	String keys[]= {"PLN","EUR","GBP","USD","AUD","HUF","RUB","INR","CHF","BTC"};
	
	Operator()
	{
		baseCurrency="USD";
		destCurrency="PLN";
		amount =1D;
		
		
		
		exec = Executors.newWorkStealingPool();
		results=new ArrayList<Future<Double>>();
		convThreads = new ArrayList<ConversionRate>();
		cr= new CurrencyRates();
		
		
		
		
	}
	
	
	public String getBaseCurrency() {
		return baseCurrency;
	}
	
	
	public void setCurrency(String Currency, String name) {
		if(name=="upper")
		{
			this.baseCurrency = Currency;
			updateResult();
		}else
		{
			this.destCurrency=Currency;
			updateResult();
		}
		
		//conRate= new ConversionRate(baseCurrency, destCurrency, amount)
		
	}
	public String getDestCurrency() {
		return destCurrency;
	}
	
	
	public Double getAmount() {
		return amount;
	}
	
	
	public void setAmount(Double amount ) {
		this.amount = amount;
		updateResult();
	}
	
	Double normalizeDouble(Double d)
	{
		d=Double.valueOf(String.format("%.4f", d));
		return d;
	}
	
	public void setResultDest(JTextArea a)
	{
		this.resultDest=a;
		//Przypisanie do okna wartości początkowej domyślnej
		Double result=(cr.getRate(baseCurrency)/cr.getRate(destCurrency))*amount;
		resultDest.setText(String.valueOf(result));
	}
	
	
	/**
	 * Funkcja aktualizująca aktualnie wyświetlaną wartość przeliczenia
	 */
	void updateResult()
	{
		System.out.println("update");
		
		Double result=(cr.getRate(baseCurrency)/cr.getRate(destCurrency))*amount;
		
		resultDest.setText(String.valueOf(normalizeDouble(result)));
		setLowerAmountArea();
	}
	
	void setPLNRate()
	{
		for(int i=0;i<10;i++)
		{
			PLN_Rate[i].setText("("+String.valueOf(normalizeDouble(cr.getRate(keys[i])))+")");
		}
	}
	
	
	void setLowerTextArea(JTextArea[] pln_rate, JTextArea[] lowerRates, String keys[])
	{
		
		this.PLN_Rate=pln_rate;
		this.lowerRates=lowerRates;
		this.keys=keys;
		setPLNRate();
		setDefaultLowerAmountArea();
	}
	
	
	void setDefaultLowerAmountArea()
	{
		for(int i=0;i<10;i++)
		{
			lowerRates[i].setText("("+String.valueOf(normalizeDouble(cr.getRate(keys[i])))+")");
		}
	}
	
	void setLowerAmountArea()
	{
		convThreads.clear();
		results.clear();
		int counter=0;
		//TODO wieksza kontrola watkow
		
		for(int i=0;i<10;i++)
		{
			convThreads.add(new ConversionRate(cr.getRate(baseCurrency), cr.getRate(keys[i]), amount));
		}
		
		for(ConversionRate t : convThreads)
		{
			results.add(exec.submit(t));
		}
		
		
		for(Future<Double> f : results)
		{
			try {
				lowerRates[counter].setText("("+normalizeDouble(f.get(2, TimeUnit.SECONDS))+")");
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			counter++;
		}
		
		
	}
	
}

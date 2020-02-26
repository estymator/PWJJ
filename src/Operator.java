
import java.util.ArrayList;
import java.util.concurrent.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	ArrayList<Makler> maklers;
	Wallet wallet;
	
	
	ServerClient serverClient;
	MainFrame mainFrame;
	String baseCurrency, destCurrency;
	Double amount;
	CurrencyRates cr;
	ConversionRate conRate;
	JTextArea resultDest;
	JTextArea PLN_Rate[]= new JTextArea[10];
	JTextArea lowerRates[]= new JTextArea[10];  // dolne pola wartości określonej ilości dla każdej waluty
	JTextArea walletTextItems[]=new JTextArea[10]; //wyswietlanie zawartosci portfela;
	String keys[]= {"PLN","EUR","GBP","USD","AUD","HUF","RUB","INR","CHF","BTC"};
	
	Operator()
	{
		wallet = new Wallet();
		baseCurrency="USD";
		destCurrency="PLN";
		amount =1D;
		serverClient=new ServerClient(this);
		
		
		exec = Executors.newWorkStealingPool();
		results=new ArrayList<Future<Double>>();
		convThreads = new ArrayList<ConversionRate>();
		maklers= new ArrayList<Makler>();
		cr= new CurrencyRates();
		
		serverClient.get(maklers);
		
		
	}
	
	/**
	 * Funkcja pobierająca maklerów utworzonych w poprzednich sesjach z serwera 
	 */
	
	public void sendMaklers()
	{
		serverClient.send(maklers);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public Wallet getWallet()
	{
		return wallet;
	}
	
	
	public Double getAmount() {
		return amount;
	}
	
	public ArrayList<Makler> getMaklers()
	{
		return maklers;
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
	
	
	public void setWalletTextItems(JTextArea[] wti)
	{
		this.walletTextItems=wti;
	}
	
	public void updateWalletTextItems()
	{
		
		for(int i=0;i<10;i++)
		{
			walletTextItems[i].setText(keys[i]+" "+String.valueOf(wallet.getWallet().get(keys[i])));
		}
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
		setLowerAmountArea();
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
	
	
	void refresh() //done
	{
		cr.refresh();
		setPLNRate();
		setLowerAmountArea();
		checkMaklers();
	}
	
	/**
	 * Otwiera okno dodawania maklera
	 */
	void setMakler()
	{
		
		MaklerThread makler = new MaklerThread(this);
		Thread mk = new Thread(makler);
		mk.start();
		System.out.println("operator.setMakler()");
		
	}
	
	
	/**
	 * Otwiera okno wypisujące wszystkich maklerów
	 */
	void listMaklers()
	{
		MaklersListThread maklers = new MaklersListThread(this);
		Thread mk = new Thread(maklers);
		mk.start();
		System.out.println("operator.listMaklers()");
	}
	
	/**
	 * 
	 * @param c1 waluta do kupienia
	 * @param c2 waluta do sprzedania
	 * @param c3 waluta bazowa pierwsza
	 * @param c4 waluta bazowa druga
	 * @param a ilosc waluty do kupna
	 * @param b próg kursu
	 * @param t typ porownania(mniejsze/większe)
	 */
	
	void addMakler(String c1, String c2, String c3, String c4, Double a, Double b, int t)
	{
		
		if(wallet.getWallet().get(c1)<a)
		{
			System.out.println("Nie masz tyle pieniedzy"); //TODO add graphic
			JOptionPane.showMessageDialog(null, "Nie posiadasz wystarczających środków");
			return;
		}
		Makler m = new Makler(cr.getAll(),wallet ,c1,c2,c3,c4,a,b,t);
		maklers.add(m);
		checkMaklers();
		
		
		
	}
	void checkMaklers()
	{
		results.clear();
		int i=maklers.size();
		
		
		for(Makler tt : maklers)
		{
			results.add(exec.submit(tt));
		}
		int counter=0;
		
		for(Future<Double> f : results)
		{
			
			try {
				if(f.get()==1)
				{
					maklers.remove(counter);
					updateWalletTextItems(); // TODO po aktualizacji nie wyswietlaja sie nazwy walut w portfelu
					
				}else
				{
					
					
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			counter++;
		}
	
	}
}

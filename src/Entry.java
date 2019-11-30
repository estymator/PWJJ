import java.util.*;
import java.util.concurrent.*;
import java.io.*;


import java.net.*;

public class Entry {
		
	public static void main(String[] args) throws IOException { //TODO eliminate Throws and make try-catch
		System.out.println("Aplikacja maklerska");
		
		/*ExecutorService exec = Executors.newWorkStealingPool();
		ArrayList<Ftask<Integer>> wyniki = new ArrayList<>();
		ArrayList<Makler> ws = new ArrayList<>();
		
		HashMap<String, Double> crr ;
		Wallet wallet = new Wallet();
		CurrencyRates cr= new CurrencyRates();
		crr=cr.getAll();*
		
		/**
		 * Uruchamianie aplikacji okienkowej
		 */
		Operator operator = new Operator();
		Generator g= new Generator(operator);
		
		Thread th = new Thread(g);
		th.start();
				
		try {
            Thread.sleep(600);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		System.out.println("koniec");
	}
	
}

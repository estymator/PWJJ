import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.*;

public class Makler implements Callable<Double>, Serializable {
	HashMap<String, Double> crr ;
	Wallet wallet;
	public String currForSale, currForBuy, currCompare1, currCompare2;
	
	public int typ;//0-wzrosnie powyzej 1-spadnie ponizej
	 public Double boundaryRate;
	public Double amount;
	Makler(HashMap<String, Double> c,Wallet w, String c1, String c2, String c3, String c4, Double a, Double b, int t)
	{
		this.wallet=w;
		this.crr=c;
		this.amount=a;
		this.currForSale=c1;
		this.currForBuy=c2;
		this.currCompare1=c3;
		this.currCompare2=c4;
		this.typ=t;
		this.boundaryRate=b;
	}
	public String toString()
	{
		String toS="Jeżeli "+currCompare1+" jest "+ typ+" względem "+ currCompare2+" niż "+ boundaryRate+"To sprzedaj "+amount+currForSale+" na "+ currForSale;
		return toS;
	}
	
	public void updateRates(HashMap<String, Double> r)
	{
		this.crr=r;
	}
	
	
	public Double call()
	{
//		System.out.println("Jeżeli "+currCompare1+" jest "+ typ+" względem "+ currCompare2+" niż "+ boundaryRate);
//		
//		System.out.println("To sprzedaj "+amount+currForSale+" na "+ currForSale);
//		System.out.println("Kurs miedzy walutami "+ (crr.get(currCompare1)/crr.get(currCompare2)));
//		
		if(typ==1)
		{
			if((crr.get(currCompare1)/crr.get(currCompare2))<boundaryRate)
			{
				Double kurs = crr.get(currForSale)/crr.get(currForBuy);
				Double ilosc =amount*kurs;
				Double oldSale=wallet.getWallet().get(currForSale);
				Double oldBuy=wallet.getWallet().get(currForBuy);
				wallet.getWallet().replace(currForSale, oldSale-amount);
				wallet.getWallet().replace(currForBuy, oldBuy+ilosc);
				return 1D;
			
			}else
			{
				return 0D;
			}
		}else
		{
			if((crr.get(currCompare1)/crr.get(currCompare2))>boundaryRate)
			{
				Double kurs = crr.get(currForSale)/crr.get(currForBuy);
				Double ilosc =amount*kurs;
				Double oldSale=wallet.getWallet().get(currForSale);
				Double oldBuy=wallet.getWallet().get(currForBuy);
				wallet.getWallet().replace(currForSale, oldSale-amount);
				wallet.getWallet().replace(currForBuy, oldBuy+ilosc);
				return 1D;
			}else
			{
				return 0D;
			}
		}
		
	}

}

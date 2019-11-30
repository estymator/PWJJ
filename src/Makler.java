import java.util.HashMap;
import java.util.concurrent.*;

public class Makler implements Callable<Double> {
	HashMap<String, Double> crr ;
	String currForSale;
	String currForBuy;
	Double amount;
	Makler(HashMap<String, Double> c, String c1, String c2, Double a)
	{
		this.crr=c;
		this.amount=a;
		this.currForSale=c1;
		this.currForBuy=c2;
	}
	public String toString()
	{
		return "Siema";
	}
	public Double call()
	{
		Double rate=(crr.get(currForSale)*amount)/crr.get(currForBuy);
		System.out.println("Sprzeda¿ "+amount+" "+currForSale+" oraz kupno "+rate+" "+currForBuy);
		return 1D;
	}

}

import java.util.concurrent.*;

public class ConversionRate implements Callable<Double> {
	Double baseRate, destRate;
	Double amount;
	Double rate;
	public ConversionRate(Double c1, Double c2, Double a) {
		baseRate=c1;
		destRate=c2;
		amount=a;
		
	}
	@Override
	public Double call() throws Exception {
		Double result=amount*(baseRate/destRate);
		return result;
	}

}

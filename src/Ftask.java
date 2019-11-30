import java.util.concurrent.*;

public class Ftask<T> extends FutureTask<T>{
	String name;
	public Ftask(Callable<T> c)	
	{
		super(c);
		name="Witaj podróżniku";
		
	}
	
	protected void done()
	{
		try {
			System.out.println("metoda done"+this.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


public class MaklerThread implements Runnable{
	private Operator operator;
	public MaklerThread(Operator o) {
	 operator=o;
	}
	public void run()
	{
		new MaklerFrame(operator);
	}
}


public class MaklersListThread implements Runnable{
	private Operator operator;
	public MaklersListThread(Operator o) {
	 operator=o;
	}
	public void run()
	{
		new MaklersListFrame(operator);
	}
}

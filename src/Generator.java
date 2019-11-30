

public class Generator implements Runnable{
	Operator operator;
	Generator(Operator o)
	{
		operator=o;
	}
	public void run()
	{
		new MainFrame(operator);
	}

}

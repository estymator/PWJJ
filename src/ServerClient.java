import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerClient {
	int port;
	String address;
	Makler m;
	Operator operator;
	public ServerClient(Operator o)
	{
		address="localhost";
		operator=o;
		port = 8082;
			
	}
	public void send(ArrayList<Makler> maklers)
	{
		int counter = maklers.size();
		if(counter==0) return;
		try {
			Socket socket = new Socket(InetAddress.getByName(address), port);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			socket.setTcpNoDelay(true);
			out.println(counter);
			for(int i=0;i<counter;i++)
			{
				m=maklers.get(i);
				
				out.println(m.currCompare1);
				out.println(m.currCompare2);
				out.println(m.currForSale);
				out.println(m.currForBuy);
				out.println(m.typ);
				out.println(m.boundaryRate);
				out.println(m.amount);
			}
			out.println("exit");
			out.close();
			socket.close();
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
	
	}
	
	
	public void get(ArrayList<Makler> maklers)
	{
		
		try {
			Socket socket = new Socket(InetAddress.getByName(address), port);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socket.setTcpNoDelay(true);
			out.println("get");
			int counter = Integer.valueOf(in.readLine());
			for(int i=0;i<counter;i++)
			{
				
				Double amount,bound;
				Integer typ;
				String c1,c2,c3,c4;
				c1=in.readLine();
				c2=in.readLine();
				c3=in.readLine();
				c4=in.readLine();
				typ=Integer.valueOf(in.readLine());
				bound=Double.valueOf(in.readLine());
				amount=Double.valueOf(in.readLine());
				operator.addMakler(c3, c4, c1, c2, amount, bound, typ);				
			}
			in.close();
			out.close();
			socket.close();
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
	
	}
}

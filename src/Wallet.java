import java.io.*;
import java.sql.*;
import java.util.*;

public class Wallet {
	HashMap<String, Double> wallet_amount = new HashMap<String, Double>();
	static String driver, url;
	Connection con=null;
	Statement st=null;
	public Wallet()
	{
		
		driver = "com.mysql.cj.jdbc.Driver";
		url = "jdbc:mysql://147.135.208.197:3306/PWJJ";
		if(loadDBDriver())
		{
			try {
		        con = DriverManager.getConnection(url, System.getenv("DBlogin"), System.getenv("DBhaslo"));
		        System.out.println("Połączono z bazą");
		    } catch (SQLException e) {
		        System.out.println("Blad przy tworzeniu połączenia!");
		        System.exit(1);
		    }
			try {
				st = con.createStatement();
				System.out.println("Utworzono statement");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResultSet query = execQuery("Select * from wallet");
			loadDataToWallet(query);
			closeConnection(con, st);
			
		}
		
		
	}
	
	static boolean loadDBDriver() {
	    // LADOWANIE STEROWNIKA
	    System.out.print("Sprawdzanie sterownika:");
	    try {
	        Class.forName(driver).newInstance();
	        return true;
	    } catch (Exception e) {
	        System.out.println("Blad przy ladowaniu sterownika bazy!");
	        return false;
	    }
	}
	
	ResultSet execQuery(String q)
	{
		try
		{
			return st.executeQuery(q);
		}
		catch(Exception e)
		{
			System.out.println("Błąd podczas wykonywania zapytania w bazie");
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
	
	private void loadDataToWallet(ResultSet r) {
	    ResultSetMetaData rsmd;
	    try {
	        rsmd = r.getMetaData();
	        int numcols = rsmd.getColumnCount(); // pobieranie liczby column
	        String c=null;
	        Double d=null;
	        while (r.next()) {
	            for (int i = 1; i <= numcols; i++) {
	            	if(i==1)
	            	{
	            		Object obj = r.getObject(i);
		                if (obj != null)
		                {
		                	c=obj.toString();
		                }
	            	}else
	            	{
	            		Object obj = r.getObject(i);
		                if (obj != null)
		                {
		                	d =Double.valueOf(obj.toString());
		                }
	            	}
	            	
	                    
	            }
	            wallet_amount.put(c, d);
	        }
	        //System.out.println(wallet_amount);
	    } catch (SQLException e) {
	        System.out.println("Bląd odczytu z bazy! " + e.toString());
	        System.exit(3);
	    }
	}
	
	
	private void closeConnection(Connection connection, Statement s) {
	    System.out.print("\nZamykanie polaczenia z bazą:");
	    try {
	        s.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Bląd przy zamykaniu polączenia " + e.toString());
	        System.exit(4);
	    }
	    System.out.print("Poprawne zamknięcie Połaczenia z baza");
	}
	
	
	HashMap<String, Double> getWallet()
	{
		return wallet_amount;
	}


	

}

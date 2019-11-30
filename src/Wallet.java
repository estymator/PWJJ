import java.io.*;
import java.sql.*;
import java.util.*;

public class Wallet {
	HashMap<String, Double> wallet_amount = new HashMap<String, Integer>();
	static String driver, url;
	Connection con;
	Statement st;
	public Wallet()
	{
		


		driver = "com.mysql.cj.jdbc.Driver";
		url = "jdbc:mysql://147.135.208.197:3306/PWJJ";
		if(loadDBDriver())
		{
			try {
		        con = DriverManager.getConnection(url, "estymator", "jnesbo"); //TODO not hardcode passwd
		    } catch (SQLException e) {
		        System.out.println("Blad przy ladowaniu sterownika!");
		        System.exit(1);
		    }
			try {
				st = con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResultSet all = execQuery("Select * from wallet");
			loadDataToWallet(all);
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
			System.out.println("Poprawnie");
			return st.executeQuery(q);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private static void loadDataToWallet(ResultSet r) {
	    ResultSetMetaData rsmd;
	    try {
	        rsmd = r.getMetaData();
	        int numcols = rsmd.getColumnCount(); // pobieranie liczby column
	        
	        while (r.next()) {
	            for (int i = 1; i <= numcols; i++) {
	            	if(i==1)
	            	{
	            		Object obj = r.getObject(i);
		                if (obj != null)
		                {
		                	String c=obj.toString();
		                }
	            	}else
	            	{
	            		Object obj = r.getObject(i);
		                if (obj != null)
		                {
		                	Double a =Double.valueOf(obj.toString());
		                }
	            	}
	            	//TODO zapisac do wallet amount
	                    
	            }
	            System.out.println();
	        }
	    } catch (SQLException e) {
	        System.out.println("Bląd odczytu z bazy! " + e.toString());
	        System.exit(3);
	    }
	}
	
	
	private static void closeConnection(Connection connection, Statement s) {
	    System.out.print("\nZamykanie polaczenia z bazą:");
	    try {
	        s.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out
	                .println("Bląd przy zamykaniu polączenia " + e.toString());
	        System.exit(4);
	    }
	    System.out.print(" zamknięcie OK");
	}
	
	
	HashMap<String, Double> getAll()
	{
		return wallet_amount;
	}


	

}

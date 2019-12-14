import java.io.*;
import java.net.*;
import java.util.*;
import java.util.HashMap;
import java.util.concurrent.locks.*;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class CurrencyRates {
	private HashMap<String, Double> currency_rates = new HashMap<String, Double>();
	private Lock lock = new ReentrantLock();
	
	
	@SuppressWarnings("unchecked")
	CurrencyRates() 
	{
		
		System.out.println("LOCK");
		StringBuffer content = new StringBuffer();
		
		try {
			content=sendReq();
		} catch(UnknownHostException e) {
			System.out.println("Sprawdz połączenie z internetem");
		}catch (IOException e1) {
		
			e1.printStackTrace();
		}
		
		
		try {
			parseResponse(content.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Dodanie kursów pln 
		
		currency_rates.put("PLN",1D);
		currency_rates.put("BTC",10000D);

	}
	
	StringBuffer sendReq() throws IOException
	{
		StringBuffer content = new StringBuffer();
		URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A/?format=json");
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setConnectTimeout(2000);
		con.setReadTimeout(2000);
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
				String inputLine;
				
				while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
				}
				in.close();
				con.disconnect();
				return content;
	}
	
	void parseResponse(String response) throws ParseException
	{
		Object ResponseJson = new JSONParser().parse(response);
		JSONArray ResponseJsonAray = (JSONArray) ResponseJson; //odpowiedz serwera konwertowana do tablicy json
		java.util.Iterator jsonItr2 =ResponseJsonAray.iterator(); 
		JSONObject jo =(JSONObject) jsonItr2.next(); 
		JSONArray resJsonArray = (JSONArray) jo.get("rates"); //cz�� danych z kursami walut
		java.util.Iterator<JSONObject> itr1 =(java.util.Iterator<JSONObject>) resJsonArray.iterator(); 
		while(itr1.hasNext())
		{
		
			JSONObject currency =(JSONObject) itr1.next();
			//System.out.println(currency);
			currency_rates.put((String) currency.get("code"),(Double) currency.get("mid"));
		}
	}
	
	HashMap<String, Double> getAll()
	{
		return currency_rates;
	}
	
	Double getRate(String key)
	{
		return currency_rates.get(key);
	}
	
	void refresh()
	{
		currency_rates.clear();
		
StringBuffer content = new StringBuffer();
		
		try {
			content=sendReq();
		} catch(UnknownHostException e) {
			System.out.println("Sprawdz połączenie z internetem");
		}catch (IOException e1) {
		
			e1.printStackTrace();
		}
		
		
		try {
			parseResponse(content.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Dodanie kursów pln 
		
		currency_rates.put("PLN",1D);
		currency_rates.put("BTC",10000D);

		
	}
}

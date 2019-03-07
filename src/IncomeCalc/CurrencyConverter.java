package IncomeCalc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
	
	public static double GetExchangeRate() {	//Convert USD to GBP via CurrencyLayer API (free 1000 requests per month - hourly updates!) 
		
		try {
			
			// pass the path to the file as a parameter 
		    File file = new File("CurrencyAccessCode.txt"); 
		    Scanner sc = new Scanner(file); 
		    
			String accessKey = sc.nextLine();
					
			String url = "http://apilayer.net/api/live?access_key=" + accessKey + "&currencies=GBP&format=1";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			int responseCode = con.getResponseCode();
			//System.out.println("\nSending GET request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			
			/*
			System.out.println(response.toString());
			
			System.out.println("\n\n");
			System.out.println(response.toString().split(",").toString());
			*/
			
			String[] parts = response.toString().split(",");

			/*
			System.out.println(parts[0]);
			System.out.println(parts[1]);
			System.out.println(parts[2]);
			System.out.println(parts[3]);
			System.out.println(parts[4]);
			System.out.println(parts[5]);
			*/
			
			double ConversionRate = Double.parseDouble(parts[5].split(":")[2].replaceAll("}}", ""));
			//System.out.println("\n\n\n" + ConversionRate);
			
			return ConversionRate;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return 404;
	}
}

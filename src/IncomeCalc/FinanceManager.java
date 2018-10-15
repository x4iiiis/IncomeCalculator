package IncomeCalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinanceManager {

	//Singleton
	private static FinanceManager instance = null;

	
	//Finance Manager singleton contains the lists of all the bills and reasons to save
	//This keeps the code tidy and keeps the lists out of the way of the functionality program code
	private static List<Bill> BillList = new ArrayList<Bill>();
	private static List<ReasonToSave> SaveList = new ArrayList<ReasonToSave>();
	
	static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	//FinanceManager is a singleton
	public static FinanceManager getInstance() throws FileNotFoundException
	{
		if(instance == null)
		{
			instance = new FinanceManager();
		}
		return instance;
	}
	
	//Things the FinanceManager should do as soon as it is instantiated
	FinanceManager() throws FileNotFoundException
	{	
		//Read .csv file
		Scanner csvReader = new Scanner(new File("BillsPrototype.csv"));
		csvReader.useDelimiter(",");
				
		//Gather every bill from the .csv file, separate the name and amount by the comma
		//and then assign them to the corresponding variable of the new Bill object created
		//for each line in the .csv (for each bill).
		//Then, add these Bill objects to the BillList.
		
		int Skip = 0; // Used to skip the first 2 rows of the csv
		while(csvReader.hasNext())
		{
			while(Skip < 4)
			{
				csvReader.nextLine();
				Skip++;
			}
			String data = csvReader.nextLine();
            String [] arr = data.split(",");
			
            //System.out.println(data);
            
			Bill B = new Bill();
			B.billName = arr[0];
            B.billAmount = Double.parseDouble(arr[1]);
            B.monthsBetweenPayments = Integer.parseInt(arr[2]);
            B.lastPayment = LocalDate.parse(arr[3], dateFormatter);
			BillList.add(B);
		}
		System.out.println();
		System.out.println();
		csvReader.close();
	}
	
	
	
	public List<Bill> getBillList()
	{
		return BillList;
	}
	
	public static void SetBillList(List<Bill> AmendedBillList)
	{
		BillList = AmendedBillList;
	}
	
	
	
	public List<ReasonToSave> getSaveList()
	{
		return SaveList;
	}
	
	public static void SetSaveList(List<ReasonToSave> AmendedSaveList)
	{
		SaveList = AmendedSaveList;
	}
	
	public static void payBills(List<Bill> BillList)
	{
		for (int i = 0; i < BillList.size(); i++)
		{
			//If bill has been paid between payday and today, call pay()
			//Dunno if that'll work actually cause pay takes in 'remaining income'
			//Which means we're probably gonna have to refactor the whole thing to make remaining income a static variable
			System.out.println(BillList.get(i).billName + "\t\t\t\t\t" + dateFormatter.format(BillList.get(i).lastPayment));
			//For now I'm just gonna use this to test the reading of dates from csv
		}
	}
}
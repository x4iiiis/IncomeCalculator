package IncomeCalc;

import java.io.File;
import java.io.FileNotFoundException;
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
		Scanner csvReader = new Scanner(new File("Bills.csv"));
		csvReader.useDelimiter(",");
				
		//Gather every bill from the .csv file, separate the name and amount by the comma
		//and then assign them to the corresponding variable of the new Bill object created
		//for each line in the .csv (for each bill).
		//Then, add these Bill objects to the BillList.
		while(csvReader.hasNext())
		{
			String data = csvReader.nextLine();
            String [] arr = data.split(",");
			
			Bill B = new Bill();
			B.billName = arr[0];
            B.billAmount = Double.parseDouble(arr[1]);
			BillList.add(B);
		}
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
}
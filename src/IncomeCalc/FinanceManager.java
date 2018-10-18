package IncomeCalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
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
	static Income inc = new Income();
	
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
		//Get the salary info
		//Read .csv file
		Scanner SalaryReader = new Scanner(new File("Income.csv"));
		SalaryReader.useDelimiter(",");
				
		//Gather every bill from the .csv file, separate the name and amount by the comma
		//and then assign them to the corresponding variable of the new Bill object created
		//for each line in the .csv (for each bill).
		//Then, add these Bill objects to the BillList.
		
		int Skip = 0; // Used to skip the first 2 rows of the csv
		while(SalaryReader.hasNext())
		{
			while(Skip < 1)
			{
				SalaryReader.nextLine();
				Skip++;
			}
			String data = SalaryReader.nextLine();
            String [] arr = data.split(",");
			
            System.out.println(data);
            
			
			inc.Salary = Double.parseDouble(arr[0]);
            inc.LastPayday = LocalDate.parse(arr[1], dateFormatter);
            try // If MonthsBetweenWages is null or fails, then it *should be* because we're using weeks instead.
            {
            	inc.MonthsBetweenWages = Integer.parseInt(arr[2]);
            }
            catch(Exception NullMonths)
            {
            	inc.WeeksBetweenWages = Integer.parseInt(arr[3]);
            }
			
		}
		System.out.println();
		System.out.println();
		SalaryReader.close();
		
		//While there are unpaid months on record, loop through payment dates
		while(inc.LastPayday.plusMonths(inc.MonthsBetweenWages).isBefore(LocalDate.now()))
		{
			inc.LastPayday = inc.LastPayday.plusMonths(inc.MonthsBetweenWages); //This line is beautiful. Keep it.
		}
		inc.NextPayday = inc.LastPayday.plusMonths(inc.MonthsBetweenWages); //nextPayment isn't in the csv, but it doesn't need to be. I guess it could be, but it'd be a waste of space
		
		
		
		//Get bill info
		//Read .csv file
		Scanner BillsReader = new Scanner(new File("BillsPrototype.csv"));
		BillsReader.useDelimiter(",");
				
		//Gather every bill from the .csv file, separate the name and amount by the comma
		//and then assign them to the corresponding variable of the new Bill object created
		//for each line in the .csv (for each bill).
		//Then, add these Bill objects to the BillList.
		
		Skip = 0; // Used to skip the first 2 rows of the csv
		while(BillsReader.hasNext())
		{
			while(Skip < 4)
			{
				BillsReader.nextLine();
				Skip++;
			}
			String data = BillsReader.nextLine();
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
		BillsReader.close();
		
		
		//Messing about with dates, not necessarily going to happen here but just want to test it and this
		//seems as good a place as any
		for(Bill b : BillList)
		{
			//Duration duration = Duration.between ( b.lastPayment , LocalDate.now() );
			//System.out.println("Duration: " + duration);
			
			//System.out.println(b.billName + " last paid " + b.lastPayment);
			
			//While there are unpaid months on record, loop through payment dates
			while(!(b.lastPayment.plusMonths(b.monthsBetweenPayments).isAfter(LocalDate.now())))
			{
				b.lastPayment = b.lastPayment.plusMonths(b.monthsBetweenPayments); //This line is beautiful. Keep it.
			}
			b.nextPayment = b.lastPayment.plusMonths(b.monthsBetweenPayments); //nextPayment isn't in the csv, but it doesn't need to be. I guess it could be, but it'd be a waste of space
			
			System.out.println(b.billName + " last paid " + b.lastPayment);
		}
		
		System.out.println();
		//System.out.println(inc.NextPayday + " <--- inc.NextPayday");
		
		System.out.println("\n\nOkay now it's time to check which fall between your last payday and today\n\n");
		
		for(Bill b : BillList)
		{
			//System.out.println(b.nextPayment + " <--" + b.billName + " next payment");
			if(b.nextPayment.isBefore(inc.NextPayday))
			{
				System.out.println(b.billName + " is still to be paid on this wage:");
				System.out.println("Due: " + b.nextPayment + "\nWage: " + inc.NextPayday + "\n");
			}
			else
			{
				System.out.println(b.billName + " has already been paid on this wage:");
				System.out.println("Due: " + b.nextPayment + "\nWage: " + inc.NextPayday + "\n");
			}
		}
		
		
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
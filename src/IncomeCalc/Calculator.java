package IncomeCalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator 
{
	public static void calculate() throws FileNotFoundException 
	{
		//Outside variables
		double incomeTaxRate = 0;
		double nationalInsuranceRate = 0;
		double studentLoanRate = 0;
		
		//My variables
		double salary = 25000;
		
		
		
		System.out.println("Salary: " + salary);
		System.out.println("Tax Rate: " + getIncomeTax(salary));
		
		//Pass salary to obtain percentage, then apply it to the taxable income (above £11,500 threshold)
		//Take this off Salary, to obtain post-tax salary
		double income = salary - (getIncomeTax(salary) * (salary - 11500));
		
		System.out.println("Post-Tax Salary: " + income);
		
		System.out.println("National Insurance Rate: " + getNationalInsuranceRate(salary));
		
		//Applying the national insurance rate to the salary minus the 162 threshold, and removing it from the salary
		//Income = Income - (GetNationalInsuranceRate(Salary) * (Salary - (162*52)));

		income = income - (calcAndDeductNationalInsurance(salary));
		System.out.println("Post National Insurance Salary: " + income);
		
		System.out.println();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
		LocalDate today = LocalDate.now();
		//System.out.println(dtf.format(today)); //2016/11/16
		
		System.out.println("");
		System.out.println("");
		
		
		//Prints the name of current directory, was using this to debug finding the csv
		//System.out.println(System.getProperty("user.dir"));
		
		//FinanceManager FM = FinanceManager.getInstance();
		

		
		//income = PayBills(income);
		//System.out.println("");
		//System.out.println("Post-bills income: " + income);
		//System.out.println("");
		//System.out.println("Your monthly expenses sum to " + MonthlyExpendature());
		
		//System.out.println();
		//System.out.println();
		FinanceManager FM = FinanceManager.getInstance();
		//FM.payBills(FM.getBillList());
	}


	public static double getIncomeTax(double salary)
	{
		double taxRate = 0;
		
		//Incomes up to £11,500 are tax-free
		if(salary <= 11500)
		{
			taxRate = 0.0;
		}
		
		//Basic rate is 20% on incomes from £11,501 to £43,000
		if(salary > 11500 && salary <= 43000)
		{
			taxRate = 0.2;
		}
		
		//Higher rate is 40% on incomes from £43,001 to £150,000
		if(salary > 43000 && salary <= 150000)
		{
			taxRate = 0.4;
		}
		
		//Additional rate is 45% on incomes over £150,000
		if(salary > 150000)
		{
			taxRate = 0.45;
		}
		
		return taxRate;
	}
	
	//Really not sure about this national insurance shit tbh
	public static double getNationalInsuranceRate(double salary)
	{
		//You begin paying National Insurance once you earn more than £162 A WEEK (2018-19)
		//12% between 162 and 892
		//2% above 892
		
		double weeklyWage = salary / 52;
		double nationalInsuranceRate = 0;
		
		
		if(weeklyWage > 162 && weeklyWage <= 892)
		{
			nationalInsuranceRate = 0.12;
		}
		
		//The way this code is set out it's not going to be able to handle the 2% above 892 yet but that's not gonna be a problem
		//personally... lmao
		
		return nationalInsuranceRate;
	}
	
	
	//Trying to fix this national insurance patter
	public static double calcAndDeductNationalInsurance(double salary)
	{
		double weeklyWage = salary / 52;
		double nationalInsuranceRate = 0;
		
		if(weeklyWage > 162 && weeklyWage <= 892)
		{
			nationalInsuranceRate = 0.12;

			return ((weeklyWage - 162) * nationalInsuranceRate) * 52;
		}
		
		//Ignoring the 2% above 892 part for now, also returning 0 here to keep the method happy but it shouldn't ever get to here
		return 0;
	}
	
	
	public static double PayBills(double PostTaxIncome) throws FileNotFoundException
	{
		for(int i = 0; i < FinanceManager.getInstance().getBillList().size(); i++)
		{
			PostTaxIncome = FinanceManager.getInstance().getBillList().get(i).Pay(PostTaxIncome);
		}
		
		return PostTaxIncome;
	}
	
	public static double MonthlyExpendature() throws FileNotFoundException
	{
		double MonthlyExpendature = 0;
		
		for(int i = 0; i < FinanceManager.getInstance().getBillList().size(); i++)
		{
		MonthlyExpendature += FinanceManager.getInstance().getBillList().get(i).billAmount;
		}
		
		return MonthlyExpendature;
	}
}

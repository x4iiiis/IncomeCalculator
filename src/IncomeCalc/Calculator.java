package IncomeCalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator 
{
	public static void calculate() throws FileNotFoundException 
	{
		//Create the Finance Manager
		FinanceManager FM = FinanceManager.getInstance();
		
		//Outside variables
		double incomeTaxRate = 0;
		double nationalInsuranceRate = 0;
		double studentLoanRate = 0;
		
		//My variables
		double salary = FM.GetIncome().Salary;
		
		//Decimal formatter
		DecimalFormat df = new DecimalFormat("#.##");	//Fix decimal formatting
		
		
		System.out.println("\t\tSalary & Taxes\n");
		
		System.out.println("Salary:\t\t\t\t\t£" + df.format(salary));
		System.out.println("Tax Rate:\t\t\t\t" + getIncomeTax(salary) + "%");
		
		//Pass salary to obtain percentage, then apply it to the taxable income (above £11,500 threshold)
		//Take this off Salary, to obtain post-tax salary
		double income = salary - (getIncomeTax(salary) * (salary - 11500));
		
		System.out.println("Post-Tax Salary:\t\t\t£" + df.format(income));
		
		System.out.println("National Insurance Rate:\t\t" + getNationalInsuranceRate(salary) + "%");
		
		//Applying the national insurance rate to the salary minus the 162 threshold, and removing it from the salary
		//Income = Income - (GetNationalInsuranceRate(Salary) * (Salary - (162*52)));

		income = income - (calcAndDeductNationalInsurance(salary));
		System.out.println("Post National Insurance Salary:\t\t£" + df.format(income));
		
		double wage = income / 12;
		System.out.println("Wage:\t\t\t\t\t£" + df.format(wage));
		
		System.out.println();
		
		
		System.out.println("\n\t\tBills & Direct Debits");
		
		//Now we've got our salary data, applied income tax and national insurance to it,
		//it's time to apply the effects of bills on the remaining wage
		double[] billTotals = FM.payBills();
		
		//Now 
		System.out.println("\nSo far this wage, you have already paid £" + billTotals[0] + " in bills, "
				+ "taking your wage to £" + df.format((wage - billTotals[0])) + ".");
		//Apply that calculation
		wage -= billTotals[0];
		
		System.out.println("Before your next payday, you will pay a further £" + df.format(billTotals[1])
				+ ", and your remaining wage will be £" + df.format((wage - billTotals[1])) + ".");
		//Apply that calculation
		wage -= billTotals[1];
		
		System.out.println("In total, you pay £" + df.format((billTotals[0] + billTotals[1])) + 
				" in bills each month, excluding bills that are paid less often than once a month.");
		
		System.out.println("Those bills are:"); // List bills that are paid further into the future. Just x4iiiis.com atm
		System.out.println("\t[insert long-term bills here]");
		
		
		//Now for savings
		System.out.println("\n\n\t\tReasons to Save\n");
		//Not really but theoretical, might be true in future
		System.out.println("You have another direct debit; You pay ~£250 a month to your Santander account,"
			+ " which leaves you with £" + df.format(wage - 250) + ".");
		//Apply that
		wage -= 250;
		
		//actual savings...
		FM.workoutSavingsRequirements(wage);
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

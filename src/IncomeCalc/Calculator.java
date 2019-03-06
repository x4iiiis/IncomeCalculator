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
		//System.out.println("Tax Rate:\t\t\t\t" + getIncomeTax(salary) + "%");

		
		System.out.println("Income Tax Deduction:\t\t\t£" + df.format(getIncomeTax(salary)));
		//Pass salary to obtain percentage, then apply it to the taxable income (above £11,500 threshold)
		//Take this off Salary, to obtain post-tax salary
		double income = salary - (getIncomeTax(salary));
		System.out.println("Post-Income Tax Salary:\t\t\t£" + df.format(income));
		
		//System.out.println("Post-Tax Salary:\t\t\t£" + df.format(income));
		
		//Applying the national insurance rate to the salary minus the 162 threshold, and removing it from the salary
		//Income = Income - (GetNationalInsuranceRate(Salary) * (Salary - (162*52)));
		
		System.out.println("National Insurance Deduction:\t\t£" + df.format(calculateNationalInsurance(salary)));
		income -= calculateNationalInsurance(salary);
		
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
		//Reworking this...
		double TaxDeduction = 0;
		
		//	2019/20 Scottish Income Tax Bands
		double personalAllowance = 12500;
		double starterRate = 0.19;
		double basicRate = 0.2;
		double intermediateRate = 0.21;
		double higherRate = 0.41;
		double topRate = 0.46;
		
		
		//Anything less than 12501 is tax-free.
		if(salary <= personalAllowance)
		{
			return 0;
		}
		
		//StarterRate Band
		if(salary > personalAllowance)
		{
			if(salary <= 14549)
			{
				TaxDeduction += 14549 - (salary - personalAllowance) * starterRate;
			}
			else
			{
				TaxDeduction += (14549 - 12500) * starterRate;
			}
		}
		
		//Basic Rate Band
		if(salary > 14549)
		{
			if(salary <= 24944)
			{
				TaxDeduction += (salary - 14549) * basicRate;
				//TaxDeduction += ((24944 - salary) - 14549) * basicRate;
			}
			else
			{
				TaxDeduction += (24944 - 14549) * basicRate;
			}
		}
		
		//Intermediate Rate
		if(salary > 24944)
		{
			if(salary <= 43430)
			{
				TaxDeduction += (salary - 24944) * intermediateRate;
			}
			else
			{
				TaxDeduction += (43430 - 24944) * intermediateRate;
			}
		}
		
		//Higher Rate
		if(salary > 43430)
		{
			if(salary <= 15000)
			{
				TaxDeduction += (salary - 43430) * higherRate;
			}
			else
			{
				TaxDeduction += (150000 - 43430) * higherRate;
			}
		}
		
		//Top Rate
		if(salary > 150000)
		{
			TaxDeduction += (salary - 150000) * topRate;
		}
		
		return TaxDeduction;
		
		
		
		/*
		double taxRate = 0;
		
		//Incomes up to £11,500 are tax-free
		if(salary <= 12500)
		{
			taxRate = 0.0;
		}
		
		//Starter rate (19%) is between 12500 and 14549
		if(salary >= 12500 && salary <= 14549)
		{
			taxRate = 0.19;
		}
		
		//Basic rate is 20% on incomes from £14549 to £24944
		if(salary > 14549 && salary <= 24944)
		{
			taxRate = 0.2;
		}
		
		//Intermediate rate (21%) is between 24944 and 43430
		if(salary > 24944 && salary <= 43430)
		{
			taxRate = 0.21;
		}
		
		//Higher rate is 41% on incomes from £43,431 to £150,000
		if(salary >= 43431 && salary <= 150000)
		{
			taxRate = 0.41;
		}
		
		//Top rate is 45% on incomes over £150,000
		if(salary > 150000)
		{
			taxRate = 0.46;
		}
		
		return taxRate;
		*/
	}
	
	
	//Trying to fix this national insurance patter
	public static double calculateNationalInsurance(double salary)
	{
		//2019/20 national insurance rate dictates that £8632 to £50000 the 12% threshold, with anything over 50k gaining
		//an additional 2%
		double TwelvePercentPortion = 0;
		double TwoPercentPortion = 0;
		
		if (salary <= 50000)
		{
			TwelvePercentPortion = (salary - 8632) * 0.12;
		}
		else
		{
			TwoPercentPortion = (salary - 50000) * 0.02;
			TwelvePercentPortion = (50000 - 8632) * 0.12;
		}
		
		double NationalInsuranceDeduction = TwelvePercentPortion + TwoPercentPortion;
		
		return NationalInsuranceDeduction;
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

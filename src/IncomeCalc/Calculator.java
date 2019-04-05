package IncomeCalc;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

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
		//salary = 27000; //Testing purposes
		
		//Decimal formatter
		DecimalFormat df = new DecimalFormat("#.##");	//Fix decimal formatting
		
		
		System.out.println("\t\tSalary & Taxes\n");
		
		System.out.println("Salary:\t\t\t\t\t£" + df.format(salary));

		System.out.println("Income Tax Deduction:\t\t\t£" + df.format(getIncomeTax(salary)));
		
		double income = salary - (getIncomeTax(salary));
		System.out.println("Post-Income Tax Salary:\t\t\t£" + df.format(income));
		
		System.out.println("National Insurance Deduction:\t\t£" + df.format(calculateNationalInsurance(salary)));
		income -= calculateNationalInsurance(salary);
		
		System.out.println("Post National Insurance Salary:\t\t£" + df.format(income));
		
		double wage = income / 12;
		System.out.println("Monthly Wage:\t\t\t\t£" + df.format(wage) + "\n");
		
		
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
		double StarterRateMinimum = 12500;
		double BasicRateMinimum = 14594;
		double IntermediateRateMinimum = 24944;
		double HigherRateMinimum = 43430;
		double TopRateMinimum = 150000;
		//	2019/20 Scottish Income Tax Band Percentages
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
		if(salary > StarterRateMinimum)
		{
			if(salary <= BasicRateMinimum)
			{
				TaxDeduction += BasicRateMinimum - (salary - personalAllowance) * starterRate;
			}
			else
			{
				TaxDeduction += (BasicRateMinimum - StarterRateMinimum) * starterRate;
			}
		}
		
		//Basic Rate Band
		if(salary > BasicRateMinimum)
		{
			if(salary <= IntermediateRateMinimum)
			{
				TaxDeduction += (salary - BasicRateMinimum) * basicRate;
			}
			else
			{
				TaxDeduction += (IntermediateRateMinimum - BasicRateMinimum) * basicRate;
			}
		}
		
		//Intermediate Rate
		if(salary > IntermediateRateMinimum)
		{
			if(salary <= HigherRateMinimum)
			{
				TaxDeduction += (salary - IntermediateRateMinimum) * intermediateRate;
			}
			else
			{
				TaxDeduction += (HigherRateMinimum - IntermediateRateMinimum) * intermediateRate;
			}
		}
		
		//Higher Rate
		if(salary > HigherRateMinimum)
		{
			if(salary <= TopRateMinimum)
			{
				TaxDeduction += (salary - HigherRateMinimum) * higherRate;
			}
			else
			{
				TaxDeduction += (TopRateMinimum - HigherRateMinimum) * higherRate;
			}
		}
		
		//Top Rate
		if(salary > TopRateMinimum)
		{
			TaxDeduction += (salary - TopRateMinimum) * topRate;
		}
		
		return TaxDeduction;
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

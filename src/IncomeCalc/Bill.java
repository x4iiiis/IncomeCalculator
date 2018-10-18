package IncomeCalc;

import java.time.LocalDate;
import java.util.Date;

public class Bill {
	String billName;
	double billAmount = 0.0;
	int monthsBetweenPayments = 0;	//Years will be converted to months
	LocalDate lastPayment;	//The day of every month (or particular month of said year) bill will be paid on
	LocalDate nextPayment;
	
	public double Pay(Double RemainingIncome)
	{
		if(billAmount != 0)
		{
			System.out.println(billName + " paid.");
			//System.out.println(billName + " (£" + billAmount + ") paid.");
		}
		return RemainingIncome - billAmount;
	}
	
	public void EditAmount(double AmendedAmount)
	{
		billAmount = AmendedAmount;
	}
	
	//Maybe have a method to find it in the .csv and delete it, as well as removing it from the list
}

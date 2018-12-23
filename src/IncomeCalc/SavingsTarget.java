package IncomeCalc;

import java.time.LocalDate;
import java.util.Date;

public class SavingsTarget {
	String targetName;
	double targetAmount = 0.0;
	LocalDate targetDate;
	
	
	public void EditAmount(double AmendedAmount)
	{
		targetAmount = AmendedAmount;
	}
	
	public void EditDate(LocalDate newDate)
	{
		targetDate = newDate;
	}
}

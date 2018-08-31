package IncomeCalc;

public class Bill {
	String billName;
	double billAmount;
	
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

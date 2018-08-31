package IncomeCalc;

import java.util.ArrayList;
import java.util.List;

public class FinanceManager {

	//Singleton
	private static FinanceManager instance = null;
	
	//Finance Manager singleton contains the lists of all the bills and reasons to save
	//This keeps the code tidy and keeps the lists out of the way of the functionality program code
	private static List<Bill> BillList = new ArrayList<Bill>();
	private static List<ReasonToSave> SaveList = new ArrayList<ReasonToSave>();
	
	//FinanceManager is a singleton
	public static FinanceManager getInstance()
	{
		if(instance == null)
		{
			instance = new FinanceManager();
		}
		return instance;
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
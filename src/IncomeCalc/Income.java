package IncomeCalc;

import java.time.LocalDate;

public class Income {
	double Salary = 0.0;
	LocalDate LastPayday;
	LocalDate NextPayday; //Same as Bill.nextPayment - not in csv but unnecessary
	int MonthsBetweenWages = 0;
	int WeeksBetweenWages = 0;
}



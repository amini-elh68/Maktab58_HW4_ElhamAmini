package Maktab58_HW4_ElhamAmini.six;

import Maktab58_HW4_ElhamAmini.five.MyDate;

public class LoanAccount extends Account {

    private double loanAmount;
    private int loanTerm;

    public LoanAccount(double cash, MyDate accountOpeningDate, double interestRate) {
        super(cash, accountOpeningDate);
        super.setType(TypeOfAccount.Loan);
        super.interestRate = interestRate;
    }

    public void deductionOfInterestRates(MyDate date) {
        double interestRateAmount = (interestRate / 100) * loanAmount;
        if (super.cash < interestRateAmount) {
            System.out.println("Account balance is less than the interest rate amount");
        } else {
            super.cash -= interestRateAmount;
            System.out.println("The interest amount was deducted from the account balance");
            Transaction transaction = new Transaction(interestRateAmount, Transaction.TransactionType.WITHDRAW, date);
            super.addTransaction(transaction);
        }
    }
}

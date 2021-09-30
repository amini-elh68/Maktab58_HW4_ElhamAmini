package Maktab58_HW4_ElhamAmini.six;

import Maktab58_HW4_ElhamAmini.five.MyDate;

public class SavingAccount extends Account {
    public SavingAccount(double cash, MyDate accountOpeningDate) {
        super(cash, accountOpeningDate);
        super.setType(TypeOfAccount.Saving);
        super.interestRate = 10;
    }

    public void depositInterest(MyDate date) {
        double amount = super.cash * (interestRate / 100.0);
        super.cash += amount;
        System.out.println("Monthly interest was paid");
        super.inquiryCash();
        Transaction transaction = new Transaction(amount, Transaction.TransactionType.DEPOSIT, date);
        super.addTransaction(transaction);
    }
}

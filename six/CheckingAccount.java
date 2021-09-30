package Maktab58_HW4_ElhamAmini.six;

import Maktab58_HW4_ElhamAmini.five.MyDate;

public class CheckingAccount extends Account {
    public CheckingAccount(double cash, MyDate accountOpeningDate) {
        super(cash, accountOpeningDate);
        super.setType(TypeOfAccount.Checking);
    }

    public void withdraw(double cash, MyDate date) {
        if (cash > 2000000) {
            System.out.println("Withdrawal limit is 2 million");
        } else {
            super.cash -= 700;
            Transaction transaction = new Transaction(700, Transaction.TransactionType.Fees, date);
            super.addTransaction(transaction);
            super.withdraw(cash, date);
        }
    }
}

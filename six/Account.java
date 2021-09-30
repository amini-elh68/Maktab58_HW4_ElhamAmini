package Maktab58_HW4_ElhamAmini.six;

import Maktab58_HW4_ElhamAmini.five.MyDate;

import java.util.Random;

public class Account {
    protected int id;
    protected double cash;
    protected MyDate accountOpeningDate;
    protected double interestRate;
    private StringBuilder builder;
    private Transaction[] transactions;
    private TypeOfAccount type;

    enum TypeOfAccount {Checking, Saving, Loan}

    public Account(double cash, MyDate accountOpeningDate) {
        this.cash = cash;
        this.accountOpeningDate = accountOpeningDate;
        this.id = getRandomNumber();
    }

    public TypeOfAccount getType() {
        return type;
    }

    public void setType(TypeOfAccount type) {
        this.type = type;
    }

    public void inquiryCash() {
        System.out.println("The balance of this account is equal to: " + cash + "$");
    }

    public void deposit(double cash) {
        this.cash += cash;
        System.out.println("Account balance increased");
        inquiryCash();
    }

    public void withdraw(double cash, MyDate dateOfTransaction) {
        if (this.cash < cash) {
            System.out.println("Account balance is not enough");
        } else {
            this.cash -= cash;
            System.out.println("Account balance decreased");
            Transaction transaction = new Transaction(cash, Transaction.TransactionType.WITHDRAW, dateOfTransaction);
            addTransaction(transaction);
        }
        inquiryCash();
    }

    private int getRandomNumber() {
        Random random = new Random();
        int min = -100000;
        int max = 999999;
        int difference = max - min;
        int randomInt = random.nextInt(difference);
        return max - randomInt;
    }

    public void printInfo() {
        builder = new StringBuilder();
        builder.append("{account number: ").append(id).append(" - ");
        builder.append("type: ").append(getType()).append(" - ");
        builder.append("cash: ").append(cash).append("$").append(" - ");
        builder.append("date of opening: ").append(accountOpeningDate).append("}");
        System.out.println(builder);
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new Transaction[1];
            transactions[0] = transaction;
        } else {
            Transaction[] temp = new Transaction[transactions.length + 1];
            int index = 0;
            for (Transaction trans : getTransactions()) {
                temp[index++] = trans;
            }
            temp[index] = transaction;
            transactions = temp;
        }
    }

    public void getLastMonthInterest(MyDate date) {
        int createYear = accountOpeningDate.getYear();
        int createMonth = accountOpeningDate.getMonth();
        int year = date.getYear();
        int month = date.getMonth();
        int monthCount = 0;
        if (month < createMonth) {
            monthCount = ((year - 1) - createYear) * 12;
            monthCount += (month + (12 - createMonth));
        } else {
            monthCount = (year - createYear) * 12;
            monthCount += (month - createMonth);
        }
        double interest = 0;
        if (getType() == TypeOfAccount.Saving) {
            double amount = cash;
            for (int i = 0; i < monthCount; i++) {
                interest = amount * (interestRate / 100.0);
                amount = (amount + interest);
            }
        } else if (getType() == TypeOfAccount.Loan) {
            double amount = cash;
            for (int i = 0; i < monthCount; i++) {
                interest = amount * (interestRate / 100);
                amount = (amount - interest);
            }
        }
        System.out.println("interest of this account is: " + interest);
    }

    public int getId() {
        return id;
    }
}

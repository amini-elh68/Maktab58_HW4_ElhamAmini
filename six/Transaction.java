package Maktab58_HW4_ElhamAmini.six;

import Maktab58_HW4_ElhamAmini.five.MyDate;

public class Transaction {

    private double cash;
    private TransactionType transactionType;
    private MyDate dateOfTransaction;
    public Transaction(double cash, TransactionType transactionType, MyDate dateOfTransaction) {
        this.cash = cash;
        this.transactionType = transactionType;
        this.dateOfTransaction = dateOfTransaction;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public MyDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(MyDate dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public void printInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("{transaction type: ").append(transactionType).append(" - ");
        builder.append("amount: ").append(cash).append(" - ");
        builder.append("date of transaction: ").append(dateOfTransaction).append("}");
        System.out.println(builder);
    }

    enum TransactionType {WITHDRAW, DEPOSIT, Interest, Fees}
}

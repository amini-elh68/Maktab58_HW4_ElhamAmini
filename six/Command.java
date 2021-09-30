package Maktab58_HW4_ElhamAmini.six;

import Maktab58_HW4_ElhamAmini.five.MyDate;

import java.util.Scanner;

public class Command {

    private int commandNumber = -1;
    private final Scanner scanner = new Scanner(System.in);

    public void executeApp() {
        while (commandNumber != 7) {
            displayCommand();
            commandNumber = scanner.nextInt();
            if (validateCommand(commandNumber)) {
                executeCommand(commandNumber);
            } else {
                displayCommand();
            }
        }
    }

    private void displayCommand() {
        System.out.println("***************Menu**************");
        System.out.println("1. Add new account");
        System.out.println("2. View accounts");
        System.out.println("3. Withdraw");
        System.out.println("4. Deposit");
        System.out.println("5. Calculate interests");
        System.out.println("6. View Transactions");
        System.out.println("7. Exit");
        System.out.println("**********************************");
        System.out.println("please enter command number:");
    }

    private boolean validateCommand(int number) {
        if (number > 0 && number < 8) {
            return true;
        } else {
            System.out.println("please enter valid command number!");
            return false;
        }
    }

    private void executeCommand(int number) {
        switch (number) {
            case 1:
                addCustomerOperations();
                break;
            case 2:
                viewAccountsOperation();
                break;
            case 3:
                withdrawOperation();
                break;
            case 4:
                depositOperation();
                break;
            case 5:
                calculateInterestsOperation();
                break;
            case 6:
                viewTransactionsOperation();
                break;
        }
    }

    private void addCustomerOperations() {
        System.out.println("please enter customer first name:");
        String firstName = scanner.next();
        System.out.println("please enter customer last name:");
        String lastName = scanner.next();
        String fullName = firstName + " " + lastName;
        System.out.println("please enter type of account:");
        System.out.println("1. Checking Account");
        System.out.println("2. Saving Account");
        System.out.println("3. Loan Account");
        int typeNumber = scanner.nextInt();
        while (typeNumber > 3 || typeNumber < 1) {
            System.out.println("please enter valid number!");
            typeNumber = scanner.nextInt();
        }
        System.out.println("Please enter the amount to deposit:");
        double cash = scanner.nextDouble();
        System.out.println("please enter date of opening account");
        MyDate date = getDate();
        Customer customer = null;
        boolean exists = false;
        for (Customer customerItem : Bank.getCustomers()) {
            if (fullName.equals(customerItem.getFullName())) {
                customer = customerItem;
                exists = true;
                break;
            }
        }
        Account account = null;
        switch (typeNumber) {
            case 1:
                account = new CheckingAccount(cash, date);
                break;
            case 2:
                account = new SavingAccount(cash, date);
                break;
            case 3:
                System.out.println("please enter interest rate:");
                double interestRate = scanner.nextDouble();
                account = new LoanAccount(cash, date, interestRate);
                break;
        }
        if (!exists) {
            customer = new Customer(fullName);
            customer.addAccount(account);
            Bank.addCustomer(customer);
        } else {
            customer.addAccount(account);
        }
        System.out.println("Account created successfully");
    }

    private void viewAccountsOperation() {
        System.out.println("please enter customer first name:");
        String firstName = scanner.next();
        System.out.println("please enter customer last name:");
        String lastName = scanner.next();
        String fullName = firstName + " " + lastName;
        Customer customer = null;
        for (Customer customerItem : Bank.getCustomers()) {
            if (fullName.equals(customerItem.getFullName())) {
                customer = customerItem;
                break;
            }
        }
        if (customer != null)
            customer.printAccounts();
        else
            System.out.println("The customer was not found!");
    }

    private void withdrawOperation() {
        System.out.println("please enter account number:");
        int accountNumber = scanner.nextInt();
        accountNumber = validateAccountNumber(accountNumber);
        Transaction transaction = getTransaction(Transaction.TransactionType.WITHDRAW);
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.withdraw(transaction.getCash(), transaction.getDateOfTransaction());
        } else {
            System.out.println("Account not found!");
        }
    }

    private void depositOperation() {
        System.out.println("please enter account number:");
        int accountNumber = scanner.nextInt();
        accountNumber = validateAccountNumber(accountNumber);
        Transaction transaction = getTransaction(Transaction.TransactionType.DEPOSIT);
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(transaction.getCash());
            account.addTransaction(transaction);
        } else {
            System.out.println("Account not found!");
        }
    }

    private void calculateInterestsOperation() {
        System.out.println("please enter account number:");
        int accountNumber = scanner.nextInt();
        accountNumber = validateAccountNumber(accountNumber);
        Account account = getAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        if (account.getType() != Account.TypeOfAccount.Checking) {
            System.out.println("please enter date of last day of month:");
            MyDate date = getDate();
            account.getLastMonthInterest(date);
        } else {
            System.out.println("The account in question does not include profits");
        }
    }

    private void viewTransactionsOperation() {
        System.out.println("please enter account number:");
        int accountNumber = scanner.nextInt();
        accountNumber = validateAccountNumber(accountNumber);
        Account account = getAccount(accountNumber);
        for (Transaction transaction : account.getTransactions()) {
            transaction.printInfo();
        }
    }

    private Account getAccount(int accountNumber) {
        Account account = null;
        for (Customer customer : Bank.getCustomers()) {
            for (Account accountItem : customer.getAccounts()) {
                if (accountItem.getId() == accountNumber)
                    account = accountItem;
            }
            if (account != null)
                break;
        }
        return account;
    }

    private Transaction getTransaction(Transaction.TransactionType type) {
        System.out.println("please enter amount:");
        double cash = scanner.nextDouble();
        System.out.println("please enter date of transaction:");
        MyDate dateOfTransaction = getDate();
        Transaction transaction = new Transaction(cash, type, dateOfTransaction);
        return transaction;
    }

    private int validateAccountNumber(int accountNumber) {
        int accountNum = accountNumber;
        while (accountNum > 999999 || accountNum < -100000) {
            System.out.println("please enter valid account number!");
            accountNum = scanner.nextInt();
        }
        return accountNum;
    }

    private MyDate getDate() {
        System.out.println("please enter year:");
        int year = scanner.nextInt();
        System.out.println("please enter month:");
        int month = scanner.nextInt();
        System.out.println("please enter day:");
        int day = scanner.nextInt();
        MyDate myDate = new MyDate(year, month, day);
        return myDate;
    }
}

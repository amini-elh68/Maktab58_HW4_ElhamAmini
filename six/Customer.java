package Maktab58_HW4_ElhamAmini.six;

public class Customer {
    private final String fullName;
    private Account[] accounts;

    public Customer(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public Account[] getAccounts() {
        if (accounts == null)
            accounts = new Account[0];
        return accounts;
    }

    public String addAccount(Account account) {
        if (accounts == null) {
            accounts = new Account[1];
            accounts[0] = account;
        } else {
            Account[] temp = new Account[accounts.length + 1];
            int index = 0;
            for (Account acc : accounts) {
                temp[index++] = acc;
            }
            temp[index] = account;
            accounts = temp;
        }
        return "success";
    }

    public void printAccounts() {
        if (getAccounts() == null) {
            System.out.println("This customer does not have an account");
        } else {
            for (Account account : getAccounts()) {
                account.printInfo();
            }
        }
    }

}

package Maktab58_HW4_ElhamAmini.six;

public class Bank {
    private static Customer[] customers;

    public static void addCustomer(Customer customer) {
        if (customers == null) {
            customers = new Customer[1];
            customers[0] = customer;
        } else {
            Customer[] temp = new Customer[customers.length + 1];
            int index = 0;
            for (Customer customerItem : customers) {
                temp[index++] = customerItem;
            }
            temp[index] = customer;
            customers = temp;
        }
    }

    public static Customer[] getCustomers() {
        if (customers == null)
            customers = new Customer[0];
        return customers;
    }
}

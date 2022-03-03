package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    public static Map<String, Customer> allCustomers = new HashMap<>();

    public static void addCustomer(String email, String firstName, String lastName){
        if (getCustomer(email).getClass().equals(Customer.class)) {
            System.out.println("ERROR - Customer exists");
        } else {
            Customer newCustomer = new Customer(email, firstName, lastName);
            allCustomers.put(email, newCustomer);
        }
    }

    public static Customer getCustomer(String customerEmail){
        return allCustomers.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers(){
        return allCustomers.values();
    }
}

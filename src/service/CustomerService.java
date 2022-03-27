package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    // Limit class to a singleton instance
    private static CustomerService instance;
    private CustomerService(){};
    public static CustomerService getInstance(){
        if (instance == null){
            instance = new CustomerService();
        }
        return instance;
    }

    private static Map<String, Customer> customers = new HashMap<>();

    public void addCustomer(String email, String firstName, String lastName){
        if (getCustomer(email).getClass().equals(Customer.class)) {
            System.out.println("ERROR - Customer exists");
        } else {
            Customer newCustomer = new Customer(email, firstName, lastName);
            customers.put(email, newCustomer);
        }
    }

    public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);
    }

    public Collection<Customer> getCustomers(){
        return customers.values();

    }
}

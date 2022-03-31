package model;

import java.util.regex.Pattern;

public class Customer {
    protected String firstName;
    protected String lastName;
    protected String email;
    final static String emailValidationRegex = "^(.+)@(.+).(.+)$";

    public Customer(String email, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Validate email format
        Pattern emailValidationPattern = Pattern.compile(emailValidationRegex);
        if (emailValidationPattern.matcher(email).matches()){
            this.email = email;
        } else {
            throw new IllegalArgumentException("Customer email is not valid");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    @Override
    public String toString(){
        return "CUSTOMER - Name: " + this.getName() + " Email: " + email;
    }
}

package com.pik.entities;


import org.springframework.data.annotation.Id;

public class Customer
{
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String telephoneNumber;

    private String email;

    public Customer(String firstname, String lastname, String telephoneNumber, String email)
    {
        this.firstName = firstname;
        this.lastName = lastname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }

    @Override
    public String toString()
    {
        return String.format("Customer[id='%s', firstName='%s', lastName='%s', telephoneNumber='%s', email='%s']",
                id, firstName, lastName, telephoneNumber, email);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }
}

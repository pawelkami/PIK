package com.pik.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Customer
{
    @Id
    private String id;

    @JsonProperty("id")
    private String emberId;

    private String firstName;

    public String getEmberId() {
        return id;
    }

    public void setEmberId(String emberId) {
        this.emberId = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String lastName;

    private String telephoneNumber;

    private String email;

    public Customer() { }

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

package com.pik.entities;

import org.springframework.data.annotation.Id;

public class Reservation
{
    @Id
    private String id;

    private Hotel hotel;

    private Customer customer;

    private String beginDate;

    private String endDate;

    public Reservation(String beginDate, String endDate)
    {
    }

    @Override
    public String toString()
    {
        return String.format("Reservation[id='%s', hotel='%s', customer first name='%s', last name='%s', begin data='%s', end date='%s']",
                id, hotel.getName(), customer.getFirstName(), customer.getLastName(), beginDate, endDate);
    }

}

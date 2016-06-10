package com.pik.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Reservation
{
    @Id
    private String id;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    private String hotelName;

    private Customer customer;

    private String roomAmount;

    private String children;

    private String adults;

    private String beginDate;

    private String endDate;

    @JsonProperty("id")
    private String emberId;

    public String getEmberId() {
        return id;
    }

    public void setEmberId(String emberId) {
        this.emberId = id;
    }

    public Reservation() { }

    public Reservation(String hotelName, Customer customer, String roomAmount, String children, String adults, String beginDate, String endDate)
    {;
        this.hotelName = hotelName;
        this.customer = customer;
        this.roomAmount = roomAmount;
        this.children = children;
        this.adults = adults;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public void setRoomAmount(String roomAmount) { this.roomAmount = roomAmount; }

    public String getRoomAmount() { return roomAmount; }

    public void setChildren(String children) { this.children = children; }

    public String getChildren() { return children; }

    public void setAdults(String adults) { this.adults = adults; }

    public String getAdults() { return adults; }

    public void setBeginDate(String beginDate) { this.beginDate = beginDate; }

    public String getBeginDate() { return beginDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getEndDate() { return endDate; }

    @Override
    public String toString()
    {
        return String.format("Reservation[id='%s', hotel='%s', customer first name='%s', last name='%s', begin data='%s', end date='%s']",
                id, this.hotelName, customer.getFirstName(), customer.getLastName(), beginDate, endDate);
    }

}

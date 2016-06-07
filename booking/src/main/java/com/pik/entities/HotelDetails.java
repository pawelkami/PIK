package com.pik.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

public class HotelDetails
{
    @Id
    private String id;

    private String description;

    private String city;

    private String address;

    private String number;

    private String email;

    @Indexed(unique = true)
    private String hotelName;

    private List<byte[]> gallery;
    
    public List<byte[]> getGallery() {
        return gallery;
    }

    public void setGallery(List<byte[]> gallery) {
        this.gallery = gallery;
    }


    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public void setId(String id) {
        this.id = id;
    }

    public HotelDetails() {}

    public HotelDetails(String hotelName, String description, String city, String address, String number, String email
            , List<byte[]> gallery)
    {
        this.hotelName = hotelName;
        this.description = description;
        this.city = city;
        this.address = address;
        this.number = number;
        this.email = email;
        this.gallery = gallery;
    }

    @Override
    public String toString()
    {
        return String.format("Hotel[id='%s', hotel name='%s', description='%s']", id, hotelName, description);
    }
}

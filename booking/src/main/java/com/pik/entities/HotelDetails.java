package com.pik.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class HotelDetails
{
    @Id
    private String id;

    private String description;

    private String city;

    private String adress;

    private String number;

    private String email;

    @Indexed(unique = true)
    private String hotelName;

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

    public String getAdress() { return adress; }

    public void setAdress(String adress) { this.adress = adress; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public void setId(String id) {
        this.id = id;
    }


    public HotelDetails(String hotelName, String description, String city, String adress, String number, String email)
    {
        this.hotelName = hotelName;
        this.description = description;
        this.city = city;
        this.adress = adress;
        this.number = number;
        this.email = email;
    }

    @Override
    public String toString()
    {
        return String.format("Hotel[id='%s', hotel name='%s', description='%s']", id, hotelName, description);
    }
}

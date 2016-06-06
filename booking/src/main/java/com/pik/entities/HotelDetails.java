package com.pik.entities;


import org.springframework.data.annotation.Id;

public class HotelDetails
{
    @Id
    private String id;

    private String description;

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

    public void setId(String id) {
        this.id = id;
    }


    public HotelDetails(String hotelName, String description)
    {
        this.hotelName = hotelName;
        this.description = description;
    }

    @Override
    public String toString()
    {
        return String.format("Hotel[id='%s', hotel name='%s', description='%s']", id, hotelName, description);
    }
}

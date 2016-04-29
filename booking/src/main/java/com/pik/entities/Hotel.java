package com.pik.entities;

import org.springframework.data.annotation.Id;

public class Hotel {

    @Id
    private String id;

    private String name;
    private String city;
    private byte[] image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() { return city; }

    public void setCity(String city) {
        this.city = city;
    }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }


    public Hotel() {}

    public Hotel(String name, String city, byte[] image)
    {
        this.name = name;
        this.city = city;
        this.image = image;
    }

    @Override
    public String toString()
    {
        return String.format("Hotel[id='%s', name='%s', city='%s']", id, name, city);
    }
}

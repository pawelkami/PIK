package com.pik.entities;

import org.springframework.data.annotation.Id;

public class Hotel {

    @Id
    private String id;

    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public Hotel() {}

    public Hotel(String name, String city)
    {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString()
    {
        return String.format("Hotel[id='%s', name='%s', city='%s']", id, name, city);
    }
}

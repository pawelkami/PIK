package com.pik.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Hotel {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
    private String city;
    private byte[] image;

    @JsonProperty("id")
    private String emberId;

    public String getEmberId() {
        return id;
    }

    public void setEmberId(String emberId) {
        this.emberId = id;
    }

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

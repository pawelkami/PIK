package com.pik.repositories;

import java.util.List;

import com.pik.entities.Hotel;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel, String> {

    public Hotel findByName(String name);

    public List<Hotel> findByCity(String city);

}

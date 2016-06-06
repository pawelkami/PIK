package com.pik.repositories;

import com.pik.entities.HotelDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "hotelDetails", path = "hotelDetails")
public interface HotelDetailsRepository extends MongoRepository<HotelDetails, String>
{
    public HotelDetails findByHotelName(@Param(value = "hotelName")String hotelName);
}

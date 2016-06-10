package com.pik.repositories;


import com.pik.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "reservation", path = "reservation")
public interface ReservationRepository extends MongoRepository<Reservation, String>
{
    public List<Reservation> findByHotelName(@Param(value="hotelName") String hotelName);
}

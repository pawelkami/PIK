package com.pik.repositories;

import com.pik.entities.HotelDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Interfejs umożliwiający łączenie się z bazą danych i modyfikacje rekordów obiektu HotelDetails.
 */

@RepositoryRestResource(collectionResourceRel = "hotelDetails", path = "hotelDetails")
public interface HotelDetailsRepository extends MongoRepository<HotelDetails, String>
{

    /**
     * Wyszukiwanie hotelu po nazwie.
     * @param name nazwa szukanego hotelu
     * @return obiekt HotelDetails pasującego do nazwy
     */
    public HotelDetails findByHotelName(@Param(value = "hotelName")String hotelName);
}

package com.pik.repositories;

import java.util.List;

import com.pik.entities.Hotel;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

/**
 * Interfejs umożliwiający łączenie się z bazą danych i modyfikacje rekordów obiektu hotel.
 */

@RestController
@RepositoryRestResource(collectionResourceRel = "hotel", path = "hotel")
public interface HotelRepository extends MongoRepository<Hotel, String> {

    public Hotel findById(@Param(value="id") String id);

    /**
     * Wyszukiwanie hotelu po nazwie.
     * @param name nazwa szukanego hotelu
     * @return obiekt hotelu pasującego do nazwy
     */
    public Hotel findByName(@Param(value = "name") String name);

    /**
     * Wyszukiwanie hotelów po mieście.
     * @param city miasto w którym szukamy hotelu
     * @return lista hoteli w danym mieście
     */
    public List<Hotel> findByCity(@Param(value = "city") String city);

    /**
     * Wyszukiwanie hotelu o okreslonej liczbie wolnych miejsc.
     */

}

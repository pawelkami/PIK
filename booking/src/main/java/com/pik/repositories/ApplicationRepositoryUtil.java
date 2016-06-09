package com.pik.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Klasa udostępniająca wszystkie obiekty repozytoriów.
 */
@Service
public class ApplicationRepositoryUtil {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HotelDetailsRepository hotelDetailsRepository;

    /**
     * @return the mongoTemplate
     */
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * @return the customerRepository
     */
    public HotelRepository getHotelRepository() {
        return hotelRepository;
    }

    /**
     * @return the hotelDetailsRepository
     */
    public HotelDetailsRepository getHotelDetailsRepository() { return hotelDetailsRepository; }
}

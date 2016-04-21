package com.pik.repositories;

import com.pik.SpringMongoConfiguration;
import com.pik.entities.Hotel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HotelRepository.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringMongoConfiguration.class})
public class HotelRepositoryTests {

    @Autowired
    private HotelRepository repository;

    @Before
    public void init()
    {
        repository.deleteAll();
        for(int i = 0; i < 100; ++i)
            repository.save(new Hotel("Hilton" + i, "Warszawa"));
    }

    @Test
    public void addNewHotelTest()
    {
        List<Hotel> hotels = repository.findAll();
        Assert.assertEquals(100, hotels.size());
        for(Hotel h : hotels)
            System.out.println(h.toString());
    }

}

package com.pik.repositories;

import com.pik.SpringMongoConfiguration;
import com.pik.entities.Customer;
import com.pik.entities.Hotel;
import com.pik.entities.Reservation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReservationRepository.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringMongoConfiguration.class})
public class ReservationTests {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void init() throws IOException {
        repository.deleteAll();
        customerRepository.deleteAll();

        Random generator = new Random();

        List<Hotel> hotels = hotelRepository.findAll();
        List<String> firstNames = new LinkedList<String>((Arrays.asList("Wiktor", "Joanna", "Roman", "Elżbieta", "Wojciech", "Adam", "Bogdan", "Anna", "Urszula", "Jakub")));
        List<String> lastNames = new LinkedList<String>((Arrays.asList("Kowalczyk", "Piasek", "Nosek", "Tusk", "Arbus", "Rusek", "Guzik", "Sasza", "Banan", "Buzek")));
        List<String> telephoneNumbers = new LinkedList<String>((Arrays.asList("111111111", "222222222", "333333333", "444444444", "555555555", "666666666", "777777777", "888888888", "999999999", "696969696")));
        List<String> emails = new LinkedList<String>((Arrays.asList("a@a.a", "b@b.b", "c@c.c", "d@d.d", "e@e.e", "f@f.f", "g@g.g", "h@h.h", "i@i.i", "j@j.j")));
        List<String> beginDates = new LinkedList<String>((Arrays.asList("06/08/2016 12:39 AM", "06/07/2016 12:39 AM", "06/09/2016 12:39 AM", "06/01/2016 12:39 AM", "06/02/2016 12:39 AM", "06/03/2016 12:39 AM", "06/04/2016 12:39 AM", "06/05/2016 12:39 AM", "06/06/2016 12:39 AM", "06/11/2016 12:39 AM")));
        List<String> endDates = new LinkedList<String>((Arrays.asList("06/08/2017 12:39 AM", "06/07/2017 12:39 AM", "06/09/2017 12:39 AM", "06/01/2017 12:39 AM", "06/02/2017 12:39 AM", "06/03/2017 12:39 AM", "06/04/2017 12:39 AM", "06/05/2017 12:39 AM", "06/06/2017 12:39 AM", "06/11/2017 12:39 AM")));

        for(Hotel hotel : hotels)
        {
            String hotelName = hotel.getName();
            String firstName = firstNames.get(Math.abs(generator.nextInt() % firstNames.size()));
            String lastName = lastNames.get(Math.abs(generator.nextInt() % lastNames.size()));
            String telephoneNumber = telephoneNumbers.get(Math.abs(generator.nextInt() % telephoneNumbers.size()));
            String email = emails.get(Math.abs(generator.nextInt() % emails.size()));
            String roomAmount = Integer.toString(Math.abs(generator.nextInt() % 8 + 1));
            String children = Integer.toString(Math.abs(generator.nextInt() % 4));
            String adults = Integer.toString(Math.abs(generator.nextInt() % 4) + 1);
            String beginDate = beginDates.get(Math.abs(generator.nextInt() % beginDates.size()));
            String endDate = endDates.get(Math.abs(generator.nextInt() % endDates.size()));

            Customer customer = new Customer(firstName, lastName, telephoneNumber, email);
            customerRepository.save(customer);
            repository.save(new Reservation(hotelName, customer, roomAmount, children, adults, beginDate, endDate));
        }
    }

    @Test
    public void addNewReservationTest()
    {
        List<Reservation> reservations = repository.findAll();
        for(Reservation r : reservations)
            System.out.println(r.toString());
    }
}

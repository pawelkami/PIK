package com.pik.repositories;

import com.pik.SpringMongoConfiguration;
import com.pik.entities.Customer;
import com.pik.entities.Hotel;
import com.pik.entities.HotelDetails;
import com.pik.entities.Reservation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HotelRepository.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringMongoConfiguration.class})
public class HotelRepositoryTests {

    @Autowired
    private HotelRepository repository;

    @Autowired
    private HotelDetailsRepository detailsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Before
    public void init() throws IOException {
        repository.deleteAll();
        detailsRepository.deleteAll();
        customerRepository.deleteAll();
        reservationRepository.deleteAll();

        File hotelDirectories = new File("src/test/java/com/pik/repositories/HDB");
        for(File directory: hotelDirectories.listFiles())
        {
            String name = null;
            String city = null;
            byte[] mainPhoto = null;
            String description = null;
            String address = null;
            String number = null;
            String email = null;
            int roomsCount = 0;
            int occupiedRoomsCount = 0;
            List<byte[]> gallery = new ArrayList<byte[]>();
            boolean exceptionOccured = false;
            for(File file: directory.listFiles())
            {
                String path = hotelDirectories+"/"+directory.getName()+"/"+file.getName();
                System.out.println(path);
                if(file.getName().toLowerCase().startsWith("main."))
                {
                    mainPhoto = extractBytes(path);
                }
                else if(file.getName().toLowerCase().endsWith("info.json"))
                {
                    JSONParser parser = new JSONParser();
                    try {
                        Object obj = parser.parse(new FileReader(path));
                        JSONObject jsonObject = (JSONObject) obj;
                        name = (String) jsonObject.get("name");
                        city = (String) jsonObject.get("city");
                        description = (String) jsonObject.get("description");
                        address = (String) jsonObject.get("address");
                        number = (String) jsonObject.get("number");
                        email = (String) jsonObject.get("email");
                        roomsCount = ((Long) jsonObject.get("roomsCount")).intValue();
                        occupiedRoomsCount = ((Long) jsonObject.get("occupiedRoomsCount")).intValue();
                    } catch (ParseException pe) {
                        System.out.println("position: " + pe.getPosition());
                        System.out.println(pe);
                        exceptionOccured = true;
                        break;
                    }
                }
                else if(file.getName().toLowerCase().endsWith(".jpg"))
                {
                    byte[] photo = extractBytes(path);
                    gallery.add(photo);
                }
            }
            if(exceptionOccured)
                continue;
            repository.save(new Hotel(name, city, mainPhoto));
            detailsRepository.save(new HotelDetails(name, description,
                    city, address, number, email, gallery, roomsCount, occupiedRoomsCount));
        }
        File customers = new File("src/test/java/com/pik/Customers");
        for(File file: customers.listFiles())
        {
            String path = customers+"/"+file.getName();
            JSONParser parser = new JSONParser();
            try {
                JSONArray a = (JSONArray) parser.parse(new FileReader(path));
                for (Object o: a)
                {
                    JSONObject jsonObj = (JSONObject) o;
                    String firstName = (String) jsonObj.get("firstName");
                    String lastName = (String) jsonObj.get("lastName");
                    String telephoneNumber = (String) jsonObj.get("telephoneNumber");
                    String email = (String) jsonObj.get("email");
                    customerRepository.save(new Customer(firstName, lastName, telephoneNumber, email));
                }
            } catch (ParseException pe) {
                System.out.println("position: " + pe.getPosition());
                System.out.println(pe);
                break;
            }
        }
        File reservations = new File("src/test/java/com/pik/Reservations");
        for(File file: reservations.listFiles())
        {
            String path = reservations+"/"+file.getName();
            JSONParser parser = new JSONParser();
            try {
                JSONArray a = (JSONArray) parser.parse(new FileReader(path));
                for (Object o: a)
                {
                    JSONObject jsonObj = (JSONObject) o;
                    String hotel = (String) jsonObj.get("hotel");
                    System.out.println(hotel);
                    String email = (String) jsonObj.get("email");
                    String beginDate = (String) jsonObj.get("beginDate");
                    String endDate = (String) jsonObj.get("endDate");
                    Customer customer = customerRepository.findByEmail(email);
                    Reservation reservation = new Reservation(hotel, customer, "1","2","2", beginDate, endDate);
                    reservationRepository.save(reservation);
                }
            } catch (ParseException pe) {
                System.out.println("position: " + pe.getPosition());
                System.out.println(pe);
                break;
            }
        }
    }

    @Test
    public void addNewHotelTest()
    {
        List<Hotel> hotels = repository.findAll();
        Assert.assertEquals(10, hotels.size());
        for(Hotel h : hotels)
            System.out.println(h.toString());
    }

    private byte[] extractBytes (String ImageName) throws IOException {
        File new_file = new File(ImageName);
        BufferedImage originalImage= ImageIO.read(new_file);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos );
        byte[] imageInByte=baos.toByteArray();
        return imageInByte;
    }

}

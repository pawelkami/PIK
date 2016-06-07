package com.pik.repositories;

import com.pik.SpringMongoConfiguration;
import com.pik.entities.Hotel;
import com.pik.entities.HotelDetails;
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

    @Before
    public void init() throws IOException {
        repository.deleteAll();
        detailsRepository.deleteAll();

        File hotelDirectories = new File("src\\test\\java\\com\\pik\\repositories\\HDB");
        for(File directory: hotelDirectories.listFiles())
        {
            String name = null;
            String city = null;
            byte[] mainPhoto = null;
            String description = null;
            String address = null;
            String number = null;
            String email = null;
            List<byte[]> gallery = new ArrayList<byte[]>();
            for(File file: directory.listFiles())
            {
                String path = hotelDirectories+"\\"+directory.getName()+"\\"+file.getName();
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
                    } catch (ParseException pe) {
                        System.out.println("position: " + pe.getPosition());
                        System.out.println(pe);
                    }
                }
                else if(file.getName().toLowerCase().endsWith(".jpg"))
                {
                    byte[] photo = extractBytes(path);
                    gallery.add(photo);
                }
            }
            repository.save(new Hotel(name, city, mainPhoto));
            detailsRepository.save(new HotelDetails(name, description,
                    city, address, number, email, gallery));
        }
    }

    @Test
    public void addNewHotelTest()
    {
        List<Hotel> hotels = repository.findAll();
        //Assert.assertEquals(10, hotels.size());
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

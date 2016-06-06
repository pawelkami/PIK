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

    @Before
    public void init() throws IOException {
        repository.deleteAll();

        File hotelDirectories = new File("src\\test\\java\\com\\pik\\repositories\\HDB");
        for(File directory: hotelDirectories.listFiles())
        {
            String name = null;
            String city = null;
            byte[] mainPhoto = null;
            List<byte[]> gallery = new ArrayList<byte[]>();
            for(File file: directory.listFiles())
            {
                String path = hotelDirectories+"\\"+directory.getName()+"\\"+file.getName();
                System.out.println(path);
                if(file.getName().toLowerCase().startsWith("main."))
                {
                    mainPhoto = extractBytes(path);
                }
                else if(file.getName().toLowerCase().endsWith("info.txt"))
                {
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    name = br.readLine();
                    city = br.readLine();
                    br.close();
                }
                else if(file.getName().toLowerCase().endsWith(".jpg"))
                {
                    byte[] photo = extractBytes(path);
                    gallery.add(photo);
                }
            }
            if(name == null || city == null || mainPhoto == null)
                continue;
            repository.save(new Hotel(name, city, mainPhoto));
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

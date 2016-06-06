package com.pik.repositories;

import com.pik.SpringMongoConfiguration;
import com.pik.entities.Hotel;
import com.pik.entities.HotelDetails;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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

        byte[] img0 = this.extractBytes("src\\test\\java\\com\\pik\\repositories\\hotel.jpg");
        byte[] img1 = this.extractBytes("src\\test\\java\\com\\pik\\repositories\\hotel1.jpg");
        byte[] img2 = this.extractBytes("src\\test\\java\\com\\pik\\repositories\\hotel2.jpg");
        byte[] img3 = this.extractBytes("src\\test\\java\\com\\pik\\repositories\\hotel3.jpg");

        byte[] img;

        for(int i = 0; i < 10; ++i) {
            if(i%4 == 0)
                img = img0;
            else if(i%4 == 1)
                img = img1;
            else if(i%4 == 2)
                img = img2;
            else
                img = img3;
            repository.save(new Hotel("Hilton" + i, "Warszawa", img));
            detailsRepository.save(new HotelDetails("Hilton" + i, "desc"
                    , "Warszawa"
                    , "Pl. Politechiniki 1"
                    , "123-123-123"
                    , "info@piking.com"));
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
        File fnew=new File(ImageName);
        BufferedImage originalImage= ImageIO.read(fnew);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos );
        byte[] imageInByte=baos.toByteArray();
        return imageInByte;
    }

}

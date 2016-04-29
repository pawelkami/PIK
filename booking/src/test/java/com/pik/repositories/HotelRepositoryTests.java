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

    @Before
    public void init() throws IOException {
        repository.deleteAll();

        byte[] img = this.extractBytes("src\\test\\java\\com\\pik\\repositories\\hotel.jpg");

        for(int i = 0; i < 100; ++i)
            repository.save(new Hotel("Hilton" + i, "Warszawa", img));
    }

    @Test
    public void addNewHotelTest()
    {
        List<Hotel> hotels = repository.findAll();
        Assert.assertEquals(100, hotels.size());
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

    /*@Test
    public void TestSave()
    {
        Hotel h = repository.findByName("Hilton0");
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(h.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outputfile = new File("src\\test\\java\\com\\pik\\repositories\\hotel2.jpg");
        try {
            ImageIO.write(bi, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}

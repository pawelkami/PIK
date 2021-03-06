package com.pik;

import com.pik.repositories.HotelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BookingApplication.class)
@WebAppConfiguration
@EnableMongoRepositories(basePackageClasses = HotelRepository.class)
public class BookingApplicationTests {

	@Test
	public void contextLoads() {
	}

}

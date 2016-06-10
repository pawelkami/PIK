package com.pik;

import com.pik.entities.HotelDetails;
import com.pik.entities.Reservation;
import com.pik.repositories.ApplicationRepositoryUtil;
import com.pik.repositories.HotelDetailsRepository;
import com.pik.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomasz on 2016-06-09.
 */

@RestController
public class HotelDetailsController {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    HotelDetailsRepository hotelDetailsRepository;

    @RequestMapping(value = "/hotelDetails")
    @ResponseBody
    public boolean check(@RequestParam Map<String, String> params)
    {
        String hotelName = params.get("name");
        System.out.println(hotelName);
        int rooms = new Integer(params.get("rooms"));
        String beginDate = params.get("beginDate");
        String endDate = params.get("endDate");
        List<Reservation> reservations = reservationRepository.findByHotelName(hotelName);
        HotelDetails hotelDetails = hotelDetailsRepository.findByHotelName(hotelName);
        int freeRooms = hotelDetails.getRoomsCount();
        for(Reservation reser: reservations)
        {
            System.out.println("In");
            if(overlap(beginDate, endDate, reser.getBeginDate(), reser.getEndDate()))
            {
                int roomsAmount = new Integer(reser.getRoomAmount());
                freeRooms -= roomsAmount;
            }
        }
        System.out.print(freeRooms);
        return freeRooms >= rooms;
    }

    private boolean overlap(String fBeginDate, String fEndDate, String sBeginDate, String sEndDate)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fBdate = format.parse(fBeginDate);
            Date fEDate = format.parse(fEndDate);
            Date sBDate = format.parse(sBeginDate);
            Date sEDate = format.parse(sEndDate);

            System.out.println(fBdate.toString());

            return !(fBdate.compareTo(sBDate) > 0 && fBdate.compareTo(sEDate) > 0
                    || fEDate.compareTo(sEDate) < 0 && fEDate.compareTo(sBDate) < 0);
        }
        catch(Exception e)
        {
            System.out.println("Error while converting to data");
            return false;
        }
    }
}

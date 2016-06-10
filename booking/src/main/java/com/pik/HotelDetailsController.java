package com.pik;

import com.pik.entities.HotelDetails;
import com.pik.repositories.ApplicationRepositoryUtil;
import com.pik.repositories.HotelDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Tomasz on 2016-06-09.
 */

@RestController
public class HotelDetailsController {
    @Autowired
    ApplicationRepositoryUtil applicationRepositoryUtil;

    @RequestMapping(value = "/hotelDetailsReservation")
    @ResponseBody
    public boolean check(@RequestParam Map<String, String> params)
    {
        String hotelName = params.get("name");
        int rooms = (new Integer(params.get("roomsCount"))).intValue();
        HotelDetailsRepository hotelDetailsRepository = applicationRepositoryUtil.getHotelDetailsRepository();
        HotelDetails hotelDetails = hotelDetailsRepository.findByHotelName(hotelName);
        if(hotelDetails.getRoomsCount() - hotelDetails.getOccupiedRoomsCount() >= rooms)
            return true;
        return false;
    }
}

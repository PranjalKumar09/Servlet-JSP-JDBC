package com.icwd.hotelservice.services;

import com.icwd.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotelById(String id);
}

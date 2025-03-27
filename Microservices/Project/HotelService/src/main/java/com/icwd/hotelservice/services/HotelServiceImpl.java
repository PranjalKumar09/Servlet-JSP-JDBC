package com.icwd.hotelservice.services;

import com.icwd.hotelservice.entity.Hotel;
import com.icwd.hotelservice.exceptions.ResourceNotFoundException;
import com.icwd.hotelservice.reposistory.HotelReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelReposistory hotelReposistory;

    @Override
    public Hotel createHotel(Hotel hotel) {

        String hotilId = UUID.randomUUID().toString();
        hotel.setId(hotilId);
        return hotelReposistory.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelReposistory.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelReposistory.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
    }
}

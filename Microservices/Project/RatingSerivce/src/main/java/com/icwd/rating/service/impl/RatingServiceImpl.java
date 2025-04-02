package com.icwd.rating.service.impl;

import com.icwd.rating.entity.Rating;
import com.icwd.rating.repository.RatingRespository;
import com.icwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRespository ratingRespository;

    @Override
    public Rating createRating(Rating rating) {
        String id = UUID.randomUUID().toString();
        rating.setRatingId(id);
        return ratingRespository.save(rating);
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRespository.findAllByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotel(String hotelId) {
        return ratingRespository.findAllByHotelId(hotelId);
    }

    @Override
    public List<Rating> getAll() {
        return ratingRespository.findAll();
    }
}

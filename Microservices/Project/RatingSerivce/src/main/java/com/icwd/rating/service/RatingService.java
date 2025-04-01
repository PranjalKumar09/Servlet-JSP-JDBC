package com.icwd.rating.service;

import com.icwd.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotel(String hotelId);

    List<Rating> getAll();
}

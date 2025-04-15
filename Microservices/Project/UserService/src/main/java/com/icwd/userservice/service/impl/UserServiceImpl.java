package com.icwd.userservice.service.impl;


import com.icwd.userservice.enitity.Hotel;
import com.icwd.userservice.enitity.Rating;
import com.icwd.userservice.enitity.User;
import com.icwd.userservice.exceptions.ResourceNotFoundException;
import com.icwd.userservice.external.HotelService;
import com.icwd.userservice.reposistory.UserReository;
import com.icwd.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserReository userReository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public List<User> findAllUser() {
        return userReository.findAll();
    }

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);

        return userReository.save(user);
    }

    @Override
    public User getUserById(String id) {


        User user = userReository.findById(id).orElseThrow(()-> new ResourceNotFoundException(("User with id " + id + " not found")));

        Rating[] ratingsOfUsers =  restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);
        logger.info("{}", ratingsOfUsers);

        List<Rating> ratings = Arrays.stream(ratingsOfUsers).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

//            ResponseEntity<Hotel> forEntity =  restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            logger.info("{}", hotel);
            rating.setHotel(hotel);
            return new Rating();
        }).toList();

        user.setRatings(ratings);

        return user;

    }



}

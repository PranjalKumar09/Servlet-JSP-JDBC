package com.icwd.userservice.controller;

import com.icwd.userservice.enitity.User;
import com.icwd.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1  = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

        @GetMapping("/{userId}")
//    @CircuitBreaker(name= "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//@Retry(name="ratingHotelRetry", fallbackMethod = "ratingHotelFallback" )
@RateLimiter(name="ratingHotelRateLimiter", fallbackMethod = "ratingHotelFallback" )
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Throwable t) {
        log.info("Fallback is executed becasue service is down: "+  t.getMessage());
        User user  =  User.builder()
                .email("dumy@gmail.com")
                .name("Dumy")
                .about("This user is created dummy because some services is down")
                .userId("-1")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user);
    }


    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.findAllUser();
        return ResponseEntity.ok(userList);
    }


}

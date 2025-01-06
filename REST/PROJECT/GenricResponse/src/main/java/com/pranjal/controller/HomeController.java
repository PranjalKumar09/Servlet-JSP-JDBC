package com.pranjal.controller;

import com.pranjal.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/company")
    public ResponseEntity<?> getCompany(){
        List<String> company = Arrays.asList("Microsoft", "Apple", "IBM", "TCS");

        return new ResponseEntity<>(company, HttpStatus.OK);
    }



    @GetMapping("/company2")
    public ResponseEntity<?> getCompany2(){
        List<String> company = Arrays.asList("Microsoft", "Apple", "IBM", "TCS");

        return CommonUtil.createBuildResponse(company, "success", HttpStatus.OK);
    }



}

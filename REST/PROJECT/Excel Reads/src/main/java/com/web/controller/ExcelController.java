package com.web.controller;


import com.web.model.ProductDtls;
import com.web.service.ExcelService;
import com.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private ProductService productService;

    @PostMapping("/import-excel")
    public ResponseEntity<?> importExcel(@RequestParam MultipartFile file) {
        try {
            List<ProductDtls> productDtlsList = excelService.importExcel(file);
            if (productService.saveProduct(productDtlsList))
                return new ResponseEntity<>("Upload Success", HttpStatus.OK);
            return new ResponseEntity<>("Upload Fail", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

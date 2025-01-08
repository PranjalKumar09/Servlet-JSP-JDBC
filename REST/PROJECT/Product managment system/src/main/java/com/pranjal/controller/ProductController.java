package com.pranjal.controller;


import com.pranjal.dto.ProductDto;
import com.pranjal.dto.ProductResponse;
import com.pranjal.service.ProductService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    /*@PostMapping("/save-product")
    public ResponseEntity<?> saveProduct(@RequestBody @Valid ProductDto productDto) {


        try {
            if (!productService.saveProduct(productDto))
                return new ResponseEntity<>("Product not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Saved Success", HttpStatus.CREATED);
    }*/

    @PostMapping("/save-product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) throws BadRequestException {
        validateProductDto(productDto);

        try {
            if (!productService.saveProduct(productDto))
                return new ResponseEntity<>("Product not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Saved Success", HttpStatus.CREATED);
    }

    public void validateProductDto(ProductDto productDto) throws BadRequestException {
        // Validate name field
        if (productDto.getName() == null || productDto.getName().isEmpty()) {
            throw new BadRequestException("Name is required and cannot be empty.");
        }

        // Validate description field
        if (productDto.getDescription() == null || productDto.getDescription().isEmpty()) {
            throw new BadRequestException("Description is required and cannot be empty.");
        } else if (productDto.getDescription().length() < 2 || productDto.getDescription().length() > 100) {
            throw new BadRequestException("Description must be between 2 and 100 characters.");
        }

        // Validate price field
        if (productDto.getPrice() == null) {
            throw new BadRequestException("Price is required and cannot be null.");
        } else if (productDto.getPrice() <= 0) {
            throw new BadRequestException("Price must be greater than zero.");
        }

        // Validate quantity field
        if (productDto.getQuantity() == null) {
            throw new BadRequestException("Quantity is required and cannot be null.");
        } else if (productDto.getQuantity() < 0) {
            throw new BadRequestException("Quantity cannot be negative.");
        }
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        ProductDto productDto = productService.getProduct(id);

        if (productDto == null){
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDto> productDtoList = null;
        try{
            productDtoList = productService.getProducts();
            if (productDtoList == null){
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
       try {
           productService.deleteProduct(id);
           return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
       }
       catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/products-page")
    public ResponseEntity<?> getProductsByPagination(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "2") int pageSize, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "true") Boolean asc) {
        ProductResponse productResponse;

        try{
            productResponse = productService.getProductWithPagination(pageNo, pageSize, sortBy, asc);
            if (productResponse == null){
                return new ResponseEntity<>("Product not found", HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }

}

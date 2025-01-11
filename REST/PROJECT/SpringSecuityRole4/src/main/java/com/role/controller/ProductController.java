    package com.role.controller;


    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/product")
    public class ProductController {

        @PostMapping("/save")
        @PreAuthorize("hasRole('ADMIN') and hasAuthority('CREATE')")
        public ResponseEntity<?> saveProduct(){
            String msg = "Product :: Create Product";
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        }
        @GetMapping("/")
        @PreAuthorize("hasAnyRole('ADMIN','USER') and hasAuthority('CREATE')")
        public ResponseEntity<?> getProduct(){
            String msg = "Admin,Seller, Customer  :: View Product";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }

        @PutMapping("/edit")
        public ResponseEntity<?> editProduct(){
            String msg = "Admin,Seller :: Edit Product";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        @DeleteMapping("/delete")
        public ResponseEntity<?> deleteProduct(){
            String msg = "Admin,Seller :: Delete Product";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }

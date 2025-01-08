package com.pranjal.service;


import com.pranjal.dto.ProductDto;
import com.pranjal.dto.ProductResponse;
import com.pranjal.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Boolean saveProduct(ProductDto product);
    List<ProductDto> getProducts();
    ProductDto getProduct(int id);
    void deleteProduct(int id);
    ProductResponse getProductWithPagination(int pageNo, int pageSize , String sortBy, Boolean asc);
}

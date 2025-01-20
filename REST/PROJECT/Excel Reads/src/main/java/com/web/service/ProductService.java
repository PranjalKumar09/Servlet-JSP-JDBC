package com.web.service;


import com.web.model.ProductDtls;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Boolean saveProduct(List<ProductDtls> product);
}

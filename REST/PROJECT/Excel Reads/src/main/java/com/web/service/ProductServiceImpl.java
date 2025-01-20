package com.web.service;

import com.web.model.ProductDtls;
import com.web.reposistory.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

    @Service
    public class ProductServiceImpl implements ProductService {
        @Autowired
        private ProductRepo productRepo;

        @Override
        public Boolean saveProduct(List<ProductDtls> product) {
            List<ProductDtls> saveAll = productRepo.saveAll(product);
            return !CollectionUtils.isEmpty(saveAll);
        }
}

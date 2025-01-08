package com.pranjal.service.impl;

import com.pranjal.dto.ProductDto;
import com.pranjal.dto.ProductResponse;
import com.pranjal.model.Product;
import com.pranjal.repository.ProductRepository;
import com.pranjal.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSerivceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getProductWithPagination(int pageNo, int pageSize , String sortBy, Boolean asc) {

        Sort sort  = asc? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);

        List<ProductDto> productDtoList = page.getContent().stream().map(product -> mapper.map(product, ProductDto.class)).toList();


        return ProductResponse.builder().productDtoList(productDtoList).isFirst(page.isFirst()).isLast(page.isLast()).totalPages(page.getTotalPages()).totalElements(page.getTotalElements()).pageSize(pageSize).pageNo(pageNo).build();
    }

    @Override
        public Boolean saveProduct(ProductDto productDto) {

            Product product =  mapper.map(productDto, Product.class);

            if  (productRepository.save(product)!=null) {
                return true;
            };
            return false;
        }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(product -> mapper.map(product, ProductDto.class)).toList();
        return productDtos;

    }

    @Override
    public ProductDto getProduct(int id) {
        Product product = productRepository.getReferenceById(id);
        if (product == null) {
            return null;
        }

        return mapper.map(product, ProductDto.class);
    }
}

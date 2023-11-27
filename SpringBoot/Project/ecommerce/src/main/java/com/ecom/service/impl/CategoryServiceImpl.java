package com.ecom.service;

import com.ecom.entity.Category;
import com.ecom.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean existCategory(String categoryName) {
        return categoryRepository.existsByTitle(categoryName);
    }

    @Override
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }


}

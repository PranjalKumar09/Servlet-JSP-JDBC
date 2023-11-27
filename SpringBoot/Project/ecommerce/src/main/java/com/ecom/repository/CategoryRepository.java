package com.ecom.repository;

import com.ecom.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryReposistory extends JpaRepository<Category, Integer> {
    Boolean existsByTitle(String title);
}

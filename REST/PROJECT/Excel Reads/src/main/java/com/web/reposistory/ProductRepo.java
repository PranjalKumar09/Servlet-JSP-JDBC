package com.web.reposistory;

import com.web.model.ProductDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductDtls, Integer> {

}

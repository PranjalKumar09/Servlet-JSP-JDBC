package com.ecom.service.impl;

import com.ecom.entity.Cart;
import com.ecom.entity.Product;
import com.ecom.entity.UserDtls;
import com.ecom.repository.CartReposistory;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserReposistory;
import com.ecom.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastServiceImpl implements CartService {
    @Autowired
    private CartReposistory cartReposistory;
    @Autowired
    private UserReposistory userReposistory;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Cart> getCartByUserId(Integer userId) {
        return List.of();
    }

    @Override
    public Cart saveCart(Integer productId, Integer userId) {
        UserDtls userDtls = userReposistory.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        Cart cart = cartReposistory.findByUserAndAndProduct(userId, productId);

        if (cart == null && product != null ) {
            cart = new Cart();
            cart.setUser(userDtls);
            cart.setProduct(product);
            cart.setQuantity(1);
            cart.setTotalPrice(product.getDiscount_price());

        } else {
            cart = cartReposistory.findByUserAndAndProduct(userId, productId);
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscount_price());
        }
        return cartReposistory.save(cart);


    }
}

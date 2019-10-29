package com.xcl.service;

import com.xcl.entity.Cart;

import java.util.List;

public interface CartService {
    public void add(Cart cart);
    public List<Cart> findAllByUserId(Integer userid);
    public void updateCart(Integer id, Integer quantity, Double cost);
    public void deleteById(Integer id);
}

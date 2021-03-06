package com.xcl.repository;


import com.xcl.entity.Cart;

import java.util.List;

public interface CartRepository {
    public void add(Cart cart);
    public List<Cart> findAllByUserId(Integer userid);
    public void updateCart(Integer id, Integer quantity, Double cost);
    public void deleteById(Integer id);
    public Double getTotaleCostByUserId(Integer id);
    public void clearByUserId(Integer id);
}

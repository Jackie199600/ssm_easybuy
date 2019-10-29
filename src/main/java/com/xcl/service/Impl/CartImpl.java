package com.xcl.service.Impl;

import com.xcl.entity.Cart;
import com.xcl.repository.CartRepository;
import com.xcl.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Override
    public void add(Cart cart) {
        cartRepository.add(cart);
    }

    @Override
    public List<Cart> findAllByUserId(Integer userid) {
        return  cartRepository.findAllByUserId(userid);
    }

    @Override
    public void updateCart(Integer id, Integer quantity, Double cost) {
        cartRepository.updateCart(id,quantity,cost);
    }

    @Override
    public void deleteById(Integer id) {
        cartRepository.deleteById(id);
    }
}

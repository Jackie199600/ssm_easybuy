package com.xcl.service.Impl;


import com.xcl.entity.Cart;
import com.xcl.entity.Order;
import com.xcl.entity.OrderDetail;
import com.xcl.entity.User;
import com.xcl.repository.CartRepository;
import com.xcl.repository.OrderRepository;
import com.xcl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Integer addOrder(User user, String useraddress) {
        Order order = new Order();
        order.setUserid(user.getId());
        order.setUseraddress(useraddress);
        order.setLoginname(user.getLoginname());
        order.setCreatetime(new Date());
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < 32; i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber = result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        order.setSerialnumber(seriaNumber);
        order.setCost(cartRepository.getTotaleCostByUserId(user.getId()));
        orderRepository.add(order);
        return order.getId();
    }

    @Override
    public void addOrderDetail(User user, Integer orderid) {
        Order order = new Order();
        order.setId(orderid);
        List<Cart> list = cartRepository.findAllByUserId(user.getId());
        for (Cart cart : list) {
            order.setCart(cart);
            orderRepository.addDetail(order);
        }
        cartRepository.clearByUserId(user.getId());
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findOrderAndDetailByUserId(Integer userid) {
        List<Order> list = orderRepository.findAllByUserId(userid);
        for(Order order:list){
            List<OrderDetail> orderDetails = orderRepository.findAllDetailByOrderId(order.getId());
            order.setOrderDetails(orderDetails);
        }
        return list;
    }
}

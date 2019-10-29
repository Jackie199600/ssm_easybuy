package com.xcl.service;


import com.xcl.entity.Order;
import com.xcl.entity.User;

import java.util.List;

public interface OrderService {
    public Integer addOrder(User user,String useraddress);
    public void addOrderDetail(User user,Integer orderid);
    public Order findById(Integer id);
    public List<Order> findOrderAndDetailByUserId(Integer userid);
}

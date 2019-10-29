package com.xcl.repository;

import com.xcl.entity.Order;
import com.xcl.entity.OrderDetail;

import java.util.List;

public interface OrderRepository {
    public void add(Order order);
    public void addDetail(Order order);
    public Order findById(Integer id);
    public List<Order> findAllByUserId(Integer userid);
    public List<OrderDetail> findAllDetailByOrderId(Integer orderid);
}

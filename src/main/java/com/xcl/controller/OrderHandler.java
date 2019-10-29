package com.xcl.controller;

import com.xcl.entity.Order;
import com.xcl.entity.User;
import com.xcl.service.CartService;
import com.xcl.service.OrderService;
import com.xcl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderHandler {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public ModelAndView add(String selectAddress, String address,String remark,HttpSession session){
        User user=(User) session.getAttribute("user");
        if(selectAddress.equals("newAddress")){
            userService.save(user.getId(),address,remark);
            selectAddress = address;
        }
        //给Order添加一条记录
        Integer id=orderService.addOrder(user,selectAddress);
        //给OrderDetail添加一条记录
        orderService.addOrderDetail(user,id);
        //查询Order
        Order order=orderService.findById(id);
        user.setCarts(cartService.findAllByUserId(user.getId()));
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("order",order);
        modelAndView.setViewName("settlement3");
        return modelAndView;
    }

    @RequestMapping("/findByUser")
    public ModelAndView findByUser(HttpSession session){
        User user=(User) session.getAttribute("user");
        List<Order> orders=orderService.findOrderAndDetailByUserId(user.getId());
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("list",orders);
        modelAndView.setViewName("orderList");
        return modelAndView;
    }

}

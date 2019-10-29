package com.xcl.controller;

import com.xcl.entity.*;
import com.xcl.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductHandler {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "findAllByLevelAndId/{level}/{id}")
    public ModelAndView findAllByLevelAndId(@PathVariable("level") String level, @PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.findAllByLevelAndId(level, id);
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        modelAndView.setViewName("goodsList");
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("productCategoryList", productCategoryList);
        return modelAndView;
    }


    @RequestMapping(value = "findById/{id}")
    public ModelAndView findById(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", productService.findById(id));
        modelAndView.addObject("productCategoryList", productCategoryService.findAll());
        modelAndView.setViewName("productDetail");
        return modelAndView;
    }

    @RequestMapping(value = "addCart/{id}/{price}/{quantity}")
    public String addCart(@PathVariable("id") Integer id, @PathVariable("price") Double price, @PathVariable("quantity") Integer quantity, HttpSession session) {
        Cart cart = new Cart();
        cart.setProductid(id);
        cart.setQuantity(quantity);
        cart.setCost(quantity * price);
        User user = (User) session.getAttribute("user");
        cart.setUserid(user.getId());
        cartService.add(cart);
        user.setCarts(cartService.findAllByUserId(user.getId()));
        return "redirect:/product/settlement";
    }

    @RequestMapping(value = "/settlement")
    public ModelAndView settlement(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Cart> list = cartService.findAllByUserId(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("toSettlement");
        return modelAndView;
    }

    @RequestMapping(value = "updateCart/{id}/{quantity}/{cost}")
    @ResponseBody
    public String update(@PathVariable("id") Integer id, @PathVariable("quantity") Integer quantity, @PathVariable("cost") Double cost, HttpSession session) {
        cartService.updateCart(id, quantity, cost);
        User user = (User) session.getAttribute("user");
        user.setCarts(cartService.findAllByUserId(user.getId()));
        return "success";
    }

    @RequestMapping(value = "removeCart/{id}")
    public String deleteById(@PathVariable("id") Integer id, HttpSession session) {
        cartService.deleteById(id);
        User user = (User) session.getAttribute("user");
        user.setCarts(cartService.findAllByUserId(user.getId()));
        return "redirect:/product/settlement";
    }

    @RequestMapping(value = "/settlement2")
    public ModelAndView settlement2(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Cart> list = cartService.findAllByUserId(user.getId());
        List<Address> addresses = userService.findAllAddressByUserId(user.getId());
        ModelAndView modelAndVie = new ModelAndView();
        modelAndVie.setViewName("settlement2");
        modelAndVie.addObject("list", list);
        modelAndVie.addObject("addresses", addresses);
        return modelAndVie;
    }


    @RequestMapping(value = "/search")
    public ModelAndView search(String keyWord) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.findAllByKeyWord(keyWord));
        modelAndView.addObject("productCategoryList", productCategoryService.findAll());
        modelAndView.setViewName("goodsList");
        return modelAndView;
    }


}

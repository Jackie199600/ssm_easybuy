package com.xcl.controller;

import com.xcl.entity.User;
import com.xcl.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private CartService cartService;


    @RequestMapping(value = "login")
    public String login(String loginName, String passWord, HttpSession session) {
        User user = userService.login(loginName, passWord);
        if (user == null) {
            return "index";
        } else {
            user.setCarts(cartService.findAllByUserId(user.getId()));
            session.setAttribute("user", user);
            return "redirect:/user/index";// MVC中的重定向
        }

    }

    @RequestMapping("/findNewsByPage/{page}/{limit}")
    public ModelAndView findNewsByPage(@PathVariable("page") int page, @PathVariable("limit") int limit) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newsList");
        int pages = newsService.getPages(limit);
        if (page > pages) {
            page = pages;
        }
        if (page == 0) {
            page = 1;
        }
        if (page < 0) {
            page = 1;
        }
        modelAndView.addObject("list", newsService.findByPage(page, limit));
        modelAndView.addObject("pages", newsService.getPages(limit));
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @RequestMapping("/findAllAddressByUserId")
    public ModelAndView findAllAddressByUserId(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userAddressList");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("list", userService.findAllAddressByUserId(user.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("productCategoryList", productCategoryService.findAll());
        modelAndView.addObject("news", newsService.findForMain());
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.jsp";
    }

    @RequestMapping("/register")
    public String register(User user) {
        userService.register(user);
        return "redirect:/user/login";
    }
}

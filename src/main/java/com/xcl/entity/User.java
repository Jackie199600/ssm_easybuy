package com.xcl.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String loginname;
    private String username;
    private String password;
    private String sex;
    private String indetitycode;
    private String email;
    private String mobile;
    private String filename;
    private List<Cart> carts;
    private List<Address> addresses;
}

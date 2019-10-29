package com.xcl.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private Integer userid;
    private String loginname;
    private String useraddress;
    private Date createtime;
    private Double cost;
    private String serialnumber;
    private Cart cart;
    private List<OrderDetail> orderDetails;
}

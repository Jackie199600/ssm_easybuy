package com.xcl.entity;

import lombok.Data;

@Data
public class Cart {
    private Integer id;
    private Integer productid;
    private Product product;
    private Integer quantity;
    private Double cost;
    private Integer userid;
}

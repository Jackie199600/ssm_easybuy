package com.xcl.entity;

import lombok.Data;

@Data
public class OrderDetail {
    private Integer id;
    private Product product;
    private Integer quantity;
    private Double cost;
}
